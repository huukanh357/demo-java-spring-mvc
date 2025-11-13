<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">


            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="" />
                <meta name="author" content="" />
                <title>Delete Product</title>
                <!-- 
            <link rel="stylesheet" href="/css/demo.css"> -->
                <!-- Bootstrap CSS -->

                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <!-- hear.jsp -->
                <jsp:include page="../layout/header.jsp" />

                <div id="layoutSidenav">
                    <!-- sidebar.jsp -->
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manager Product</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/admin">DashBoard</a> / Product</li>
                                </ol>


                                <div class="container mt-5">
                                    <div class="Row">
                                        <div class="col-md-10 col-12 mx-auto">
                                            <h1>Product detail id= ${id}</h1>
                                            <hr>
                                            <div class="card col-12">
                                                <div class="card-header ">
                                                    Product information
                                                </div>
                                                <form:form method="post" action="/admin/product/delete"
                                                    modelAttribute="product">

                                                    <div class="mb-3" style="display: none;">
                                                        <label class="form-label">ID</label>
                                                        <form:input path="id" class="form-control" disabled="true" />
                                                        <form:hidden path="id" />
                                                    </div>

                                                    <ul class="list-group list-group-flush">
                                                        <li class="list-group-item">ID: ${id}</li>
                                                        <li class="list-group-item">Name: ${product.name} </li>
                                                        <li class="list-group-item">Giá: ${product.price} </li>
                                                        <li class="list-group-item">Miêu tả ngắn: ${product.shortDesc}
                                                        </li>
                                                        <li class="list-group-item">Miêu tả chi tiết:
                                                            ${product.detailDesc}
                                                        </li>
                                                        <li class="list-group-item">Số lượng: ${product.quantity}</li>
                                                        <li class="list-group-item">Đã bán: ${product.sold}</li>
                                                        <li class="list-group-item">Hãng: ${product.factory}</li>
                                                        <li class="list-group-item">Mục tiêu: ${product.target}</li>
                                                    </ul>
                                            </div>
                                            <div class="alert alert-danger" role="alert">
                                                Are you sure to delete this User!!!
                                            </div>


                                            <div class="d-flex justify-content-between">
                                                <button class="btn btn-primary">Button</button>
                                                <a href="/admin/user" class="btn btn-success "> Back</a>
                                            </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </main>

                        <!-- footer.jsp -->
                        <jsp:include page="../layout/footer.jsp" />
                    </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="js/scripts.js"></script>

            </body>

            </html>