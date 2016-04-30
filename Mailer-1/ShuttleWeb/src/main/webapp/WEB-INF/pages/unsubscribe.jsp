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
    <header class="demo-header android-header mdl-layout__header mdl-layout__header--waterfall">
        <div class="mdl-layout-icon"></div>
        <div class="mdl-layout__header-row">
          <span class="android-title mdl-layout-title">
              <img class="android-logo-image" src="http://moneyview.in/img/logos/white.png" alt="MoneyView Mailer">
          </span>
            <div class="android-header-spacer mdl-layout-spacer">
            </div>
            <!-- Navigation -->
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="demo-mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">Money View - Personal Finance Manager </h2>
            </div>
            <div class="mdl-card__supporting-text">
                Enter your email address to unsubscribe
            </div>
            <span id="unsub_span" style="color:darkred;display:none">Email cannot be empty</span>
            <div class="mdl-card__actions mdl-card--border">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input type="text" class="mdl-textfield__input" id="unsub_email" name="email" />
                    <label class="mdl-textfield__label" for="unsub_email">Email Address</label>
                </div>
                <button type="button" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" onclick="unsubscribe()">
                    Unsubscribe
                </button>
            </div>
        </div>
        </main>
</div>

<script>
    function unsubscribe(){
       email =  document.getElementById("unsub_email").value;
        if(email!=null || email!=''){
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
                    alert(xmlHttp.responseText);
                }
            }
            xmlHttp.open("POST", "/unsubscribe/ajax", true);
            xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlHttp.setRequestHeader("Content-Encoding", "gzip");
            xmlHttp.send("userId="+'<%=request.getParameter("userId")%>'+"&mailId="+'<%=request.getParameter("mailId")%>'+"&email="+email);
        } else {
            document.getElementById("unsub_span").style.display="block";
        }
    }
</script>

</body>
</html>
