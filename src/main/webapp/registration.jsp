<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%= request.getAttribute("pageTitle") != null ? request.getAttribute("pageTitle") : "Default Title" %>
    </title>

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Registration</h2>

    <%-- Main content goes here --%>
    <div id="content" class="card p-4 shadow">
        <form action="/registration" method="post">
            <fieldset>
                <div class="form-group">
                    <label for="login">Login:</label>
                    <input type="text" class="form-control" id="login" name="name"/>
                </div>
                <div style="z-index: 1000">
                    <c:forEach var="loginValidationError" items="${loginValidationErrors}">
                        <p>
                                ${loginValidationError}
                        </p>
                    </c:forEach>
                </div>

                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" id="password" name="password"/>
                </div>
                <c:forEach var="passwordValidationError" items="${passwordValidationErrors}">
                    <p>
                            ${passwordValidationError}
                    </p>
                </c:forEach>
                <button type="submit" class="btn btn-primary">Register</button>
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