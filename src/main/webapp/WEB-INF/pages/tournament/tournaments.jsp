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
            <h1><fmt:message key="tournaments" bundle="${bundle}"/></h1>
        </div>
        <h3><a href="?command=addTournament"><span class="glyphicon glyphicon-plus"></span> <fmt:message
                key="create_tournament" bundle="${bundle}"/></a></h3>

        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="name" bundle="${bundle}"/></th>
                <th><fmt:message key="date_of_beginning" bundle="${bundle}"/></th>
                <th><fmt:message key="participants" bundle="${bundle}"/></th>
                <th><fmt:message key="winner" bundle="${bundle}"/></th>
                <th><fmt:message key="edit" bundle="${bundle}"/></th>
                <th><fmt:message key="delete" bundle="${bundle}"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tournaments}" var="tournament">
                <tr>
                    <td><c:out value='${tournament.name}'/></td>
                    <td><c:out value='${tournament.beginningDate}'/></td>
                    <td><c:forEach items="${tournament.participants}" var="participant">
                       <div><c:out value='${participant.name}'/></div>
                    </c:forEach></td>
                    <td><c:out value='${tournament.winner}'/></td>
                    <td><a href="?command=editTournament&tournamentId=${tournament.id}"><span
                            class="glyphicon glyphicon-pencil"></span>
                        <fmt:message key="edit" bundle="${bundle}"/></a></td>
                    <td><a href="?command=deleteTournament&tournamentId=${tournament.id}"><span
                            class="glyphicon glyphicon-trash"></span>
                        <fmt:message key="delete" bundle="${bundle}"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
