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
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <div class="row">
            <div class="col-sm-6">
                <h2 class="form-signin-heading"><fmt:message key="edit_tournament" bundle="${bundle}"/></h2>
                <form class="form-horizontal" action="?command=updateTournament&tournamentId=${tournament.id}"
                      method="POST">
                    <fieldset class="form-group">
                        <label for="name"><fmt:message key="name" bundle="${bundle}"/></label>
                        <input class="form-control" id="name" name="name"
                               pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                               title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20"
                               placeholder="<fmt:message key="name" bundle="${bundle}"/>" required
                               value="<c:out value='${tournament.name}'/>">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="winner"><fmt:message key="winner" bundle="${bundle}"/></label>
                        <select class="form-control" id="winner" name="winner" size="1">
                            <c:choose>
                                <c:when test="${empty tournament.participants}">
                                    <option selected></option>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${not empty tournament.winner}">
                                        <option selected><c:out value='${tournament.winner}'/></option>
                                    </c:if>
                                    <c:forEach items="${tournament.participants}" var="participant">
                                        <option><c:out value='${participant.name}'/></option>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </fieldset>
                    <fieldset class="form-group">
                        <button class="btn btn-lg btn-primary btn-block"><fmt:message key="save"
                                                                                      bundle="${bundle}"/></button>
                    </fieldset>
                    <c:if test="${not empty tournament.participants}">
                        <fieldset class="form-group">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th><fmt:message key="name" bundle="${bundle}"/></th>
                                    <th><fmt:message key="trainer" bundle="${bundle}"/>/<fmt:message key="jockey"
                                                                                                     bundle="${bundle}"/></th>
                                    <th><fmt:message key="exclude" bundle="${bundle}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tournament.participants}" var="participant">
                                    <tr>
                                        <td><c:out value='${participant.name}'/></td>
                                        <td><c:out value='${participant.trainer}'/>/<c:out
                                                value='${participant.jockey}'/></td>
                                        <td>
                                            <a class="btn btn-info"
                                               href="?command=moveParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=tournament&action=exclude">
                                                <span class="glyphicon glyphicon-minus"></span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </fieldset>
                    </c:if>
                </form>
            </div>
            <div class="col-sm-4">
                <div class="panel panel-primary">
                    <div class="panel-heading"><fmt:message key="include_participant_to_tournament"
                                                            bundle="${bundle}"/></div>
                    <c:if test="${not empty participants}">
                        <table class="table">
                            <thead>
                            <tr>
                                <th><fmt:message key="name" bundle="${bundle}"/></th>
                                <th><fmt:message key="trainer" bundle="${bundle}"/>/<fmt:message key="jockey"
                                                                                                 bundle="${bundle}"/></th>
                                <th><fmt:message key="exclude" bundle="${bundle}"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${participants}" var="participant">
                                <tr>
                                <tr>
                                    <td><c:out value='${participant.name}'/></td>
                                    <td><c:out value='${participant.trainer}'/>/<c:out
                                            value='${participant.jockey}'/></td>
                                    <td><a class="btn btn-info"
                                           href="?command=moveParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=tournament&action=include">
                                        <span class="glyphicon glyphicon-plus"></span></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>