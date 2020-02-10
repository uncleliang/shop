<%--
  Created by IntelliJ IDEA.
  User: hanliang
  Date: 2020/2/7
  Time: 10:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>会员登录</title>

    <link href="../css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<!--引入共同头部-->
<jsp:include page="common/head.jsp"/>

<div class="container login">
    <div class="span12">
        <div class="ad">
            <img src="../image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">
        </div>		</div>
    <div class="span12 last">
        <div class="wrap">
            <div class="main">
                <div class="title">
                    <strong>会员登录</strong>USER LOGIN
                </div>
                <form id="loginForm"  method="post" novalidate="novalidate">

                    <table>
                        <tbody>
                        <tr>
                            <th></th>
                            <td><span class="message"></span></td>
                        </tr>
                        <tr>
                            <th>
                                用户名/E-mail:
                            </th>
                            <td>
                                <input type="text" id="username" name="username" class="text" maxlength="20">

                            </td>
                        </tr>
                        <tr>
                            <th>
                                密&nbsp;&nbsp;码:
                            </th>
                            <td>
                                <input type="password" id="password" name="password" class="text" maxlength="20" autocomplete="off">
                            </td>
                        </tr>
                        <tr>
                            <th>
                                验证码:
                            </th>
                            <td>
										<span class="fieldSet">
											<input type="text" id="captcha" name="verifyCode" class="text captcha" maxlength="4" autocomplete="off">
                                            <img id="captchaImage" class="captchaImage" src="../code/generateCode" title="点击更换验证码">
										</span>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <label>
                                    <input type="checkbox" id="isRememberUsername" name="isRememberUsername" value="true">记住用户名
                                </label>
                                <label>
                                    &nbsp;&nbsp;<a >找回密码</a>
                                </label>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;

                            </th>
                            <td>
                                <input id="btnLogin" type="button" class="submit" value="登 录">
                            </td>
                        </tr>
                        <tr class="register">
                            <th>&nbsp;

                            </th>
                            <td>
                                <dl>
                                    <dt>还没有注册账号？</dt>
                                    <dd>
                                        立即注册即可体验在线购物！
                                        <a href="/user/goRegister">立即注册</a>
                                    </dd>
                                </dl>
                            </td>
                        </tr>
                        </tbody></table>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="/js/login.js"></script>
</body></html>