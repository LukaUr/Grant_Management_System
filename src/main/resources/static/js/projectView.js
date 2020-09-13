$(() => {

    const $mainInside = $('.mainInside');
//    adding events on menu buttons
    const $menuItems = $('.leftMenuItem');
    const $sections = $('.section');

    const addEventsOnMenuItems = () => {
        $menuItems.on('click', function() {
            $menuItems.removeClass("active");
            $(this).addClass("active");
            $sections.addClass('hidden');
            const sectionName = $(this).data('section');
            const $section = $('#' + sectionName);
            $section.removeClass('hidden');
        })
    };
    addEventsOnMenuItems();

//    adding partners
    let countPartners = $('.partner').length;
    let $addPartnerBtn = $('#addPartnerButton');
    $addPartnerBtn.on('click', function (event) {
        event.preventDefault();
        let $partner = $('<div class="partner">');
        $partner.append($('<h3>Partner</h3>'));
        $partner.append('<button class="button removeButton">Remove</button>');

        let $labelName = $('<label>');
        $labelName.append('<h3>Partner name</h3>');
        $labelName.append(`<textarea id="applicant.partners${countPartners}.name" name="applicant.partners[${countPartners}].name" cols="100" rows="5"></textarea>`);
        $partner.append($labelName);

        let $labelLegalForm = $('<label>');
        $labelLegalForm.append('<h3>Partner legal form</h3>');
        $labelLegalForm.append(`<textarea id="applicant.partners${countPartners}.legalForm" name="applicant.partners[${countPartners}].legalForm" cols="100" rows="5"></textarea>`);
        $partner.append($labelLegalForm);

        let $labelRegNumber = $('<label>');
        $labelRegNumber.append('<h3>Registration number</h3>');
        $labelRegNumber.append(`<input type="text" id="applicant.partners${countPartners}.registrationNumber" name="applicant.partners[${countPartners}].registationNumber"/>`);
        $partner.append($labelRegNumber);

        let $labelVatNumber = $('<label>');
        $labelRegNumber.append('<h3>VAT number</h3>');
        $labelRegNumber.append(`<input type="text" id="applicant.partners${countPartners}.vatNumber" name="applicant.partners[${countPartners}].VATNumber"/>`);
        $partner.append($labelRegNumber);

        let $labelBankAccount = $('<label>');
        $labelBankAccount.append('<h3>Bank account number</h3>');
        $labelBankAccount.append(`<input type="text" id="applicant.partners${countPartners}.bankAccountNumber" name="applicant.partners[${countPartners}].bankAccountNumber"/>`);
        $partner.append($labelBankAccount);

        let $labelEmail = $('<label>');
        $labelEmail.append('<h3>Contact details</h3>');
        $labelEmail.append('<h3>Email</h3>');
        $labelEmail.append(`<input type="email" id="applicant.partners${countPartners}.email" name="applicant.partners[${countPartners}].email"/>`);
        $partner.append($labelEmail);

        let $labelPhone = $('<label>');
        $labelPhone.append('<h3>Phone</h3>');
        $labelPhone.append(`<input type="number" id="applicant.partners${countPartners}.phone" name="applicant.partners[${countPartners}].phone"/>`);
        $partner.append($labelPhone);

        let $labelCountry = $('<label>');
        $labelCountry.append('<h3>Country</h3>');
        $labelCountry.append(`<input type="text" id="applicant.partners${countPartners}.country" name="applicant.partners[${countPartners}].country"/>`);
        $partner.append($labelCountry);

        let $labelPostCode = $('<label>');
        $labelPostCode.append('<h3>Post code</h3>');
        $labelPostCode.append(`<input type="text" id="applicant.partners${countPartners}.postCode" name="applicant.partners[${countPartners}].postCode"/>`);
        $partner.append($labelPostCode);

        let $labelAddress = $('<label>');
        $labelAddress.append('<h3>Address:</h3>');
        $labelAddress.append(`<input type="text" id="applicant.partners${countPartners}.addressLine1" name="applicant.partners[${countPartners}].addressLine1"/><br>`);
        $labelAddress.append(`<input type="text" id="applicant.partners${countPartners}.addressLine2" name="applicant.partners[${countPartners}].addressLine2"/>`);
        $partner.append($labelAddress);

        $addPartnerBtn.before($partner);
        countPartners++;
    });

//    removing partners
    const addEventsOnRemovePartner = () => $mainInside.on('click', '.removeButton', (event) => {
        event.preventDefault();
        $(event.target).closest('.partner').remove();
    });
    addEventsOnRemovePartner()

//    adding tasks to timetable
    let countTasks = $('.taskDiv').length;
    let $addTaskBtn = $('#addTaskButton');
    $addTaskBtn.on('click', function (event) {
        event.preventDefault();

        let $task = $('<div class="taskDiv">');
        $task.append($(`<h3>Task no ${countTasks + 1}</h3>`));
        $task.append('<button class="button removeTaskButton">Remove</button>');

        $task.append($(`<p>Task name</p>`));
        let $labelName = $('<label>');
        $labelName.append(`<input type="text" id="timetable.tasks${countTasks}.name" name="timetable.tasks[${countTasks}].name"/>`);
        $task.append($labelName);

        $task.append($(`<p>Task description</p>`));
        let $labelDescription = $('<label>');
        $labelDescription.append(`<textarea id="timetable.tasks${countTasks}.description" name="timetable.tasks[${countTasks}].description" cols="100" rows="5"></textarea>`);
        $task.append($labelDescription);

        $task.append($(`<p>Start date</p>`));
        let $labelStart = $('<label>');
        $labelStart.append(`<input type="date" id="timetable.tasks${countTasks}.taskStart" name="timetable.tasks[${countTasks}].taskStart"/>`);
        $task.append($labelStart);

        $task.append($(`<p>End date</p>`));
        let $labelEnd = $('<label>');
        $labelEnd.append(`<input type="date" id="timetable.tasks${countTasks}.taskEnd" name="timetable.tasks[${countTasks}].taskEnd"/>`);
        $task.append($labelEnd);

        $addTaskBtn.before($task)
        countTasks++;
    })

//    removing tasks from timetable
    const addEventsOnRemoveTask = () => $mainInside.on('click', '.removeTaskButton', (event) => {
        event.preventDefault();
        $(event.target).closest('.taskDiv').remove();
    });
    addEventsOnRemoveTask();

//    task date check

})