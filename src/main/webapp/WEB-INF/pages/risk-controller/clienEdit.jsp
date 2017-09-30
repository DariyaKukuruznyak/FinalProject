<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-heading">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/client/list" class="panel-title">
            <div class="glyphicon glyphicon-arrow-left"></div>
            Go back</a>
    </div>
    <div class="panel-body">
        <sf:form modelAttribute="client" method="POST">
            <fieldset class="form-group">
                <label for="fullName">Full name</label>
                <input class="form-control" id="fullName" value="${client.fullName}" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="email">Email</label>
                <input class="form-control" id="email" value="${client.email}" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="login">Login</label>
                <input id="login" class="form-control" value="${client.login}" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="dateOfRegistration">Date of registration</label>
                <input type="date" class="form-control" id="dateOfRegistration" value="${client.dateOfRegistration}"
                       required>
            </fieldset>
            <fieldset class="form-group">
                <label for="maxBet">Max bet</label>
                <input id="maxBet" class="form-control" value="${client.maxBet}" required>
            </fieldset>
            <fieldset class="form-group">
                <label for="balance">Balance</label>
                <input id="balance" class="form-control" value="${client.balance}" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="description">description</label>
                <input id="description" class="form-control" value="${client.description}">
            </fieldset>
            <fieldset class=" form-group">
                <button class="btn btn-lg btn-primary btn-block" type="submit">OK</button>
            </fieldset>
        </sf:form>
    </div>
</div>
