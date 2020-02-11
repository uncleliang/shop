$(function(){


    $("#increase").click(function () {
        var q = parseInt($("#quantity").val());
        q++;
        $("#quantity").val(q);
    });

    $("#decrease").click(function () {
        var q = parseInt($("#quantity").val());
        q--;
        if(q<=0){
            q=1;
        }
        $("#quantity").val(q);
    })
})