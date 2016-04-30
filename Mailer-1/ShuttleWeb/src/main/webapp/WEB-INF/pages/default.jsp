<%--
  Created by IntelliJ IDEA.
  User: kashishsinghal
  Date: 05/11/15
  Time: 3:08 PM
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
            <div class="android-navigation-container">
                <nav class="android-navigation mdl-navigation">
                    <a class="mdl-navigation__link mdl-typography--text-uppercase" href="">Campaigns</a>
                    <a class="mdl-navigation__link mdl-typography--text-uppercase" href="">Mail Lists</a>
                    <a class="mdl-navigation__link mdl-typography--text-uppercase" href="">Running Jobs</a>
                    <a class="mdl-navigation__link mdl-typography--text-uppercase" href="">Analytics</a>
                </nav>
            </div>
        </div>
    </header>
    <main class="mdl-layout__content">
        OOPS! Something went wrong. Our engineers are working on it.
    </main>
</div>


</body>
</html>
