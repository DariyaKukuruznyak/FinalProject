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
            <h1>All bets</h1>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Date</th>
                <th>Type</th>
                <th>Market</th>
                <th>Outcome</th>
                <th>Coefficient</th>
                <th>Total coefficient</th>
                <th>Sum in</th>
                <th>Sum out</th>
                <th>Result</th>
                <c:if test="${user.userRole!=clientRole}">
                    <th>Description</th>
                </c:if>
                <c:if test="${user.userRole==riskControllerRole}">
                    <th>Cancel</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bets}" var="bet">
                <tr>
                    <td>${bet.date}</td>
                    <td>${bet.type}</td>
                    <td><c:forEach items="${bet.outcomes}" var="outcome">
                        <div>${outcome.market}</div>
                    </c:forEach></td>
                    <td><c:forEach items="${bet.outcomes}" var="outcome">
                        <div>${outcome.name}</div>
                    </c:forEach></td>
                    <td><c:forEach items="${bet.outcomes}" var="outcome">
                        <div>${outcome.coefficient}</div>
                    </c:forEach></td>
                    <td>${bet.totalCoefficient}</td>
                    <td>${bet.sumIn}</td>
                    <td>${bet.sumOut}</td>
                    <td>${bet.result}</td>
                    <c:if test="${user.userRole!=clientRole}">
                        <td><textarea name="betDescription" cols="100" rows="3"></textarea>
                            <a href="?command=editBetDescription&betId=${bet.id}">Save</a></td>
                    </c:if>
                    <c:if test="${user.userRole==riskControllerRole}">
                        <td><a class="btn btn-dangerous"
                               href="?command=cancelBet&betId=${bet.id}">
                            Cancel
                        </a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="../fragments/footer.jspf" %>
</body>
</html>
