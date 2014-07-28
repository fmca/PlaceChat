<%-- 
    Document   : TelaResultados
    Created on : 24/05/2014, 12:45:05
    Author     : Filipe_2
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="com.fuzuapp.model.resultados.entidades.Resultado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("newLineChar", "\n");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
        <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="http://maps.googleapis.com/maps/api/js?libraries=places"></script>
        <script src="js/jquery.geocomplete.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Resultados</title>
    </head>
    <body>
        <div class="menu shad">

            <form action="resultados" method="get">
                <input id="lat" name="lat" style="width:90px; font-style:italic; " placeholder="lat" >
                <input id="lon" name="lon" style="width:90px; font-style:italic; " placeholder="lon" >
                <input id="raio" name="raio" style="width:90px; font-style:italic; " placeholder="raio km" >
                <input type="submit" value="Pesquisar" />
            </form>

        </div>

        <div id="mapa-pesquisa" style="width: 30%; height: 50em; float: right">

            <input type="text" style="float: left;" id="searchbox" placeholder="Pesquisar" width="100%"/>
            <div id="map" style="width: 100%; height: 50em;"></div>

        </div>



        <div style="float: left; width: 60%" ng-app="instantSearch" ng-controller="InstantSearchController">

            <input type="text" ng-model="searchString" placeholder="Filtre por texto ou hashtag" style="width: 100%; margin-left: 2em;"/>
            <ul id="#list">

                <li ng-repeat="i in items| searchFor:searchString">
                    <a href="{{i.url}}"> <img class = "{{i.tipo}}" src ="{{i.fotoUrl}}" title="{{i.nomeUsuario}}"/></a>
                    <span class = "horario" >{{i.horario}}</span>
                    <span class = "descricao" >{{i.descricao}}</span>
                    <button class="fav">Favoritar</button>
                </li>
                <!-- < li >  </li >-->
            </ul>

        </div>

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

                        if (item.descricao.toLowerCase().indexOf(searchString) !== -1 || ("/"+item.tipo.toLowerCase()) == (searchString) ) {
                            result.push(item);
                        }

                    });
                    return result;
                };
            });

            function InstantSearchController($scope) {

                $scope.items = [
            <c:forEach items="${resultados}" var="resultado">
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

    </div>




    <script>
        $(function (){
            $(".fav").on("click",function(){
                var urlS = $($(this).parent().find("a")[0]).attr("href");
                var nomeS = $($(this).parent().find("img")[0]).attr("title");
                var fotourlS = $($(this).parent().find("img")[0]).attr("src");
                var tipoS = $($(this).parent().find("img")[0]).attr("class");
                var horarioS = $($(this).parent().find(".horario")[0]).text().trim();
                var descricaoS = $($(this).parent().find(".descricao")[0]).text();

                $.post( "/favoritos", { nome: nomeS, url: urlS, fotourl: fotourlS, tipo: tipoS, descricao: descricaoS, horario: horarioS})
                        .done(function( data ) {
                            alert("Favoritado!");
                        });

            });
        });


        $("#searchbox").geocomplete();
        // create a map in the "map" div, set the view to a given place and zoom
        var map = L.map('map').setView([<c:out value="${latitude}"/>,<c:out value="${longitude}"/>], 13);
        // add an OpenStreetMap tile layer
        L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);
        L.circle([<c:out value="${latitude}"/>,<c:out value="${longitude}"/>], <c:out value="${raio}"/>).addTo(map);
        longitudeInput = document.getElementById("lon");
        latitudeInput = document.getElementById("lat");
        raioInput = document.getElementById("raio");
        map.on('move', function(e) {

            refresh();
        });
        function refresh() {
            var ll = map.getCenter();
            longitudeInput.value = ll.lng;
            latitudeInput.value = ll.lat;
        }

        $("#searchbox")
                .geocomplete()
                .bind("geocode:result", function(event, result) {

                    console.log(result);
                    map.panTo(new L.LatLng(result.geometry.location.k, result.geometry.location.B));
                });
        refresh();

    </script>
</body>

<style>
    ul{
        list-style: none;
    }
    body{
        margin: 0;

    }
    .menu{

        margin: 0;
        margin-bottom: 1em;
    }
    .shad {
        border-bottom: 1px solid #999;
        background-color: #fafafa;
        background-image: linear-gradient(to bottom, #fff, #e2e2e2);
        padding:10px;
        text-align: center;
        box-shadow: 0 1px 10px rgba(0, 0, 0, 0.7);
    }

    .horario{
        color: #E74C3C;
        font-size: 8px;
    }

    /* Seleciona tamanho da imagem de acordo com o tipo de resultado */

    .texto{
        width: 40px;
        height: 40px;
    }

    .video{
        width: 100px;
        height: 100px;
    }

</style>
</html>
