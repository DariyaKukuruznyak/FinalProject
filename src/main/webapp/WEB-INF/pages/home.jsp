<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">HOME
    <%--<c:choose>--%>
        <%--<c:when test="${ empty countries}">--%>
            <%--<h2>Unfortunately there are no active events now</h2>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
            <%--<div>--%>
                <%--<c:forEach items="${countries}" var="country">--%>
                    <%--<ul>--%>
                        <%--<li>${country.name} <span class="badge">${country.tournamentNumber}</span>--%>
                            <%--<ul><c:forEach items="${country.tournaments}" var="event">--%>
                                <%--<li>--%>
                                    <%--<a href="?command=event&id=${event.id}">--%>
                                            <%--${event.name}--%>
                                    <%--</a>--%>
                                <%--</li>--%>
                            <%--</c:forEach>--%>
                            <%--</ul>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</c:forEach>--%>
            <%--</div>--%>
            <%--<div class="event-details">--%>
                <%--<div>${event.tournament.country.name} - ${event.tournament.name}</div>--%>
                <%--<div>${event.tournament.beginningDateAndTime} </div>--%>
                <%--<c:forEach items="${event.markets}" var="market">--%>
                    <%--${market.name}--%>
                    <%--<c:forEach items="${market.outcomes}" var="outcome">--%>
                        <%--${outcome.name}--%>
                        <%--<a name="outcome" class="btn-success"--%>
                           <%--href="?command=checkOutcomeIn&id=${outcome.id}&coefficient=${outcome.coefficient}">${outcome.coefficient}</a>--%>
                    <%--</c:forEach>--%>
                <%--</c:forEach>--%>
            <%--</div>--%>
            <%--<div class="basket">--%>
                <%--<form class="form-horizontal" action="" method="post">--%>
                    <%--<select size="1">--%>
                        <%--<c:forEach items="${typeOfBet}" var="type">--%>
                            <%--<option>${type}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                    <%--<div>Outcome</div>--%>
                    <%--<div>Coefficient</div>--%>
                    <%--<div class="form-group row">--%>
                        <%--<label for="sum" class="col-sm-4 col-form-label">Sum of the bet</label>--%>
                        <%--<div class="col-sm-8">--%>
                            <%--<input type="sum" name="sum" id="sum"--%>
                                   <%--class="form-control"--%>
                                   <%--placeholder="Sum" required pattern="[0-9]+(\.[0-9]+)?" maxlength="10"--%>
                                   <%--title="Expected sum in format 'digits' or 'digits.digits'">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<input type="submit" value="Bet">--%>
                <%--</form>--%>
            <%--</div>--%>
        <%--</c:otherwise>--%>
    <%--</c:choose>--%>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>