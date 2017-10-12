<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> <fmt:message key="back_home" bundle="${bundle}"/></a>
    <div class="panel-body">
        <div class="page-header">
            <h1><fmt:message key="users" bundle="${bundle}"/></h1>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="first_name" bundle="${bundle}"/></th>
                <th><fmt:message key="last_name" bundle="${bundle}"/></th>
                <th><fmt:message key="user_email" bundle="${bundle}"/></th>
                <th><fmt:message key="user_login" bundle="${bundle}"/></th>
                <th><fmt:message key="date_of_registration" bundle="${bundle}"/></th>
                <th><fmt:message key="role" bundle="${bundle}"/></th>
                <th><fmt:message key="edit" bundle="${bundle}"/></th>
                <th><fmt:message key="delete" bundle="${bundle}"/></th>
            <tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="userItem">
                <c:if test="${userItem.id!=user.id}">
                    <tr>
                        <td><c:out value="${userItem.firstName}"/></td>
                        <td><c:out value="${userItem.lastName}"/></td>
                        <td><c:out value="${userItem.email}"/></td>
                        <td><c:out value="${userItem.login}"/></td>
                        <td><c:out value="${userItem.dateOfRegistration}"/></td>
                        <td><c:out value="${userItem.stringUserRole}"/></td>
                        <td><a href="?command=userProfile&id=${userItem.id}"><span class="glyphicon glyphicon-pencil"></span>
                            <fmt:message key="edit" bundle="${bundle}"/>
                        </a></td>
                        <td><a href="?command=deleteUser&id=${userItem.id}"><span class="glyphicon glyphicon-trash"></span>
                            <fmt:message key="delete" bundle="${bundle}"/></a></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../fragments/footer.jspf" %>
</body>
</html>
