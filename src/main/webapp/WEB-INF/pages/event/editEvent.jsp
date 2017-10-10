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
        <h2 class="form-signin-heading"><fmt:message key="event" bundle="${bundle}"/> #${event.id}</h2>
        <div class="row">
            <div class="col-sm-6">
                <fieldset class="form-group">
                    <label for="creationDateAndTime"><fmt:message key="date_of_creation" bundle="${bundle}"/></label>
                    <div class="form-control" id="creationDateAndTime">${event.creationDateAndTime}</div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="beginningDateAndTime"><fmt:message key="date_of_beginning" bundle="${bundle}"/></label>
                    <div class="form-control" id="beginningDateAndTime">${event.tournament.beginningDateAndTime}</div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="tournament"><fmt:message key="tournament" bundle="${bundle}"/></label>
                    <div class="form-control" id="tournament">${event.tournament.fullName}</div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="winner"><fmt:message key="winner" bundle="${bundle}"/></label>
                    <div class="form-control" id="winner">${event.tournament.winner}</div>
                </fieldset>
                <fieldset>
                    <label for="status"><fmt:message key="status" bundle="${bundle}"/></label>
                    <div class="form-control" id="status">${event.status}</div>
                </fieldset>
                <fieldset>
                    <c:choose>
                        <c:when test="${event.status==lockedStatus}">
                            <a class="btn btn-lg btn-success btn-block"
                               href="?command=changeStatus&status=inprogress&eventId=${event.id}">
                                <fmt:message key="run_to_inprogress" bundle="${bundle}"/></a>
                        </c:when>
                        <c:when test="${event.status==inprogressStatus}">
                            <a class="btn btn-lg btn-danger btn-block"
                               href="?command=changeStatus&status=locked&eventId=${event.id}">
                                <fmt:message key="locked" bundle="${bundle}"/></a>
                            <c:if test="not empty event.tournament.winner">
                                <a class="btn btn-lg btn-success btn-block"
                                   href="?command=changeStatus&status=finished&eventId=${event.id}"><fmt:message
                                        key="finished" bundle="${bundle}"/></a>
                            </c:if>
                        </c:when>
                    </c:choose>
                </fieldset>
            </div>
            <div class="col-sm-6">
                <fieldset>
                    <c:choose>
                        <c:when test="${event.suspended}">
                            <a class="btn btn-lg btn-danger btn-block"
                               href="?command=changeStatus&status=activate&eventId=${event.id}">
                                <fmt:message key="suspended" bundle="${bundle}"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-lg btn-success btn-block"
                               href="?command=changeStatus&status=suspend&eventId=${event.id}"><fmt:message
                                    key="activate" bundle="${bundle}"/></a>
                        </c:otherwise>
                    </c:choose>
                </fieldset>
                <c:if test="${not empty event.markets}">
                <c:forEach items="${event.markets}" var="market">
                <fieldset class="panel panel-primary">
                    <div class="panel-heading">
                        <div>${market.name}</div>
                    </div>
                    <div><fmt:message key="margin" bundle="${bundle}"/>: <fmt:formatNumber value="${market.margin}"
                                                                                           maxFractionDigits="2"/>%
                    </div>
                    <c:forEach items="${market.outcomes}" var="outcome">
                        <form class="form-horizontal" action="?command=applyCoefficient&outcomeId=${outcome.id}"
                              method="POST">
                            <table>
                                <tbody>
                                <tr>
                                    <td>${outcome.name}</td>
                                    <td><input name="coefficient" id="coefficient" class="form-control"
                                               placeholder="Coefficient" required
                                               pattern="[1-9][0-9]*(\.[0-9]{1,2}?" maxlength="6"
                                               title="Expected integer or double. Max length = 6"
                                               value="${outcome.coefficient}">
                                    </td>
                                    <td>
                                        <button class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>
                                            <fmt:message key="apply" bundle="${bundle}"/>
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </form>
                    </c:forEach>
                </fieldset>
            </div>
            </c:forEach>
            </c:if>
        </div>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>