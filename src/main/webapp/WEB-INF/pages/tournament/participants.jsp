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
            <h1><fmt:message key="participants" bundle="${bundle}"/></h1>
        </div>
        <h3><a href="?command=addParticipant"><span class="glyphicon glyphicon-plus"></span> <fmt:message
                key="create_participant" bundle="${bundle}"/></a>
        </h3>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="name" bundle="${bundle}"/></th>
                <th><fmt:message key="age" bundle="${bundle}"/></th>
                <th><fmt:message key="weight" bundle="${bundle}"/></th>
                <th><fmt:message key="trainer" bundle="${bundle}"/></th>
                <th><fmt:message key="jockey" bundle="${bundle}"/></th>
                <th><fmt:message key="tournaments" bundle="${bundle}"/></th>
                <th><fmt:message key="edit" bundle="${bundle}"/></th>
                <th><fmt:message key="delete" bundle="${bundle}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${participants}" var="participant">
                <tr>
                    <td<c:out value='>${participant.name}'/></td>
                    <td><c:out value='${participant.age}'/></td>
                    <td><c:out value='${participant.weight}'/></td>
                    <td><c:out value='${participant.trainer}'/></td>
                    <td><c:out value='${participant.jockey}'/></td>
                    <td>
                        <c:forEach items="${participant.tournaments}" var="tournament">
                            <div><c:out value='${tournament.name}'/></div>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="?command=editParticipant&participantId=${participant.id}"><span
                                class="glyphicon glyphicon-pencil"></span>
                            <fmt:message key="edit" bundle="${bundle}"/></a>
                    </td>
                    <td>
                        <a href="?command=deleteParticipant&participantId=${participant.id}"><span
                                class="glyphicon glyphicon-trash"></span>
                            <fmt:message key="delete" bundle="${bundle}"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../fragments/footer.jspf" %>
</body>
</html>
