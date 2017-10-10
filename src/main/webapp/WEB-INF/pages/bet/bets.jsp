<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span><fmt:message key="back_home" bundle="${bundle}"/></a>
    <div class="panel-body">
        <div class="page-header">
            <h1><fmt:message key="bets" bundle="${bundle}"/></h1>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th><fmt:message key="date" bundle="${bundle}"/></th>
                <th><fmt:message key="type" bundle="${bundle}"/></th>
                <th><fmt:message key="market" bundle="${bundle}"/></th>
                <th><fmt:message key="outcome" bundle="${bundle}"/></th>
                <th><fmt:message key="coefficient" bundle="${bundle}"/></th>
                <th><fmt:message key="total_coefficient" bundle="${bundle}"/></th>
                <th><fmt:message key="sum_in" bundle="${bundle}"/></th>
                <th><fmt:message key="sum_out" bundle="${bundle}"/></th>
                <th><fmt:message key="result" bundle="${bundle}"/></th>
                <c:if test="${user.userRole!=clientRole}">
                    <th><fmt:message key="description" bundle="${bundle}"/></th>
                </c:if>
                <c:if test="${user.userRole==riskControllerRole}">
                    <th><fmt:message key="cancel" bundle="${bundle}"/></th>
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
                            <a href="?command=editBetDescription&betId=${bet.id}"><fmt:message key="save" bundle="${bundle}"/></a></td>
                    </c:if>
                    <c:if test="${user.userRole==riskControllerRole}">
                        <td><a class="btn btn-dangerous"
                               href="?command=cancelBet&betId=${bet.id}">
                            <fmt:message key="cancel" bundle="${bundle}"/>
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
