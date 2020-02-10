$(function(){

    //  单击图片
    $("#captchaImage").click(function(){

        var d = new Date();
        //  切换验证码--->动态修改 img元素的 src 属性
        $(this).attr("src","/code/generateCode?time="+d.getTime());
    });

    // 光标离开用户名文本框
    $("#username").blur(function(){

        // 异步验证用户名是否已存在
        $.ajax({

            url:'/user/checkUsername',
            data: {username:$("#username").val()},
            success:function(data){
                // 判断验证结果  true表示已存在， false 表示不存在
                if(data.code==0){
                    $("#username").siblings(".message").text("用户名已存在").css("color","red");
                }
            }

        })
    });


    // 进入form中的文本框清空错误消息
    $("form input").focus(function(){
        // 清空消息
        $(this).siblings(".message").text("");
    });

    // 单击注册按钮
    $("#btnRegister").click(function(){
        // 异步验证验证码是否正确是否已存在
        $.ajax({

            url:'/user/checkVerifyCode',
            data: {verifyCode:$("#captcha").val()},
            success:function(data){
                // 判断验证结果  true表示输入正确，false表示输入错误
                if(!data){
                    $("#captcha").siblings(".message").text("验证码错误").css("color","red");
                }else{ // 如果没有问题，就提交整个表单的数据

                    var email = $("#email").val();
                    // 手动提交表单
                    $.ajax({
                        url:'/user/register',
                        data: $("#registerForm").serialize(),
                        success:function(data){

                            // 激活邮件已经发送成功，提示用户去完成激活
                            if(data){
                                location.href="/user/registerResult?email="+email;
                            }
                        }
                    });
                }
            }

        })

    })
})