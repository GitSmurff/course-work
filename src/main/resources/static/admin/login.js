$(document).ready(function () {
    $("#adminLogin").click(function (e) {
        e.preventDefault();
        var username = $("#username").val();
        var password = $("#password").val();

        var data = JSON.stringify({
            "username": username,
            "password": password
        });

        $.ajax({
            type: "POST",
            url: "/app/auth/admin/login",
            contentType: "application/json; charset=utf-8",
            async: true,
            data: data,
            success: function (xhr, status, error) {
                window.location.href = '';
            },
            error: function (xhr, status, error) {
                alert("Error login or password!")
                window.location.href = '/app/auth/admin/login/page';
            }
        });
    });
});