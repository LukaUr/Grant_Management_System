$(() => {
    'use strict'

    // determine years to be included in budget table

    const $taskStarts = $('.taskStart');
    const taskStartsDates = $taskStarts.map(function () {
        return $(this).val()
    });
    const taskStartYears = $.map(taskStartsDates, t => parseInt(t.substr(0, 4)));
    const firstYear = Math.min(...taskStartYears);
    console.log(firstYear);

    const $taskEnds = $('.taskEnd')
    const taskEndDates = $taskEnds.map(function () {
        return $(this).val()
    });
    const taskEndYears = $.map(taskEndDates, t => parseInt(t.substr(0, 4)));
    const lastYear = Math.max(...taskEndYears);
    console.log(lastYear);
    const listOfYears = new Array();
    for (let i = firstYear; i < (lastYear + 1); i++) {
        listOfYears.push(i);
    }

    // fetch current budget data

    class BudgetTask {
        constructor(id, name, yearOfStart, yearOfEnd, entries) {
            this.id = id;
            this.name = name;
            this.yearOfStart = yearOfStart;
            this.yearOfEnd = yearOfEnd;
            this.entries = entries;
        }
    }

    class BudgetEntry {
        constructor(id, year, total, funding, selfFunding) {
            this.id = id;
            this.year = year;
            this.total = total;
            this.funding = funding;
            this.selfFunding = selfFunding;
            this.coverage = total / funding * 100;
        }
    }

    const $budgetTasks = $('#budgetTable .budgetTask');
    let budgetTaskList = new Array();
    $budgetTasks.forEach(function () {
        const $budgetEntries = $(this).find('.budgetEntry');
        let budgetEntryList = new Array();
        $budgetEntries.forEach(function () {
            const entryId = $(this).find('.entryId').val();
            const entryYear = $(this).find('.entryYear').val();
            const entryTotal = $(this).find('.entryTotal').val();
            const entryFunding = $(this).find('.entryFunding').val();
            const entrySelf = $(this).find('.entrySelf').val();
            const budgetEntry = new BudgetEntry(entryId, entryYear, entryTotal, entryFunding, entrySelf);
            budgetEntryList.push(budgetEntry);
        })
        const taskId = $(this).find('.budgetTaskId').val();
        const taskName = $(this).find('.budgetTaskName').val();
        const taskStart = $(this).find('.budgetTaskStart').val();
        const taskEnd = $(this).find('.budgetTaskEnd').val();
        const yearOfStart = +(taskStart.substr(0, 4));
        const yearOfEnd = +(taskEnd.substr(0, 4));
        const budgetTask = new BudgetTask(taskId, taskName, yearOfStart, yearOfEnd, budgetEntryList);
        budgetTaskList.push()
    })


    // clear current budget table

    const $budgetTable = $('#budgetTable');
    $budgetTable.remove();

    // build new budget table

    const $newBudgetTable = $('<table id="budgetTable">');
    const $headerTr = $('<tr>');
    $headerTr.append($('<th>No.</th>'));
    $headerTr.append($('<th>Task name</th>'));
    $headerTr.append($('<th>Value</th>'));
    for (let i in listOfYears) {
        const $header = ($('<th></th>'))
        $header.text(i);
        $headerTr.append($header);
    }

    const $budgetTableDiv = $('#budgetTableDiv');
    $budgetTableDiv.append($newBudgetTable);


});