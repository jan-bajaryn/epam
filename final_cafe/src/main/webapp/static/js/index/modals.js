$('.myBtn').click(function () {
    $(this).next().show();
});
$('.close').click(function () {
    $('.modal').hide();
});