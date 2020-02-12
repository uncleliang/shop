<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="../网上商城/index.htm">
                <img src="/image/r___________renleipic_01/logo.gif" alt="NIIT"/>
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
        </div>	</div>
    <div class="span10 last">
        <div class="topNav clearfix">
            <ul>
                <li id="headerLogin" class="headerLogin" style="display: list-item;">
                    <c:if test="${USER!=null}">
                        <a href="#">${USER.username}</a>|
                    </c:if>
                    <c:if test="${USER == null}">
                        <a href="/user/goLogin">登录</a>|
                    </c:if>

                </li>
                <li id="headerRegister" class="headerRegister" style="display: list-item;">
                    <a href="/user/goRegister">注册</a>|
                </li>
                <li id="headerUsername" class="headerUsername"></li>
                <li id="headerLogout" class="headerLogout">
                    <a>[退出]</a>|
                </li>
                <li>
                    <a>会员中心</a>
                    |
                </li>
                <li>
                    <a>购物指南</a>
                    |
                </li>
                <li>
                    <a>关于我们</a>

                </li>
            </ul>
        </div>
        <div class="cart">
            <a  href="/product/showCart">购物车</a>
        </div>
        <div class="phone">
            客服热线:
            <strong>88888/88888888</strong>
        </div>
    </div>
    <div class="span24">
        <ul class="mainNav">
            <li> <a href="/index">首页</a>|  </li>
            <c:forEach items="${Menus}" var="m">
                <li>
                    <a href="/category/goCategory/${m.cid}/0/1">${m.cname}</a>|
                </li>
            </c:forEach>
        </ul>
    </div>
</div>

