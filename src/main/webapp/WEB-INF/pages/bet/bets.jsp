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
                <th><fmt:message key="outcome" bundle="${bundle}"/></th>
                <th><fmt:message key="coefficient" bundle="${bundle}"/></th>
                <th><fmt:message key="result" bundle="${bundle}"/></th>
                <th><fmt:message key="total_coefficient" bundle="${bundle}"/></th>
                <th><fmt:message key="sum_in" bundle="${bundle}"/></th>
                <th><fmt:message key="sum_out" bundle="${bundle}"/></th>
                <th><fmt:message key="bets" bundle="${bundle}"/>
                    <fmt:message key="result" bundle="${bundle}"/></th>
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
                    <td><c:out value="${bet.date}"/></td>
                    <td><c:out value="${bet.type}"/></td>
                    <td><c:forEach items="${bet.items}" var="item">
                        <div><c:out value="${item.name}"/></div>
                    </c:forEach></td>
                    <td><c:forEach items="${bet.items}" var="item">
                        <div><c:out value="${item.coefficient}"/></div>
                    </c:forEach></td>
                    <td><c:forEach items="${bet.items}" var="item">
                        <div><c:out value="${item.result}"/></div>
                    </c:forEach></td>
                    <td><c:out value="${bet.totalCoefficient}"/></td>
                    <td><c:out value="${bet.sumIn}"/></td>
                    <td><c:out value="${bet.sumOut}"/></td>
                    <td><c:out value="${bet.result}"/></td>
                    <c:if test="${user.userRole!=clientRole}">
                        <td>
                            <form action="?command=editBetDescription&id=${bet.id}" method="POST">
                                <textarea name="description" id="description" cols="50" rows="2"><c:out
                                        value="${bet.description}"/></textarea>
                                <button class="btn btn-info"><fmt:message key="save" bundle="${bundle}"/></button>

                            </form>
                        </td>
                    </c:if>
                    <c:if test="${user.userRole==riskControllerRole}">
                        <td><a class="btn btn-dangerous" href="?command=cancelBet&betId=${bet.id}">
                            <fmt:message key="cancel" bundle="${bundle}"/>
                        </a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
