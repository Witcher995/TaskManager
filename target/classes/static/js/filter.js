

$(document).ready(function () {
    $("#clickMe").click(function () {
        if ($('.fr').prop("disabled")) {
            $('.fr').prop("disabled", false);
            $('#accept').prop("disabled", false);
        } else {
            $('.fr').prop("disabled", true);
            $('#accept').prop("disabled", true);
        }
    });
});

