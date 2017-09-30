<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-heading">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/user/add">
            <div class="glyphicon glyphicon-plus"></div>
            Add new user</a>
    </div>
    <div class="panel-body">
        <div class="page-header">
            <h1>All users</h1>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Role</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Login</th>
                <th>Date of the registration</th>
                <th>Info</th>
                <th>Edit</th>
                <th>Remove</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${staff}" var="user">
                <tr>
                    <td>${user.userRole}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.login}</td>
                    <td>${user.dateOfRegistration}</td>
                    <td>
                        <a class="btn btn-info"
                           href="${pageContext.request.contextPath}/user/info/${user.id}">
                            <div class="glyphicon glyphicon-info-sign"></div>
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-success"
                           href="${pageContext.request.contextPath}/user/edit/${user.id}">
                            <div class="glyphicon glyphicon-pencil"></div>
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="${pageContext.request.contextPath}/user/delete/${user.login}">
                            <div class="glyphicon glyphicon-trash"></div>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

