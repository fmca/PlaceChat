<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
    Document   : TelaFavoritos
    Created on : 24/05/2014, 12:45:31
    Author     : Filipe_2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.fuzuapp.model.resultados.entidades.Resultado"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Favoritos</title>
    </head>
    <body>
    <div style="float: left; width: 60%" ng-app="instantSearch" ng-controller="InstantSearchController">

        <h1>Favoritos de "${nome}"</h1>
        <input type="text" ng-model="searchString" placeholder="Filtre por texto ou hashtag" style="width: 100%; margin-left: 2em;"/>
        <ul >

            <li ng-repeat="i in items| searchFor:searchString">
                <a href="{{i.url}}"> <img class = "{{i.tipo}}" src ="{{i.fotoUrl}}" title="{{i.nomeUsuario}}"/></a>
                <span class = "horario" >{{i.horario}}</span>
                <span class = "descricao" >{{i.descricao}} </span>
            </li>
            <!-- < li >  </li >-->
        </ul>

    </div>
    </body>

    <script>

        var app = angular.module("instantSearch", []);

        app.filter('searchFor', function() {

            return function(arr, searchString) {

                if (!searchString) {
                    return arr;
                }

                var result = [];
                searchString = searchString.toLowerCase();
                angular.forEach(arr, function(item) {

                    if (item.descricao.toLowerCase().indexOf(searchString) !== -1) {
                        result.push(item);
                    }

                });
                return result;
            };
        });

        function InstantSearchController($scope) {

            $scope.items = [
                <c:forEach items="${favoritos}" var="resultado">
                {
                    fotoUrl: "${resultado.fotoUrl}",
                    nomeUsuario: "<c:out value='${resultado.nomeUsuario}' />",
                    horario: "${resultado.horario}",
                    tipo: "${resultado.tipo}",
                    url: "<c:out value='${resultado.url}'/>",
                    descricao: "<c:out value='${fn:replace(resultado.descricao, newLineChar, ";")}' />"
                },
                </c:forEach>
            ];
        }
    </script>
</html>
