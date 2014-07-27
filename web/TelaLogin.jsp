<%-- 
    Document   : TelaLogin
    Created on : 24/05/2014, 12:43:17
    Author     : Filipe_2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
    	
        <title>Login</title>
    </head>
    <body>
       
        <div id="content">

    		<div id="marcalogin"><img src="images/placechat_login.png"></div>
    		<div id="login">
    	<form action="login" method="post">
            <label for="login">Login</label>
            <input type="text" name="login" class="text" value="<%= request.getParameter("login") != null ? request.getParameter("login") : ""%>"/></br> 
             <label for="senha">Senha</label>
             <input name="senha" class="text" type="password"/> </br>
            <span class="erro"> ${erro} </span></br>
            <input type="submit" value="Entrar" class="button"/> 
          </form> 
    		</div>
    	</div>
       
        
    </body>
</html>
