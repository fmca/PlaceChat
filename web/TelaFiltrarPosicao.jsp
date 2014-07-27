<%-- 
    Document   : TelaFiltrarPosicao
    Created on : 24/05/2014, 12:45:16
    Author     : Filipe_2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="http://maps.googleapis.com/maps/api/js?libraries=places"></script>
        <script src="js/jquery.geocomplete.min.js"></script>
        <title>Escolha um lugar</title>
    </head>
    <body>



        <form action="resultados" method="get">
            <ul style="list-style: none">
                <li>
                    <label>O que estão falando em </label>
                    <input type="radio" name="location" class="mylocation_fields" id="mylocation_radio" >Minha posição</input>
                    <input type="radio" name="location" id="endereco_radio"><input type="text" placeholder="Endereço (Ex.: Maracanã)" id="endereco"></input></input>
                    <input hidden="hidden" id="lat" name="lat" style="width:90px; font-style:italic; " placeholder="lat"/>
                    <input hidden="hidden" id="lon" name="lon" style="width:90px; font-style:italic; " placeholder="lon" />
                </li>
                <li>
                    <label>Num raio de </label>
                    <input id="raio" name="raio" style="width:90px; font-style:italic; " placeholder="2 km">

                </li>
                <li>
                    <input type="submit" value="Pesquisar"></input>
                </li>
            </ul>
        </form>

    </body>


    <script>

        var mylocation = {};
        var endereco = {};

        $(".mylocation_fields").hide();
        navigator.geolocation.getCurrentPosition(GetLocation);
        function GetLocation(location) {

            $(".mylocation_fields").show();
            mylocation.latitude = location.coords.latitude;
            mylocation.longitude = location.coords.longitude;

        }

 $("#endereco_radio").click();
 
        $("#endereco")
        .geocomplete()
        .bind("geocode:result", function(event, result) {

            endereco.latitude = result.geometry.location.k;
            endereco.longitude = result.geometry.location.B;
            if($("#endereco_radio").is(":checked")){
                
                $("#endereco_radio").click();
            }
            
        });

        $("#mylocation_radio").on("click", function() {
            $("#lat").attr('value', '' + mylocation.latitude);
            $("#lon").attr('value', '' + mylocation.longitude);
        });
        $("#endereco_radio").on("click", function() {
            $("#lat").attr('value', '' + endereco.latitude);
            $("#lon").attr('value', '' + endereco.longitude);
        });
        
    </script>
</html>
