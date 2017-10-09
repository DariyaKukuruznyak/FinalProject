<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div id="container" class="container">

    <div class="row">
        <c:if test="${not empty activeEvents}">
            <div class="col-sm-2">
                <div class="panel panel-info">
                    <div class="panel-heading"><h3>All events <span class="badge">${fn:length(activeEvents)}</span></h3></div>
                    <div class="panel-body">
                        <div><a>Select all</a></div>
                        <div><a>Clear selection</a></div>
                        <%@include file="fragments/additional/homepage/activeEvents.jspf" %>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-info">
                    <div class="panel-heading"><h3><span class="glyphicon glyphicon-list-alt"></span> Selected events</h3></div>
                    <div class="panel-body">
                        <%@include file="fragments/additional/homepage/selectedEvents.jspf" %>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                       <h3><span class="glyphicon glyphicon-briefcase"></span> Basket</h3>
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
                                <h3>Basket is empty</h3>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>