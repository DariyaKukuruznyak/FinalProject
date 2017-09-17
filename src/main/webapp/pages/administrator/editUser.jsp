<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-heading">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/user/list" class="panel-title">
            <div class="glyphicon glyphicon-arrow-left"></div>
            Go back</a>
    </div>
    <div class="panel-body">
        <div class="page-header"><h1>User ${operationWithUser}</h1></div>
        <h2 style="color: red">${sessionScope.errorMessage}</h2>
        <sf:form modelAttribute="user" method="POST">
            <fieldset class="form-group">
                <label for="role">Role</label>
                <input class="form-control" id="role" name="role" value="${user.userRole}" required>
            </fieldset>
            <fieldset class="form-group">
                <label for="firstName">First name</label>
                <input class="form-control" id="firstName" name="firstName" value="${user.firstName}"
                       required>
            </fieldset>
            <fieldset class=" form-group">
                <label for="lastName">Last name</label>
                <input id="lastName" class="form-control" name="lastName"
                       value="${user.lastName}" required>
            </fieldset>
            <fieldset class=" form-group">
                <label for="email">Email</label>
                <input class="form-control" id="email" name="email"
                       value="${user.email}" required>
            </fieldset>
            <fieldset class=" form-group">
                <label for="dateOfRegistration">Date of registration</label>
                <input type="date" class="form-control" id="dateOfRegistration" name="dateOfRegistration"
                       value="${user.dateOfRegistration}" required>
            </fieldset>
            <fieldset class=" form-group">
                <button class="btn btn-lg btn-primary btn-block" type="submit">OK</button>
            </fieldset>
        </sf:form>
    </div>
</div>
