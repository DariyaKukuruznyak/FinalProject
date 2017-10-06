<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home</a>
    <div class="panel-body">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <h2 class="form-signin-heading">Event #${event.id}</h2>
        <div class="row">
            <div class="col-sm-6">
                <fieldset class="form-group">
                    <label for="creationDateAndTime">Date of creation</label>
                    <div class="form-control" id="creationDateAndTime" >${event.creationDateAndTime}</div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="tournament">Tournament</label>
                    <div class="form-control" id="tournament">${event.tournament.fullName}</div>
                </fieldset>
                <fieldset class="form-group">
                    <label for="winner">Winner</label>
                    <div class="form-control" id="winner">${event.tournament.winner}</div>
                </fieldset>
                    <label for="status">Status</label>
                <div class="form-control" id="status">${event.status}</div>
            </div>
            <div class="col-sm-6">
                <c:if test="${not empty event.markets}">
                    <c:forEach items="${event.markets}" var="market">
                        <div class="alert alert-info">${market.name}</div>
                        <h3 class="form-signin-heading">${market.margin}</h3>
                        <c:forEach items="${market.outcomes}" var="outcome">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Coefficient</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${outcome.name}</td>
                                    <td><input id="coefficient" name="coefficient" required
                                               pattern="[1-9][0-9]*(\.[0-9]{1,2}?" maxlength="6"
                                               title="Expected integer or double. Max length = 6"
                                               placeholder="Coefficient"
                                               value=">${outcome.coefficient}">
                                    </td>
                                    <td>
                                        <a href="?command=applyCoefficient&outcomeId=${outcome.id}"><span
                                                class="glyphicon glyphicon-ok"></span>apply</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </c:forEach>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>