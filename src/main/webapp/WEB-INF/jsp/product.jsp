<%--
  Created by IntelliJ IDEA.
  User: hanliang
  Date: 2020/2/11
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>网上商城</title>
    <link href="/css/common.css" rel="stylesheet" type="text/css">
    <link href="/css/product.css" rel="stylesheet" type="text/css">


</head>
<body>

<jsp:include page="common/head.jsp"/>

<div class="container productContent">
    <div class="span6">
        <div class="hotProductCategory">
            <dl>
                <c:forEach items="${csList}" var="cs">
                    <dd>
                        <a href="/category/goCategory/${cid}/${cs.csid}/1">${cs.csname}</a>
                    </dd>
                </c:forEach>
            </dl>
        </div>
    </div>
    <div class="span18 last">
        <div class="productImage">
            <a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="#" rel="gallery">
                <img style="opacity: 1;" title="" class="medium" src="/${product.image}">
            </a>
        </div>
        <div class="name">${product.pname}</div>
        <div class="sn">
            <div>编号：${product.pid}</div>
        </div>
        <div class="info">
            <dl>
                <dt>商城价:</dt>
                <dd>
                    <strong>￥：${product.shopPrice}元/份</strong>
                    参 考 价：
                    <del>￥${product.marketPrice}元/份</del>
                </dd>
            </dl>
            <dl>
                <dt>促销:</dt>
                <dd> <a target="_blank" title="限时抢购 (2020-02-11 ~ 2020-02-29)">限时抢购</a> </dd>
            </dl>
            <dl>
                <dt>    </dt>
                <dd>
                    <span>    </span>
                </dd>
            </dl>
        </div>
        <div class="action">

            <dl class="quantity">
                <dt>购买数量:</dt>
                <dd>
                    <input id="quantity" name="quantity" value="1" maxlength="4" onpaste="return false;" type="text">
                    <div>
                        <span id="increase" class="increase">&nbsp;</span>
                        <span id="decrease" class="decrease">&nbsp;</span>
                    </div>
                </dd>
                <dd>
                    件
                </dd>
            </dl>
            <div class="buy">
                <input id="addCart" class="addCart" value="加入购物车" type="button">

            </div>
        </div>

        <div id="bar" class="bar">
            <ul>
                <li id="introductionTab">
                    <a href="#introduction">商品介绍</a>
                </li>
            </ul>
        </div>

        <div id="introduction" name="introduction" class="introduction">
            <div class="title">
                <strong>${product.pdesc}</strong>
            </div>
            <div>
                <img src="/${product.image}">
            </div>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="/js/product.js"></script>
</body>
</html>
