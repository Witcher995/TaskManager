/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function updateStatus(id, status) {
    $.ajax({
        url: "/api/update/task/" + id + "/" + status,
        type: "POST",
        data: {id: id, status: status}}
    //error: (xhr, ajaxOptions, thrownError) => {alert(thrownError);}
    );
}

$(function () {
    $("#todo, #inprogress, #done").sortable({
        connectWith: ".connectedSortable",
        update: function (e, ui) {
            if (ui.sender !== null) {
                console.log(e);
                let id = ui.item.attr("id");
                let status = this.id;

                updateStatus(id, status);
            }
        }
    }).disableSelection();
});

$(document).ready(function () {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#todo li, #inprogress li, #done li").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) >= 0);
        });
    });
});

$(document).ready(function () {
    $("#sort").click(function () {
        var list = $('#todo'), items = list.children().get();
        list.append(items.sort(function (a, b) {
            if (b.id > a.id)
                return a.id - b.id;

            else
                return b.id - a.id;

        }));
    });
});

$(document).ready(function () {
    $("#sort1").click(function () {
        var list = $('#inprogress'), items = list.children().get();
        list.append(items.sort(function (a, b) {
            if (b.id > a.id)
                return a.id - b.id;

            else
                return b.id - a.id;

        }));
    });
});

$(document).ready(function () {
    $("#sort2").click(function () {
        var list = $('#done'), items = list.children().get();
        list.append(items.sort(function (a, b) {
            if (b.id > a.id)
                return a.id - b.id;

            else
                return b.id - a.id;

        }));
    });
});