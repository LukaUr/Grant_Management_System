$(() => {
    'use strict'
    //utils

    const getYearFromDate = (date) => {
        if (date == undefined) return null;
        return parseInt(date.substr(0, 4));
    }

    const updateTable = () => {

// set project start

        const $taskStarts = $('.taskStart');
        const taskStartDates = $taskStarts.map(function () {
            return $(this).val()
        });1
        const taskStartDatesArray = [];
        for (const date of taskStartDates) {
            if (date != "") {
                taskStartDatesArray.push(date);
            }
        }
        const projectStartDate = (taskStartDatesArray.length == 0) ? undefined : taskStartDatesArray.reduce(function (pre, cur) {
            return Date.parse(pre) > Date.parse(cur) ? cur : pre;
        });

// set project end

        const $taskEnds = $('.taskEnd')
        const taskEndDates = $taskEnds.map(function () {
            return $(this).val()
        });
        const taskEndDatesArray = [];
        for (const date of taskEndDates) {
            if (date != "") {
                taskEndDatesArray.push(date);
            }
        }
        const projectEndDate = (taskEndDatesArray.length == 0) ? undefined : taskEndDatesArray.reduce(function (pre, cur) {
            return Date.parse(pre) > Date.parse(cur) ? pre : cur;
        })

// determine years to be included in budget table

        const firstYear = getYearFromDate(projectStartDate);
        const lastYear = getYearFromDate(projectEndDate);
        console.log(projectStartDate);
        console.log(firstYear);
        console.log(projectEndDate);
        console.log(lastYear);
        const listOfYears = [];
        for (let i = firstYear; i < (lastYear + 1); i++) {
            listOfYears.push(i);
        }

// fetch current budget data

        class BudgetTask {
            constructor(id, localId, entries) {
                this.id = id;
                this.localId = localId;
                this.entries = entries;
            }
        }

        class BudgetEntry {
            constructor(id, year, total, funding) {
                this.id = id;
                this.year = year;
                this.total = total;
                this.funding = funding;
                this.selfFunding = total - funding;
                this.coverage = funding / total * 100;
            }
        }

        class TaskFromTimetable {
            constructor(id, localId, name, yearOfStart, yearOfEnd) {
                this.id = id;
                this.localId = localId;
                this.name = name;
                this.yearOfStart = yearOfStart;
                this.yearOfEnd = yearOfEnd;
            }
        }

        const $budgetTasks = $('#budgetTable .budgetTask');
        let budgetTaskList = [];
        for (const task of $budgetTasks) {
            const $budgetEntries = $(task).find('.budgetData');
            let budgetEntryList = [];
            for (const entry of $budgetEntries) {
                const entryId = $(entry).find('.entryId').val();
                const entryYear = $(entry).find('.entryYear').val();
                const entryTotal = $(entry).find('.entryTotal').val();
                const entryFunding = $(entry).find('.entryFunding').val();
                const budgetEntry = new BudgetEntry(entryId, entryYear, entryTotal, entryFunding);
                budgetEntryList.push(budgetEntry);
            }
            const taskId = $(task).find('.budgetTaskId').val();
            const localId = $(task).data('local_id');
            const budgetTask = new BudgetTask(taskId, localId, budgetEntryList);
            budgetTaskList.push(budgetTask);
        }

        // clear current budget table

        const $budgetTable = $('#budgetTable');
        $budgetTable.remove();

        // build new budget table

        const $newBudgetTable = $('<table id="budgetTable">');

        //header

        const $headerTr = $('<tr>');
        $headerTr.append($('<th>No.</th>'));
        $headerTr.append($('<th>Task name</th>'));
        $headerTr.append($('<th>Value</th>'));
        for (let i of listOfYears) {
            const $header = ($('<th></th>'))
            $header.text(i);
            $headerTr.append($header);
        }
        $newBudgetTable.append($headerTr);

        // populate new task list with tasks from timetable

        const tasksFromTimetable = [];
        const $taskDivsFromTimetable = $('#timetable .taskDiv')
        for (const taskDiv of $taskDivsFromTimetable) {
            const taskId = $(taskDiv).find('.taskId').val();
            const localId = $(taskDiv).data('local_id');
            const name = $(taskDiv).find('.taskName').val();
            const dateOfStart = $(taskDiv).find('.taskStart').val();
            const yearOfStart = getYearFromDate(dateOfStart);
            const dateOfEnd = $(taskDiv).find('.taskEnd').val();
            const yearOfEnd = getYearFromDate(dateOfEnd);
            const task = new TaskFromTimetable(taskId, localId, name, yearOfStart, yearOfEnd);
            tasksFromTimetable.push(task);
        }

        // *****
        // fill budget table with tasks and entries using tasks from timetable and fetched budgetary data
        // *****

        // fill first three columns with general data

        for (let i = 0; i < tasksFromTimetable.length; i++) {
            const taskFromTT = tasksFromTimetable[i];
            const localId = taskFromTT.localId;
            const $row = $(`<tr class="budgetTask" data-local_id="${localId}">`);

            const $firstColumn = $('<td>');
            $firstColumn.text(i + 1);
            $row.append($firstColumn);

            const $secondColumn = $('<td>');
            $secondColumn.text(taskFromTT.name);
            $row.append($secondColumn);

            const $thirdColumn = $('<td>');
            const $budgetEntryNames = $('<div class="budgetEntry valueNames">');
            $budgetEntryNames.append($('<div>Total amount</div>'));
            $budgetEntryNames.append($('<div>Grant</div>'));
            $budgetEntryNames.append($('<div>Coverage</div>'));
            $budgetEntryNames.append($('<div>Self funding</div>'));
            const $IdInput = $(`<input type="number" class="budgetTaskId" id="tasks${i}.id" name="tasks[${i}].id" hidden/>`)
            $IdInput.val(taskFromTT.id);
            $budgetEntryNames.append($IdInput);
            $thirdColumn.append($budgetEntryNames);
            $row.append($thirdColumn);

            // if no data provided, fill with placeholder

            if (isNaN(taskFromTT.yearOfStart) ||
                // taskFromTT.yearOfStart == null ||
                isNaN(taskFromTT.yearOfEnd) ||
                // taskFromTT.yearOfEnd === null ||
                taskFromTT.yearOfEnd < taskFromTT.yearOfStart) {
                const yearsCount = listOfYears.length;
                const $column = $(`<td colspan="${yearsCount}" style="color: red">`);
                $column.text("Define the start and the end date of this task in Timetable");
                $row.append($column);
                $newBudgetTable.append($row);

            } else {

                // iterate through all budget tasks and find one matching localId

                let currentBudgetTask = new BudgetTask(null, null, []);
                for (const budgetTask of budgetTaskList) {
                    if (budgetTask.localId == localId) {
                        currentBudgetTask.id = budgetTask.id;
                        currentBudgetTask.localId = budgetTask.localId;
                        currentBudgetTask.entries = budgetTask.entries;
                    }
                }

                // fill columns with data for each year

                let entryCount = 0;
                for (const year of listOfYears) {

                    // fill with empty tag if year no in range of the task

                    if (year < taskFromTT.yearOfStart || year > taskFromTT.yearOfEnd) {
                        const $emptyDiv = $('<div class="emptyEntry">');
                        const $emptyColumn = $('<td>');
                        $emptyColumn.append($emptyDiv);
                        $row.append($emptyColumn);
                    } else {

                        // prepare data to fill the budget entry

                        let currentEntry = new BudgetEntry();
                        for (const budgetEntry of currentBudgetTask.entries) {
                            if (budgetEntry.year == year) {
                                currentEntry.id = budgetEntry.id;
                                currentEntry.year = year;
                                currentEntry.total = budgetEntry.total;
                                currentEntry.funding = budgetEntry.funding;
                                currentEntry.selfFunding = currentEntry.total - currentEntry.funding;
                                currentEntry.coverage = currentEntry.funding / currentEntry.total * 100;
                            }
                        }

                        // append entry to the table

                        const $column = $('<td>');
                        const $entryDiv = $('<div class="budgetEntry budgetData">');
                        const $entryId = $(`<input type="number" class="entryId" id="tasks${i}.budgetEntryList${entryCount}.id}" name="tasks[${i}].budgetEntryList[${entryCount}].id" value="${currentEntry.id}" hidden/>`)
                        $entryDiv.append($entryId);
                        const $entryYear = $(`<input type="number" class="entryYear" id="tasks${i}.budgetEntryList${entryCount}.year}" name="tasks[${i}].budgetEntryList[${entryCount}].year" value="${year}" hidden/>`);
                        $entryDiv.append($entryYear);

                        const $totalDiv = $('<div>');
                        const $totalInput = $(`<input type="number" class="entryTotal" data-budget_type="total" id="tasks${i}.budgetEntryList${entryCount}.totalAmount}" name="tasks[${i}].budgetEntryList[${entryCount}].totalAmount" value="${currentEntry.total}">`)
                        $totalDiv.append($totalInput);
                        $entryDiv.append($totalDiv);

                        const $fundingDiv = $('<div>');
                        const $fundingInput = $(`<input type="number" class="entryFunding" data-budget_type="funding" id="tasks${i}.budgetEntryList${entryCount}.totalFunding}" name="tasks[${i}].budgetEntryList[${entryCount}].totalFunding" value="${currentEntry.funding}">`)
                        $fundingDiv.append($fundingInput);
                        $entryDiv.append($fundingDiv);

                        const $coverageDiv = $('<div>');
                        const $coverageInput = $(`<input type="number" class="entryCoverage" value="${currentEntry.coverage}" disabled>`)
                        $coverageDiv.append($coverageInput);
                        $coverageDiv.append($('<span>%</span>'))
                        $entryDiv.append($coverageDiv);

                        const $selfDiv = $('<div>');
                        const $selfInput = $(`<input type="number" class="entrySelf" id="tasks${i}.budgetEntryList${entryCount}.selfFunding}" name="tasks[${i}].budgetEntryList[${entryCount}].selfFunding" value="${currentEntry.selfFunding}" disabled>`)
                        const $selfInputHidden = $(`<input type="number" class="entrySelf" id="tasks${i}.budgetEntryList${entryCount}.selfFunding}" name="tasks[${i}].budgetEntryList[${entryCount}].selfFunding" value="${currentEntry.selfFunding}" hidden>`)
                        $selfDiv.append($selfInput);
                        $selfDiv.append($selfInputHidden);
                        $entryDiv.append($selfDiv);

                        $column.append($entryDiv);
                        $row.append($column);
                        entryCount++;
                    }
                }
                $newBudgetTable.append($row);
            }
        }

// append new budgetTable to DOM

        const $budgetTableDiv = $('#budgetTableDiv');
        $budgetTableDiv.append($newBudgetTable);
        console.log('table updated');

// calculate new general info

        const $totalValues = $('.budgetData input[data-budget_type="total"]');
        let totalProjectValue = 0;
        for (const input of $totalValues) {
            if (typeof +($(input).val()) == 'number') {
                totalProjectValue += +($(input).val());
            }
        }
        const $totalProjectValueInfo = $('#generalInfo #totalValue');
        $totalProjectValueInfo.text(totalProjectValue);

        const $totalProjectValueInput = $('#timetable input[data-total_value=""]')
        $totalProjectValueInput.val(totalProjectValue);

// calculate new funding info

        const $fundingValues = $('.budgetData input[data-budget_type="funding"]');
        let fundingProjectValue = 0;
        for (const input of $fundingValues) {
            if (typeof +($(input).val()) == "number") {
                fundingProjectValue += +($(input).val());
            }
        }
        const $fundingPRojectValueInfo = $('#generalInfo #totalGrant');
        $fundingPRojectValueInfo.text(fundingProjectValue);

        const $totalProjectFundingInput = $('#timetable input[data-grant_value=""]')
        $totalProjectFundingInput.val(fundingProjectValue);

    }
    updateTable();

    const $budgetTableButton = $('.leftMenuItem[data-section="budget"]');
    $budgetTableButton.on('click', () => updateTable());

    const $inputsInTable = $('#budgetTableDiv');
    $inputsInTable.on('change', 'input', () => updateTable());

})
