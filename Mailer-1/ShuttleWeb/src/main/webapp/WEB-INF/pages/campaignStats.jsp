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
    <title>Android</title>
    <t:assets type="css"/>
    <t:assets type="js"/>
    <%= (request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : "" %>
    <script src="/scripts/datatables/jquery.js"></script>
    <script src="/scripts/datatables/jquery.dataTables.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
</head>
<body>
<div class="col-sm-12">
    <div class="col-sm-4"></div>
    <div class="col-sm-6">
        <h3>Mail Campaign STATS</h3>
    </div>
</div>
<div class="col-sm-12">
    <div class="col-sm-9">
        <table id="campaignDetails">
            <thead>
            <th>Campaign Name</th>
            <th>Campaign Creative</th>
            <th>Total Sent</th>
            <th>Opens</th>
            <th>Unsubscribe At Current Mailer</th>
            </thead>
            <tbody>
            <td>${mail.campaign}</td>
            <td>${mail.creative}</td>
            <td>Unavailable</td>
            <td>${opens}</td>
            <td>${unsubscribeAtCurrentMail}</td>
            </tbody>
        </table>
    </div>
</div>
<div class="col-sm-12">
    <div class="col-sm-4"></div>
    <div class="col-sm-6">
        <h3>Links STATS</h3>
    </div>
</div>
<div class="col-sm-12">
    <div class="col-sm-9">
        <table id="linkDetails">
            <thead>
            <th>Link Address</th>
            <th>Starting Index</th>
            <th>Clicks</th>
            </thead>
            <tbody>
            <s:iterator value="clickMap" var="clickMapElement">
                <s:set var="link" value="#clickMapElement.key"/>
                <s:set var="clicks" value="#clickMapElement.value"/>
                <tr>
                    <td>${link.link}</td>
                    <td>${link.startingIndex}</td>
                    <td>${clicks}</td>
                </tr>
            </s:iterator>
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
        var table1 = $('#campaignDetails').DataTable({
            "scrollX": true
        });
        var table2 = $('#linkDetails').DataTable({
            "scrollX": true
        });

    });
    linkDetails
</script>

</body>
</html>

