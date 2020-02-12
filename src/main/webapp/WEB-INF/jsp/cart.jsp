<%--
  Created by IntelliJ IDEA.
  User: hanliang
  Date: 2020/2/12
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>购物车</title>

    <link href="/css/common.css" rel="stylesheet" type="text/css">
    <link href="/css/cart.css" rel="stylesheet" type="text/css">


</head>
<body>
<jsp:include page="common/head.jsp"/>

<div class="container cart">
    <div class="span24">
        <div class="step step1">

        </div>
        <table>
            <tbody><tr>
                <th>图片</th>
                <th>商品</th>
                <th>价格</th>
                <th>数量</th>
                <th>小计</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${cart.cartMap.values()}" var="cartItem">
                <tr>
                    <td width="60">
                        <input type="hidden" name="id" value="22">
                        <img src="/${cartItem.product.image}">
                    </td>
                    <td>
                        <a target="_blank"> ${cartItem.product.pname}</a>
                    </td>
                    <td>
                        ￥${cartItem.product.shopPrice}
                    </td>
                    <td class="quantity" width="60">
                        ￥${cartItem.quantity}
                    </td>
                    <td width="140">
                        <span class="subtotal">￥${cartItem.subTotal}</span>
                    </td>
                    <td>
                        <a href="javascript:;" class="delete">删除</a>
                    </td>
                </tr>

            </c:forEach>

            </tbody></table>
        <dl id="giftItems" class="hidden" style="display: none;">
        </dl>
        <div class="total">
            <em id="promotion"></em>
            <em>
                登录后确认是否享有优惠
            </em>
            赠送积分: <em id="effectivePoint">${cart.total}</em>
            商品金额: <strong id="effectivePrice">￥${cart.total}元</strong>
        </div>
        <div class="bottom">
            <a href="javascript:;" id="clear" class="clear">清空购物车</a>
            <a href="./会员登录.htm" id="submit" class="submit">提交订单</a>
        </div>
    </div>
</div>

<jsp:include page="common/footer.jsp"/>
</body></html>
