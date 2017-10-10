<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home">
        <span class="glyphicon glyphicon-home"></span> <fmt:message key="back_home" bundle="${bundle}"/></a>
    <div class="panel-body">
        <div class="page-header">
            <h1><fmt:message key="events" bundle="${bundle}"/></h1>
        </div>
        <h3><a href="?command=addEvent"><span class="glyphicon glyphicon-plus"></span>
            <fmt:message key="create_new_event" bundle="${bundle}"/></a></h3>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="status" bundle="${bundle}"/></th>
                <th><fmt:message key="action" bundle="${bundle}"/></th>
                <th><fmt:message key="date_of_creation" bundle="${bundle}"/></th>
                <th><fmt:message key="date_of_beginning" bundle="${bundle}"/></th>
                <th><fmt:message key="tournament" bundle="${bundle}"/></th>
                <th><fmt:message key="bets" bundle="${bundle}"/></th>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${event.status == eventStatusInprogress}">
                                <c:choose>
                                    <c:when test="${event.isSuspended}">
                                        <a class="btn btn-danger" href="?command=activateEvent&eventId=${event.id}">
                                            <fmt:message key="suspended" bundle="${bundle}"/></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-success"
                                           href="?command=suspendedEvent&eventId=${event.id}">
                                            <fmt:message key="active" bundle="${bundle}"/></a>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                <div>${event.status}</div>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="?command=editEvent&eventId=${event.id}"><span
                                class="glyphicon glyphicon-pencil"></span>
                            <fmt:message key="edit" bundle="${bundle}"/></a>
                    </td>
                    <td>${event.creationDateAndTime}</td>
                    <td>${event.tournament.beginningDateAndTime}</td>
                    <td>${event.tournament.fullName}</td>
                    <td>
                        <a href="?command=showBetsOfEvent&eventId=${event.id}"><fmt:message key="bets"
                                                                                            bundle="${bundle}"/>
                            <span class="badge">${event.numberOfBets}</span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../fragments/footer.jspf" %>
</body>
</html>

