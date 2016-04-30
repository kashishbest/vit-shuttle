<%--
  Created by IntelliJ IDEA.
  User: bhargavsarvepalli
  Date: 27/10/15
  Time: 12:03 AM
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <title>Android</title>
    <t:assets type="css"/>
    <t:assets type="js"/>
    <%= (request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : "" %>
    <%--<style>--%>
        <%--#view-source {--%>
            <%--position: fixed;--%>
            <%--display: block;--%>
            <%--right: 0;--%>
            <%--bottom: 0;--%>
            <%--margin-right: 40px;--%>
            <%--margin-bottom: 80px;--%>
            <%--z-index: 900;--%>
        <%--}--%>
    <%--</style>--%>
</head>
<body>
<div class="col-sm-12">
    <div class="col-sm-4"></div>
    <div class="col-sm-5">
        <h3>Mail Campaign List</h3>
    </div>
</div>
<div class="col-sm-12">
    <div class="col-sm-9">
        <table id="campaignList">
            <thead>
            <th>Campaign Name</th>
            <th>Campaign Creative</th>
            <th>Option</th>
            </thead>
            <tbody>
            <c:forEach items="${mailList}" var="mailerCampaign">
                <tr>
                    <s:form action="campaignDetails">
                        <td nowrap="nowrap">${mailerCampaign.campaign}</td>
                        <td nowrap="nowrap" >${mailerCampaign.creative}</td>
                        <input type="hidden" name="campaignId" id="campaignId" key="campaignId" value="${mailerCampaign.id}">
                        <td nowrap="nowrap" >
                            <s:submit type="button" cssClass="btn btn-primary" method="fetchMailJobDetails" key="button.get"
                                      theme="simple">
                                <i class="icon-ok icon-white"></i>
                                VIEW
                            </s:submit>
                        </td>
                    </s:form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<script>
    function addOnloadEvent(fnc){
        if ( typeof window.addEventListener != "undefined" )
            window.addEventListener( "load", fnc, false );
        else if ( typeof window.attachEvent != "undefined" ) {
            window.attachEvent( "onload", fnc );
        }
        else {
            if ( window.onload != null ) {
                var oldOnload = window.onload;
                window.onload = function ( e ) {
                    oldOnload( e );
                    window[fnc]();
                };
            }
            else
                window.onload = fnc;
        }
    }

    addOnloadEvent(function() {
        var table = $('#campaignList').DataTable({
            "scrollX": true
        });

    });
</script>

</body>
</html>

