$(document).ready(function () {
    $("#adminLogout").click(function (e) {
        e.preventDefault();
        $.ajax({
            url: '/app/auth/logout',
            success: function () {
                window.location.href = "/app/auth/admin/login/page";
            }
        });
    });
});