<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home </a>
    <div class="panel-body">
        <div class="page-header">
            <h1>Events</h1>
        </div>
        <table class="table">
            <a href="?command=addEvent"><span class="glyphicon glyphicon-plus"></span> Create new event </a>
            <thead>
            <tr>
                <th>Suspended</th>
                <th>Date of creation</th>
                <th>Date of beginning</th>
                <th>Tournament</th>
                <th>Participants</th>
                <th>Margin</th>
                <th>Max win</th>
                <th>Status</th>
                <th>Bets</th>
                <th>Edit</th>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${event.isSuspended}">
                                <a class="btn btn-danger" href="?command=activateEvent&eventId=${event.id}">Suspend</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-danger" href="?command=suspendedEvent&eventId=${event.id}">Active</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${event.creationDateAndTime}</td>
                    <td>${event.tournament.beginningDateAndTime}</td>
                    <td><c:forEach items="${event.tournament.participants}" var="participant">
                        <div>${participant}</div>
                    </c:forEach>
                    </td>
                    <td>${event.margin}</td>
                    <td>${event.maxWin}</td>
                    <td>${event.status}</td>
                    <td><span class="badge">${event.numberOfBets}</span></td>
                    <td>
                        <a href="?command=eventEdit&id=${event.id}"><span class="glyphicon glyphicon-pencil"></span> Edit</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../fragments/footer.jspf" %>
</body>
</html>
