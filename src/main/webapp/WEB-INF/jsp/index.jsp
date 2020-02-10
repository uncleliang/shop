<%--
  Created by IntelliJ IDEA.
  User: hanliang
  Date: 2020/2/7
  Time: 3:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>NIIT商城</title>
    <link href="../css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="../css/common.css" rel="stylesheet" type="text/css"/>
    <link href="../css/index.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<!--引入共同头部-->
<jsp:include page="common/head.jsp"/>

<div class="container index">
    <div class="span24">
        <div id="hotProduct" class="hotProduct clearfix">
            <div class="title">
                <strong>热门商品</strong>
            </div>
            <ul class="tab">
                <li class="current">
                    <a href="../蔬菜分类.htm?tagIds=1" target="_blank"></a>
                </li>
                <li>
                    <a  target="_blank"></a>
                </li>
                <li>
                    <a target="_blank"></a>
                </li>
            </ul>
            <ul class="tabContent" style="display: block;">
               <c:forEach items="${hotProducts}" var="p">
                   <li>
                       <a  target="_blank"><img src="${p.image}" style="display: block;"></a>
                   </li>
               </c:forEach>
            </ul>

        </div>
    </div>
    <div class="span24">
        <div id="newProduct" class="newProduct clearfix">
            <div class="title">
                <strong>最新商品</strong>
                <a  target="_blank"></a>
            </div>
            <ul class="tab">
                <li class="current">
                    <a href="../蔬菜分类.htm?tagIds=2" target="_blank"></a>
                </li>
                <li>
                    <a  target="_blank"></a>
                </li>
                <li>
                    <a target="_blank"></a>
                </li>
            </ul>
            <ul class="tabContent" style="display: block;">

            </ul>

        </div>
    </div>
    <div class="span24">
        <div class="friendLink">
            <dl>
                <dt>新手指南</dt>
                <dd>
                    <a  target="_blank">支付方式</a>
                    |
                </dd>
                <dd>
                    <a  target="_blank">配送方式</a>
                    |
                </dd>
                <dd>
                    <a  target="_blank">售后服务</a>
                    |
                </dd>
                <dd>
                    <a  target="_blank">购物帮助</a>
                    |
                </dd>
                <dd>
                    <a  target="_blank">蔬菜卡</a>
                    |
                </dd>
                <dd>
                    <a  target="_blank">礼品卡</a>
                    |
                </dd>
                <dd>
                    <a target="_blank">银联卡</a>
                    |
                </dd>
                <dd>
                    <a  target="_blank">亿家卡</a>
                    |
                </dd>

                <dd class="more">
                    <a >更多</a>
                </dd>
            </dl>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script src="/js/index.js"></script>
</body></html>