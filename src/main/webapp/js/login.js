$(function(){

    // 单击登陆按钮
    $("#btnLogin").click(function(){
        $.ajax({
            url:"/user/login",
            data:$("#loginForm").serialize(),
            success:function(data){
                if(data.code!=0){
                    $("#loginForm").find(".message").text(data.message).css("color","red");
                }else{
                    // 跳转到首页
                    location.href="/index";
                }
            }
        });
    });

    //  重新输入的时候 清空错误消息
    $("#loginForm input").focus(function(){
        $("#loginForm").find(".message").text("");
    })
})