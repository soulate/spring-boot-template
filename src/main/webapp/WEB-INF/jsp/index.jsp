<%--
  Created by IntelliJ IDEA.
  User: SouLTree
  Date: 2018-09-21
  Time: 오전 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/include/import.jsp"%>
<html>
<head>
    <title>Title</title>
    <script>


        /*var url = 'http://local.wcg.com:7070/internal/sample/list?test=&name=가';

        //GET
        axios.get('/internal/sample/list?test=&name=가',{ params: '' })
        .then(function (response) {
            console.log("success :: ",response.data);
        })
        .catch(function (error) {
            console.log("error msg :: ", error.response.data);
        });*/

        /*
        $.ajax({
            type : "POST",
            url : "/sample/list",
            dataType : "json",
            data : {

            },
            async: true,
            beforeSend:function () {

            },
            success: function(data) {
                console.log(JSON.stringify(data));

            },
            error: function(data) {
                console.log(JSON.stringify(data.responseJSON));
            },
            complete: function() {

            }
        });

        $.ajax({
            type : "POST",
            url : "/sample/list2",
            dataType : "json",
            data : {

            },
            async: true,
            beforeSend:function () {

            },
            success: function(data) {
                console.log(JSON.stringify(data));

            },
            error: function(data) {
                console.log(JSON.stringify(data.responseJSON));
            },
            complete: function() {

            }
        });
        */

        /*
        $.ajax({
            type : "POST",
            url : "/sample/insert",
            dataType : "json",
            data : {

            },
            async: true,
            beforeSend:function () {

            },
            success: function(data) {
                console.log(JSON.stringify(data));

            },
            error: function(data) {
                console.log(JSON.stringify(data.responseJSON));
            },
            complete: function() {

            }
        });
        */

    </script>
</head>
<body>
Welcome index.jsp
<br/>
<c:out value="${msg}" />
<h1><spring:message code="welcome.message" arguments="홍길동,김철수"/></h1>
<spring:message code="field.required.msg"/>
<div class="sample"></div>
<br/>

<sec:authorize access="isAnonymous()">
    <a href="<c:url value="/login" />">로그인</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">

    <ul>
        <sec:authorize access="hasAuthority('ADMIN')">
            <li>관리자 ${name} 님 안녕하세요.</li>
        </sec:authorize>
        <sec:authorize access="hasAuthority('USER')">
            <li>${name} 님 안녕하세요.</li>
        </sec:authorize>
    </ul>

    <a href="<c:url value="/logout" />">로그아웃</a>

</sec:authorize>

<br/>
<br/>
<script>
    Common.sample();
</script>
</body>
</html>
