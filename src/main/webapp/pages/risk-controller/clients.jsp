<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-body">
        <h1>Clients</h1>
        <table class="table">
            <thead>
            <tr>
                <th>Full name</th>
                <th>Email</th>
                <th>Login</th>
                <th>Registration</th>
                <th>Max bet</th>
                <th>Balance</th>
                <th>Description</th>
                <th>Stakes</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.fullName}</td>
                    <td>${client.email}</td>
                    <td>${client.login}</td>
                    <td>${client.dateOfRegistration}</td>
                    <td>${client.maxBet}</td>
                    <td>${client.balance}</td>
                    <td>${client.description}</td>
                    <td>
                        <a class="btn btn-info"
                           href="${pageContext.request.contextPath}/client/stakes">${client.numberOfStakes}</a>
                    </td>
                    <td>
                        <a class="btn btn-success" href="${pageContext.request.contextPath}/client/edit/${client.id}">
                            <div class="glyphicon glyphicon-pencil"></div>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
