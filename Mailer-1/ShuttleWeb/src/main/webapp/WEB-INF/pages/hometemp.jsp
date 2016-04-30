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
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <title>Android</title>
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
    <div class="col-sm-3"></div>
    <div class="col-sm-7">
    <s:form id="referralServiceForm" action="testMail"  enctype="multipart/form-data" method="post" cssClass="well" validate="true">
        <s:textfield cssClass="form-control" key="campaignName"><strong>Campaign Name</strong></s:textfield>
        <s:textfield cssClass="form-control" key="creativeName"><strong>Creative Name</strong></s:textfield>
        <s:textfield cssClass="form-control" key="subject"><strong>Subject</strong></s:textfield>
        <s:textfield cssClass="form-control" key="senderEmail"><strong>Sender</strong></s:textfield>
        <s:file label="File" key="templateFile"/>
        <s:submit type="button" cssClass="btn btn-primary" method="execute" key="button.get"
                  theme="simple">
            <i class="icon-ok icon-white"></i>
            Submit
        </s:submit>
    </s:form>
    </div>
</body>

</html>

