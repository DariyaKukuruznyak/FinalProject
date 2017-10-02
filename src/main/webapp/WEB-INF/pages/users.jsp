<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home </a>
    <div class="panel-body">
        <div class="page-header">
            <h1>All users</h1>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Login</th>
                <th>Date of registration</th>
                <th>Role</th>
                <th>Edit</th>
                <th>Delete</th>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.login}</td>
                    <td>${user.dateOfRegistration}</td>
                    <td>${user.stringUserRole}</td>
                    <td><a href="?command=profile&id=${user.id}"><span class="glyphicon glyphicon-pencil"></span> Edit </a></td>
                    <td><a href="?command=deleteUser&id=${user.id}"><span class="glyphicon glyphicon-trash"></span> Delete </a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="fragments/footer.jspf" %>
</body>
</html>
