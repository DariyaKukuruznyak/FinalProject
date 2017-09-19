<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-body">
        <h1>Bets</h1>
        <table class="table">
            <thead>
            <tr>
                <th>Client</th>
                <th>Sum in</th>
                <th>Sum out</th>
                <th>Type</th>
                <th>Result</th>
                <th>Outcomes</th>
                <th>Description</th>
                <th>Cancel</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${bets}" var="bet">
                <tr>
                    <td>${bet.client}</td>
                    <td>${bet.sumIn}</td>
                    <td>${bet.sumOut}</td>
                    <td>${bet.type}</td>
                    <td>${bet.result}</td>
                    <td>
                        <c:forEach items="${bet.outcomes}" var="outcome">
                            ${outcome}
                        </c:forEach></td>
                    <td>${bet.description}</td>
                    <td><a class="btn btn-danger"
                           href="${pageContext.request.contextPath}/client/bet/cancel/${bet.id}">Cancel</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
