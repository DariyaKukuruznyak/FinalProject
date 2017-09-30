<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="icon" href="images/icon.jpg"/>
    <title>RaceBet</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <%--<link rel="stylesheet" href="css/style.css">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <%--<script type="text/javascript" src="js/registerValidation.js"></script>--%>
</head>

<body>
<header>
    <div class="container">
        <a class="navbar-brand" href="">RACE BET</a>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/register"><span class="glyphicon glyphicon-user"></span> Join Now</a></li>
            <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
        </ul>
    </div>
    <a class="overlay" id="login"></a>
    <div class="popup">
        <a class="close" title="Close" href=""></a>
        <%--<c:import url="pages/login.jsp"/>--%>
    </div>
    <a class="overlay" id="register"></a>
    <div class="popup">
        <a class="close" title="Close" href=""></a>
        <%--<c:import url="pages/register.jsp"/>--%>
    </div>
</header>
<div class="container">

</div>
<footer>
    <hr>
    <p>&copy; RaceBet 2017</p>
</footer>
</body>

</html>