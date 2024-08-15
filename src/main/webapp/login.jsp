<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="locale" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "Default Title" %></title>

    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4"><fmt:message key="login_heading"/></h2>

    <!-- Main content -->
    <div id="content" class="card p-4 shadow">
        <form action="language" method="post">
            <fieldset>
                <div class="form-group">
                    <label for="languageSelect"><fmt:message key="language_label"/></label>
                    <select class="form-control" id="languageSelect" name="language">
                        <option value="en">English</option>
                        <option value="ru">Russian</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-secondary"><fmt:message key="submit_button"/></button>
            </fieldset>
        </form>
        <form action="login" method="post">
            <fieldset>
                <div class="form-group">
                    <label for="login"><fmt:message key="login_label"></fmt:message> </label>
                    <input type="text" class="form-control" id="login" name="name" required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="password_label"/></label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary"><fmt:message key="login_button"/></button>
            </fieldset>
        </form>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
