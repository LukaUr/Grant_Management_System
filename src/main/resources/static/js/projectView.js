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
        $partner.append($(`<input type="text" id="applicant.partners[${countPartners}].id" name="applicant.partners[${countPartners}].id" hidden>`))
        let $label = $('<label>');
        $label.append('<h3> Partner name</h3>');
        $label.append(`<textarea id="applicant.partners[${countPartners}].name" name="applicant.partners[${countPartners}].name" cols="100" rows="5"></textarea>`);
        $partner.append($label);
        $addPartnerBtn.before($partner);
        countPartners++;
    });


})