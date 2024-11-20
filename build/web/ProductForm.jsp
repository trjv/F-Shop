<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Product Form</title>
        <link rel="icon" href="img/logo2.png" type="img/x-icon">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <header>
            <jsp:include page="navbar.jsp"></jsp:include>
            </header>
            <div class="container">
                <h2>${product == null ? 'Add New Product' : 'Edit Product'}</h2>
            <form action="Controller" method="post" enctype="multipart/form-data">
                <input type="hidden" name="action" value="${product == null ? 'new' : 'update'}">
                <c:if test="${product != null}">
                    <input type="hidden" name="id" value="${product.id}">
                </c:if>
                <div class="form-group">
                    <label for="productName">Product Name</label>
                    <input type="text" class="form-control" id="productName" name="productName" value="${product != null ? product.getName() : ''}" required>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" value="${product != null ? product.getPrice() : ''}" required>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description" required>${product != null ? product.getDescription() : ''}</textarea>
                </div>
                <div class="form-group">
                    <label for="categoryId">Category</label>
                    <select class="form-control" id="categoryId" name="categoryId" required>
                        <option value="">Select Category</option>
                        <option value="1" ${product != null && product.getCategoryID() == 1 ? 'selected' : ''}>Digital Cameras</option>
                        <option value="2" ${product != null && product.getCategoryID() == 2 ? 'selected' : ''}>DSLR Cameras</option>
                        <option value="3" ${product != null && product.getCategoryID() == 3 ? 'selected' : ''}>Mirrorless Cameras</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="manufacturerId">Manufacturer</label>
                    <select class="form-control" id="manufacturerId" name="manufacturerId" required>
                        <option value="">Select Manufacturer</option>
                        <option value="1" ${product != null && product.getManufacturerID() == 1 ? 'selected' : ''}>Sony</option>
                        <option value="2" ${product != null && product.getManufacturerID() == 2 ? 'selected' : ''}>Canon</option>
                        <option value="3" ${product != null && product.getManufacturerID() == 3 ? 'selected' : ''}>Fujifilm</option>
                        <option value="4" ${product != null && product.getManufacturerID() == 4 ? 'selected' : ''}>Nikon</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="stock">Stock</label>
                    <textarea class="form-control" id="description" name="stock" required>${product != null ? product.getStock() : ''}</textarea>
                </div>
                <div class="form-group">
                    <label for="image">Image File</label>
                    <input type="file" class="form-control" id="image" name="image"  ${product == null ? 'required' : ''}>
                </div>
                <button type="submit" class="btn btn-primary">${product == null ? 'Add Product' : 'Change'}</button>

            </form>
        </div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
