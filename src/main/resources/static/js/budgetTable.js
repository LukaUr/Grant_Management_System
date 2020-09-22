$(() => {
    'use strict'
    //utils

    const getYearFromDate = (date) => {
        if (date == undefined || date == null) return null;
        return parseInt(date.substr(0, 4));
    }

    // determine years to be included in budget table
    const updateTable = () => {

        const $taskStarts = $('.taskStart');
        const taskStartsDates = $taskStarts.map(function () {
            return $(this).val()
        });
        const taskStartYears = new Array();
        for (const date of taskStartsDates) {
            if (date != "") {
                taskStartYears.push(getYearFromDate(date));
            }
        }
        const firstYear = Math.min(...taskStartYears);

        const $taskEnds = $('.taskEnd')
        const taskEndDates = $taskEnds.map(function () {
            return $(this).val()
        });
        const taskEndYears = new Array();
        for (const date of taskEndDates) {
            if (date != "") {
                taskEndYears.push(getYearFromDate(date));
            }
        }
        ;
        const lastYear = Math.max(...taskEndYears);
        const listOfYears = new Array();
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
        let budgetTaskList = new Array();
        for (const task of $budgetTasks) {
            const $budgetEntries = $(task).find('.budgetData');
            let budgetEntryList = new Array();
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

        const tasksFromTimetable = new Array();
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
            const $IdInput = $(`<input type="number" class="budgetTaskId" id="timetable.tasks${i}.id" name="timetable.tasks[${i}].id" hidden/>`)
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

                let currentBudgetTask = new BudgetTask(null, null, new Array());
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
                        const $entryId = $(`<input type="number" class="entryId" id="timetable.tasks${i}.budgetEntryList${entryCount}.id}" name="timetable.tasks[${i}].budgetEntryList[${entryCount}].id" value="${currentEntry.id}" hidden/>`)
                        $entryDiv.append($entryId);
                        const $entryYear = $(`<input type="number" class="entryYear" id="timetable.tasks${i}.budgetEntryList${entryCount}.year}" name="timetable.tasks[${i}].budgetEntryList[${entryCount}].year" value="${year}" hidden/>`);
                        $entryDiv.append($entryYear);

                        const $totalDiv = $('<div>');
                        const $totalInput = $(`<input type="number" class="entryTotal" id="timetable.tasks${i}.budgetEntryList${entryCount}.totalAmount}" name="timetable.tasks[${i}].budgetEntryList[${entryCount}].totalAmount" value="${currentEntry.total}">`)
                        $totalDiv.append($totalInput);
                        $entryDiv.append($totalDiv);

                        const $fundingDiv = $('<div>');
                        const $fundingInput = $(`<input type="number" class="entryFunding" id="timetable.tasks${i}.budgetEntryList${entryCount}.totalFunding}" name="timetable.tasks[${i}].budgetEntryList[${entryCount}].totalFunding" value="${currentEntry.funding}">`)
                        $fundingDiv.append($fundingInput);
                        $entryDiv.append($fundingDiv);

                        const $coverageDiv = $('<div>');
                        const $coverageInput = $(`<input type="number" class="entryCoverage" value="${currentEntry.coverage}" disabled>`)
                        $coverageDiv.append($coverageInput);
                        $coverageDiv.append($('<span>%</span>'))
                        $entryDiv.append($coverageDiv);

                        const $selfDiv = $('<div>');
                        const $selfInput = $(`<input type="number" class="entrySelf" id="timetable.tasks${i}.budgetEntryList${entryCount}.selfFunding}" name="timetable.tasks[${i}].budgetEntryList[${entryCount}].selfFunding" value="${currentEntry.selfFunding}" disabled>`)
                        $selfDiv.append($selfInput);
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

    }
    updateTable();

    const $budgetTableButton = $('.leftMenuItem[data-section="budget"]');
    $budgetTableButton.on('click', () => updateTable());

    const $inputsInTable = $('#budgetTableDiv');
    $inputsInTable.on('change', 'input', () => updateTable());

})