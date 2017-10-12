<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/homeHeader.jspf" %>
<div id="container" class="container">
    <div class="row">
        <c:if test="${not empty activeEvents}">
        <c:choose>
        <c:when test="${empty user or user.userRole==clientRole}">
        <div class="col-sm-2">
            </c:when>
            <c:otherwise>
            <div class="col-sm-4">
                </c:otherwise>
                </c:choose>
                <div class="panel panel-info">
                    <div class="panel-heading"><h3><fmt:message key="events" bundle="${bundle}"/>
                        <span class="badge">${fn:length(activeEvents)}</span></h3></div>
                    <div class="panel-body">
                        <div><a href="?command=moveAllEvents&action=include">
                            <fmt:message key="select_all" bundle="${bundle}"/></a></div>
                        <div><a href="?command=moveAllEvents&action=exclude">
                            <fmt:message key="clear_selection" bundle="${bundle}"/></a></div>
                        <%@include file="fragments/additional/homepage/activeEvents.jspf" %>
                    </div>
                </div>
            </div>
            <c:choose>
            <c:when test="${empty user or user.userRole==clientRole}">
            <div class="col-sm-6">
                </c:when>
                <c:otherwise>
                <div class="col-sm-8">
                    </c:otherwise>
                    </c:choose>
                    <div class="panel panel-info">
                        <div class="panel-heading"><h3><span class="glyphicon glyphicon-list-alt"></span>
                            <fmt:message key="selected_events" bundle="${bundle}"/></h3></div>
                        <div class="panel-body">
                            <%@include file="fragments/additional/homepage/selectedEvents.jspf" %>
                        </div>
                    </div>
                </div>
                <c:if test="${empty user or user.userRole==clientRole}">
                    <div class="col-sm-4">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3><span class="glyphicon glyphicon-briefcase"></span>
                                    <fmt:message key="basket" bundle="${bundle}"/></h3>
                            </div>
                            <div class="panel-body">
                                <c:choose>
                                    <c:when test="${not empty collectedOutcomes}">
                                        <%@include file="fragments/additional/homepage/basket.jspf" %>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${not empty successMessage}">
                                            <div class="alert alert-success">${successMessage}</div>
                                        </c:if>
                                        <h3><fmt:message key="basket_is_empty" bundle="${bundle}"/></h3>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:if>
                </c:if>
            </div>
        </div>
    </div>
</div>
</body>
</html>