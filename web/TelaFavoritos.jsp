<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : TelaFavoritos
    Created on : 24/05/2014, 12:45:31
    Author     : Filipe_2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <ul>
    <c:forEach items="${favoritos}" var="favorito">
        <li>oi</li>
    </c:forEach>
    </ul>
    </body>
</html>
