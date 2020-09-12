$(() => {

//    adding events on menu buttons
    const $menuItems = $('.leftMenuItem');
    const $sections = $('.section');

    const addEventsOnMenuItems = () => {
        $menuItems.on('click', function () {
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
    $addPartnerBtn = $('#addPartnerButton');
    $addPartnerBtn.on('click', function (event) {
        event.preventDefault();
        let $partner = $('<div class="partner">');
        $partner.append($('<h3>Partner</h3>'))

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
    const $removeButtons = $('.removeButton');
    $removeButtons.on('click', function (event) {
        event.preventDefault();
        $divToDelete = this.closest('.partner');
        $divToDelete.remove();
    });


})