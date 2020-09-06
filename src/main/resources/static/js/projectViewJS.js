$(() => {
    const $menuItem = $('.leftMenuItem');

    const addEventsOnMenuItems = () => {
        $menuItem.on('click', function () {
            $menuItem.removeClass("active");
            $(this).addClass("active");
        })
    };
    addEventsOnMenuItems();
})