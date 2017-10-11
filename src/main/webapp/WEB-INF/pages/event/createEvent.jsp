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
            <div class="alert alert-danger">${errorMessage}</div>
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
                                    <th><fmt:message key="country" bundle="${bundle}"/></th>
                                    <th><fmt:message key="date_of_beginning" bundle="${bundle}"/></th>
                                    <th>Select</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tournaments}" var="tournament">
                                    <tr>
                                        <td>${tournament.name}</td>
                                        <td>${tournament.country}</td>
                                        <td>${tournament.beginningDateAndTime}</td>
                                        <td>
                                            <a href="?command=selectTournament&tournamentId=${tournament.id}"><span
                                                    class="glyphicon glyphicon-ok"></span> <fmt:message key="select"
                                                                                                        bundle="${bundle}"/></a>
                                        </td>
                                    </tr>
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
                                    <div id="name">${selectedTournament.name}</div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="country" class="col-sm-4 col-form-label"><fmt:message key="country"
                                                                                                  bundle="${bundle}"/></label>
                                <div class="col-sm-8">
                                    <div id="country">${selectedTournament.country}</div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="beginningDateAndTime" class="col-sm-4 col-form-label"><fmt:message
                                        key="date_of_beginning" bundle="${bundle}"/></label>
                                <div class="col-sm-8">
                                    <div id="beginningDateAndTime">${selectedTournament.beginningDateAndTime}</div>
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
                                        <td>${participant.name}</td>
                                        <td>${participant.age}</td>
                                        <td>${participant.weight}</td>
                                        <td>${participant.trainer}</td>
                                        <td>${participant.jockey}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-danger"> <fmt:message key="no_active_tournaments" bundle="${bundle}"/></div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>