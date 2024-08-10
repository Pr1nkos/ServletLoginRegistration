<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Product List</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Product List</h1>
    <form action="/secure/products" method="post" class="mb-5">
        <fieldset>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name"/>
            </div>
            <div class="form-group">
                <label for="imageURL">Image URL:</label>
                <input type="text" class="form-control" id="imageURL" name="imageURL"/>
            </div>
            <button type="submit" class="btn btn-primary">Add product</button>
        </fieldset>
    </form>

    <c:forEach var="product" items="${requestScope.userProducts}">
        <div class="card mb-3">
            <div class="card-body">
                <h5 class="card-title">${product.name}</h5>
                <img src="${product.imageURL}" class="card-img-top" alt="image" style="width: 100px;">
                <form action="/secure/products" method="post" class="mt-2">
                    <input type="hidden" name="_method" value="DELETE"/>
                    <input type="hidden" name="productID" value="${product.uuid}"/>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </c:forEach>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>