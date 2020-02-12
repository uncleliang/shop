$(function(){


    // 数量加
    $("#increase").click(function () {
        var q = parseInt($("#quantity").val());
        q++;
        $("#quantity").val(q);
    });
    // 数量减
    $("#decrease").click(function () {
        var q = parseInt($("#quantity").val());
        q--;
        if(q<=0){
            q=1;
        }
        $("#quantity").val(q);
    });


    $("#addCart").click(function () {
        // 将数据添加到购物车
        $.ajax({

            url:'/product/addCart',
            data:{
                pid:$("#pid").val(),
                quantity:$("#quantity").val()
            },
            success:function (res) {
                // TODO 提示用户添加成功，购物车上面显示小图标数字表明当前购物车中有多少数据
                alert("添加购物车成功!")
            }

        })
    })
})