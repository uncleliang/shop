<%--
  Created by IntelliJ IDEA.
  User: hanliang
  Date: 2020/2/10
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网上商城</title>
    <link href="/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<jsp:include page="common/head.jsp"/>

<div class="container productList">
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

        <form id="productForm" action="/image/蔬菜 - Powered By Mango Team.htm" method="get">
            <input type="hidden" id="brandId" name="brandId" value="">
            <input type="hidden" id="promotionId" name="promotionId" value="">
            <input type="hidden" id="orderType" name="orderType" value="">
            <input type="hidden" id="pageNumber" name="pageNumber" value="1">
            <input type="hidden" id="pageSize" name="pageSize" value="20">

            <div id="result" class="result table clearfix">
                <ul>
                    <c:forEach items="${pList}" var="p">
                        <li>
                            <a href="/分页面.htm">
                                <img src="/${p.image}" width="170" height="170"  style="display: inline-block;">

                                <span style='color:green'> ${p.pname} </span>

                                <span class="price"> 商城价： ￥${p.shopPrice}/份 </span>

                            </a>
                        </li>
                    </c:forEach>


                </ul>
            </div>
            <div class="pagination">
                <!--判断是否是第一页-->
                <c:if test="${pageInfo.pageNum==1}">
                    <span class="firstPage">&nbsp;</span>
                    <span class="previousPage">&nbsp;</span>
                </c:if>
                <c:if test="${pageInfo.pageNum!=1}">
                    <a  class="firstPage" href="/category/goCategory/${cid}/${csid}/1">&nbsp;</a>
                    <a  class="previousPage" href="/category/goCategory/${cid}/${csid}/${pageInfo.pageNum-1}">&nbsp;</a>
                </c:if>

                <!--循环显示页码-->
                <c:forEach begin="1" end="${pageInfo.pages}" var="p">
                    <c:if test="${pageInfo.pageNum==p}">
                        <span class="currentPage">${p}</span>
                    </c:if>
                    <c:if test="${pageInfo.pageNum!=p}">
                        <a href="/category/goCategory/${cid}/${csid}/${p}">${p}</a>
                    </c:if>
                </c:forEach>
                <%--<span class="currentPage">1</span>--%>

                <!--判断是否是最后一页-->
                <c:if test="${pageInfo.pageNum==pageInfo.pages}">
                    <span class="nextPage">&nbsp;</span>
                    <span class="lastPage">&nbsp;</span>
                </c:if>

                <c:if test="${pageInfo.pageNum!=pageInfo.pages}">
                    <a  class="nextPage" href="/category/goCategory/${cid}/${csid}/${pageInfo.pageNum+1}">&nbsp;</a>
                    <a  class="lastPage" href="/category/goCategory/${cid}/${csid}/${pageInfo.pages}">&nbsp;</a>
                </c:if>
            </div>
        </form>
    </div>
</div>
<jsp:include page="common/footer.jsp"/>
</body></html>
