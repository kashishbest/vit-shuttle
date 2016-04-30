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
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <%@include file="html/header.html" %>
    <main class="mdl-layout__content">
        <div class="demo-container mdl-grid">
            <div class="mdl-cell mdl-cell--1-col mdl-cell--hide-tablet mdl-cell--hide-phone"></div>
            <div class="demo-content mdl-color--white mdl-shadow--4dp content mdl-color-text--grey-800 mdl-cell mdl-cell--10-col">
                <h3>Mail Campaign Stats</h3>
                <table id="campaignDetails" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                    <thead>
                        <th class="mdl-data-table__cell--non-numeric">Campaign Name</th>
                        <th class="mdl-data-table__cell--non-numeric">Campaign Creative</th>
                        <th>Total Sent</th>
                        <th>Delivery cfmd.</th>
                        <th>Opens</th>
                        <th>Bounces</th>
                        <th>Unsubscribes</th>
                    </thead>
                    <tbody>
                        <td class="mdl-data-table__cell--non-numeric">${mail.campaign}</td>
                        <td class="mdl-data-table__cell--non-numeric">${mail.creative}</td>
                        <td>${totalSent}</td>
                        <td>${deliveryConfirmed}</td>
                        <td><fmt:formatNumber value="${opens/(deliveryConfirmed>0?deliveryConfirmed:100)*100}" maxFractionDigits="3"/>%<br/>(${opens})</td>
                        <td><fmt:formatNumber value="${bounces/(deliveryConfirmed>0?deliveryConfirmed:100)*100}" maxFractionDigits="3"/>%<br/>(${bounces})</td>
                        <td><fmt:formatNumber value="${unsubscribeAtCurrentMail/(deliveryConfirmed>0?deliveryConfirmed:100)*100}" maxFractionDigits="3"/>%<br/>(${unsubscribeAtCurrentMail})</td>
                    </tbody>
                </table>

                <h3>Link Stats</h3>

                <table id="linkDetails" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                    <thead>
                    <th class="mdl-data-table__cell--non-numeric">Link Address</th>
                    <th class="mdl-data-table__cell--non-numeric">Clicks</th>
                    <th>Starting Index</th>
                    </thead>
                    <tbody>
                    <s:iterator value="clickMap" var="clickMapElement">
                        <s:set var="link" value="#clickMapElement.key"/>
                        <s:set var="clicks" value="#clickMapElement.value"/>
                        <tr>
                            <td class="mdl-data-table__cell--non-numeric">${link.link}</td>
                            <td>fmt:formatNumber value="${clicks/(deliveryConfirmed>0?deliveryConfirmed:100)*100}" maxFractionDigits="3"/> % (${clicks})</td>
                            <td>${link.startingIndex}</td>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>

                <h3>Browser Stats</h3>

                <h4>Opens</h4>

                <table id="openBrowser" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                    <thead>
                    <th class="mdl-data-table__cell--non-numeric">User Agent</th>
                    <th>Count</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>

                <h4>Clicks</h4>

                <table id="clickBrowser" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                    <thead>
                    <th class="mdl-data-table__cell--non-numeric">User Agent</th>
                    <th>Count</th>
                    </thead>
                    <tbody>
                    </tbody>
                </table>

            </div>
        </div>
    </main>

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

    function getOpenBrowserStats(){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var obj = eval ("(" + xmlHttp.responseText + ")");
                html = "";
                for (var key in obj)
                    html +="<tr><td class=\"mdl-data-table__cell--non-numeric\">"+key +"</td><td>"+ obj[key]+"</td></tr>";
                document.getElementById("openBrowser").getElementsByTagName("tbody")[0].innerHTML = html;
                var table3 = $('#openBrowser').DataTable({
                    "scrollX": true,
                    "order": [[ 1, "desc" ]]
                });
            }
        }
        xmlHttp.open("POST", "/campaignDetails/ajax/open", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.setRequestHeader("Content-Encoding", "gzip");
        xmlHttp.send("campaignId="+'${mail.id}');
    }

    function getClickBrowserStats(){
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                var obj = eval ("(" + xmlHttp.responseText + ")");
                html = "";
                for (var key in obj)
                    html +="<tr><td class=\"mdl-data-table__cell--non-numeric\">"+key +"</td><td>"+ obj[key]+"</td></tr>";
                document.getElementById("clickBrowser").getElementsByTagName("tbody")[0].innerHTML = html;
                var table4 = $('#clickBrowser').DataTable({
                    "scrollX": true,
                    "order": [[ 1, "desc" ]]
                });
            }

        }
        xmlHttp.open("POST", "/campaignDetails/ajax/click", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.setRequestHeader("Content-Encoding", "gzip");
        xmlHttp.send("campaignId="+'${mail.id}');
    }

    addOnloadEvent(function() {
        var table1 = $('#campaignDetails').DataTable({
            "scrollX": true
        });
        var table2 = $('#linkDetails').DataTable({
            "scrollX": true,
            "order": [[ 1, "desc" ]]
        });
        getClickBrowserStats();
        getOpenBrowserStats();

    });


</script>

</body>
</html>

