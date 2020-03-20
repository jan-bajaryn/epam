$('.minus').click(function () {
    let a = $(this).nextAll().filter('.id').first().textContent;
    console.log(a);
    console.log(window.location.origin + "/minus");
    $.get(window.location.origin + "/minus", {
        product_id: a
    }, function (data) {
        alert(data);
    })
});