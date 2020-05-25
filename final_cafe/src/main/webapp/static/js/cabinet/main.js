$('#change_password').click(function () {
    $('#new_password,#old_password').prop('disabled', function (i, v) {
        return !v;
    });
});