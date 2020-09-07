$(() => {
    const $menuItem = $('.leftMenuItem');
    const $sections = $('.section');

    const addEventsOnMenuItems = () => {
        $menuItem.on('click', function () {
            $menuItem.removeClass("active");
            $(this).addClass("active");
            $sections.addClass('hidden');
            const sectionName = $(this).data('section');
            const $section = $('#'+sectionName);
            $section.removeClass('hidden');
        })
    };
    addEventsOnMenuItems();
})