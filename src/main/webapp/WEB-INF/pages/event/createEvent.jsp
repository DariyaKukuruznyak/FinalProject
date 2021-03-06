<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> <fmt:message key="back_home" bundle="${bundle}"/></a>
    <div class="panel-body">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger"><c:out value="${errorMessage}"/></div>
        </c:if>
        <c:if test="${not empty selectedTournament}">
            <a href="?command=createEvent" class="btn btn-success"><h2>
                <fmt:message key="create_event" bundle="${bundle}"/></h2></a>
        </c:if>
        <c:choose>
            <c:when test="${not empty tournaments}">
                <div class="row">
                    <div class="col-sm-6">
                        <h2 class="form-signin-heading"><fmt:message key="select_tournament" bundle="${bundle}"/></h2>
                        <c:if test="${not empty tournaments}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><fmt:message key="name" bundle="${bundle}"/></th>
                                    <th><fmt:message key="date_of_beginning" bundle="${bundle}"/></th>
                                    <th><fmt:message key="select" bundle="${bundle}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tournaments}" var="tournament">
                                    <c:if test="${not empty tournament.participants}">
                                        <tr>
                                            <td><c:out value="${tournament.name}"/></td>
                                            <td><c:out value="${tournament.beginningDate}"/></td>
                                            <td>
                                                <a href="?command=selectTournament&tournamentId=${tournament.id}">
                                                    <span class="glyphicon glyphicon-ok"></span> <fmt:message
                                                        key="select"
                                                        bundle="${bundle}"/></a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                    <div class="col-sm-6">
                        <c:if test="${not empty selectedTournament}">
                            <h2 class="form-signin-heading"><fmt:message key="selection" bundle="${bundle}"/></h2>
                            <h3 class="form-signin-heading"><fmt:message key="tournament" bundle="${bundle}"/></h3>
                            <div class="form-group row">
                                <label for="name" class="col-sm-4 col-form-label">Name</label>
                                <div class="col-sm-8">
                                    <div id="name"><c:out value="${selectedTournament.name}"/></div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="beginningDateAndTime" class="col-sm-4 col-form-label"><fmt:message
                                        key="date_of_beginning" bundle="${bundle}"/></label>
                                <div class="col-sm-8">
                                    <div id="beginningDateAndTime"><c:out
                                            value="${selectedTournament.beginningDate}"/></div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="participantsNumber" class="col-sm-4 col-form-label"><fmt:message
                                        key="number_of_participant" bundle="${bundle}"/></label>
                                <div class="col-sm-8">
                                    <div id="participantsNumber">${fn:length(selectedTournament.participants)}</div>
                                </div>
                            </div>
                            <h3 class="form-signin-heading"><fmt:message key="participants" bundle="${bundle}"/></h3>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><fmt:message key="name" bundle="${bundle}"/></th>
                                    <th><fmt:message key="age" bundle="${bundle}"/></th>
                                    <th><fmt:message key="weight" bundle="${bundle}"/></th>
                                    <th><fmt:message key="trainer" bundle="${bundle}"/></th>
                                    <th><fmt:message key="jockey" bundle="${bundle}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${selectedTournament.participants}" var="participant">
                                    <tr>
                                        <td><c:out value="${participant.name}"/></td>
                                        <td><c:out value="${participant.age}"/></td>
                                        <td><c:out value="${participant.weight}"/></td>
                                        <td><c:out value="${participant.trainer}"/></td>
                                        <td><c:out value="${participant.jockey}"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger"><fmt:message key="no_active_tournaments" bundle="${bundle}"/></div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>