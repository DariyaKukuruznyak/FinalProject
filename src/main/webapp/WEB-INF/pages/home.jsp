<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <div class="row">
        <c:if test="${not empty activeEvents}">
            <div class="col-sm-2">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h2>All events</h2><span class="badge">${fn:length(activeEvents)}</span>
                    </div>
                    <div class="panel-body">
                        <div><a>Select all</a></div>
                        <div><a>Clear selection</a></div>
                        <%@include file="fragments/additional/homepage/activeEvents.jspf" %>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-info">
                    <div class="panel-heading">Selected events</div>
                    <div class="panel-body">
                        <%@include file="fragments/additional/homepage/selectedEvents.jspf" %>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        Basket
                    </div>
                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${not empty selectedOutcomes}">
                                <%@include file="fragments/additional/homepage/basket.jspf" %>
                            </c:when>
                            <c:otherwise><h2>Basket is empty</h2></c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<script>
    function selectCoefficient(outcomeId) {
        console.log("HI!");
//        var basket = document.getElementById('basket');
//        basket.appendChild(document.createTextNode("<button>'Click me '" + outcomeId + "</button>"));
    }
</script>
<%@include file="fragments/footer.jspf" %>
</body>
</html>