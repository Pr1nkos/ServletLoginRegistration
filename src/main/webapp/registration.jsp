<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.08.2024
  Time: 11:48
  Template for JSP pages.
  You can modify this template using File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- Including common header and scripts --%>
<%@ include file="includes/header.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "Default Title" %></title>
    
    <%-- Custom CSS --%>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h2><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "Page Title" %></h2>
        
        <%-- Main content goes here --%>
        <div id="content">
            <!-- Content to be added -->
        </div>
    </div>
    
    <%-- Including common footer --%>
    <%@ include file="includes/footer.jsp" %>
</body>
</html>
