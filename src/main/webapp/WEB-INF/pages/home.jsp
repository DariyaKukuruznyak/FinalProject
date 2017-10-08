<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <div class="row">
        <c:if test="${not empty activeEvents}">
            <div class="col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h2>All events</h2><span class="badge">${fn:length(activeEvents)}</span>
                        <div><a>Select all</a></div>
                        <div><a>Clear selection</a></div>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">
                            <c:forEach items="${activeEvents}" var="event">
                                <li class="list-group-item">
                                    <a style="text-decoration:none" href=""><input type="checkbox" id="${event.id}"
                                                                                   value="coding">
                                        <label for="${event.id}">${event.tournament.fullName}</label></a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="panel panel-info">
                    <div class="panel-body">
                        <c:forEach items="${selectedEvents}" var="event">
                            <div class="panel panel-primary">
                                <div class="panel-heading">${event.tournament.fullName}
                                        ${event.tournament.beginningDateAndTime}
                                </div>
                                <div class="panel-body">
                                    <c:forEach items="${event.markets}" var="market">
                                        <div class="panel panel-info">
                                            <div class="panel-heading"> ${market.name}</div>
                                            <table class="panel-body">
                                                <tbody>
                                                <c:forEach items="${market.outcomes}" var="outcome">
                                                    <tr>
                                                        <td class="list-group-item">${outcome.name}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${event.suspended}">
                                                                    <a class="list-group-item list-group-item-danger">
                                                                        <fmt:formatNumber value="${outcome.coefficient}"
                                                                                          maxFractionDigits="2"/></a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a class="list-group-item list-group-item-success">
                                                                        <fmt:formatNumber value="${outcome.coefficient}"
                                                                                          maxFractionDigits="2"/></a>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>