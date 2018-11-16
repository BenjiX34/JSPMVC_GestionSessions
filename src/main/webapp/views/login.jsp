<%-- 
    Document   : view
    Created on : 14 nov. 2018, 13:22:27
    Author     : pedago
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Jeu de la juste somme</title>
    </head>
    <body>
        <h1>Bienvenue dans notre jeu !</h1>
        <hr>
        <h2>${nbPlayers} <c:choose>
                            <c:when test="${nbPlayers<=1}">
                                joueur connecté
                            </c:when>
                            <c:otherwise>
                                joueurs connectés
                            </c:otherwise>
                        </c:choose></h2>
        <hr>
        <form method="POST">
		<label>Ton pseudo : <input name="playerName"></label>
		<input name="action" value="Connexion" type="SUBMIT">
	</form>
    </body>
</html>
