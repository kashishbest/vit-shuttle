<%--
  Created by IntelliJ IDEA.
  User: kashishsinghal
  Date: 02/11/15
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp" %>

<html>
<head>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <title>Verify Mail</title>
    <t:assets type="css"/>
    <t:assets type="js"/>
    <%= (request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : "" %>
    <style>
        #view-source {
            position: fixed;
            display: block;
            right: 0;
            bottom: 0;
            margin-right: 40px;
            margin-bottom: 80px;
            z-index: 900;
        }
    </style>
</head>
<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <%@include file="html/header.html"%>
    <main class="mdl-layout__content">
        <c:if test="${verified == true}">
            Mail has been verified. Job would be created and will be send out soon to the mailing list
        </c:if>
    </main>
</div>


</body>
</html>
