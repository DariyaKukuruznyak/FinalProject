<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home">
        <span class="glyphicon glyphicon-home"></span> Back home </a>
    <div class="panel-body">
        <div class="page-header">
            <h1>Events</h1>
        </div>
        <h3><a href="?command=addEvent"><span class="glyphicon glyphicon-plus"></span>Create new event</a></h3>
        <table class="table">
            <thead>
            <tr>
                <th>Status</th>
                <th>Action</th>
                <th>Date of creation</th>
                <th>Date of beginning</th>
                <th>Tournament</th>
                <th>Bets</th>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${event.status == eventStatusInprogress}">
                                <c:choose>
                                    <c:when test="${event.isSuspended}">
                                        <a class="btn btn-danger" href="?command=activateEvent&eventId=${event.id}">Suspended</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-success"
                                           href="?command=suspendedEvent&eventId=${event.id}">Active</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <div>${event.status}</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="?command=editEvent&eventId=${event.id}"><span class="glyphicon glyphicon-pencil"></span>Edit</a>
                    </td>
                    <td>${event.creationDateAndTime}</td>
                    <td>${event.tournament.beginningDateAndTime}</td>
                    <td>${event.tournament.fullName}</td>
                    <td>
                        <a href="?command=showBetsOfEvent&eventId=${event.id}">Bets <span
                                class="badge">${event.numberOfBets}</span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../fragments/footer.jspf" %>
</body>
</html>

