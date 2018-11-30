<%--
  Created by IntelliJ IDEA.
  User: SouLTree
  Date: 2018-10-21
  Time: 오전 3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/include/import.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>로그인페이지</h1>
<form action="/internal/login" method="post">

<%--
USERNAME : <input id="email" name="email" type="text" size="10" value="user1@test.com"/>
<br/>
PW : <input id="password" name="password" type="password" size="10" value="pass1"/>
<br/>
--%>

USERNAME : <input id="email" name="email" type="text" size="20" value="admin@test.com"/>
<br/>
PW : <input id="password" name="password" type="password" size="20" value="admin"/>
<br/>
<c:if test="${not empty sessionScope.message}">
    <br/>
    <span style="color:red">
        <fmt:message key="${sessionScope.message}"/>
    </span>
    <c:remove var="message" scope="session" />
    <br/>
    <br/>
</c:if>
<input type="submit" value="로그인"/>
</form>
<%--<button onclick="fnLogin();">로그인</button>--%>
<br/>
<br/>

<!--
<a href="javascript:;" class="btn_social" data-social="facebook"><img src="/resources/images/facebook.png" width="40px" height="40px"/></a>
<a href="javascript:;" class="btn_social" data-social="google"><img src="/resources/images/google.png" width="40px" height="40px"/></a>
-->

<script>

    /*
    $('.btn_social').click(function () {
        var socialType = $(this).data('social');
        location.href="/oauth2/authorization/"+socialType;
    });
    */

</script>
</body>
</html>
