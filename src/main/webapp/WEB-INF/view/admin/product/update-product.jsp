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
                <title>Create Product</title>
                <!-- 
            <link rel="stylesheet" href="/css/demo.css"> -->
                <!-- Bootstrap CSS -->

                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const productFile = $("#productFile");
                        const orgImage = "${product.image}";
                        if (orgImage) {
                            const urlImage = "/images/product/" + orgImage;
                            $("#imagePreview").attr("src", urlImage);
                            $("#imagePreview").css({ "display": "block" });
                        }
                        productFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#imagePreview").attr("src", imgURL);
                            $("#imagePreview").css({ "display": "block" });
                        });
                    });
                </script>

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
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h1>Create Product</h1>
                                            <hr>
                                            <form:form method="post" action="/admin/product/update"
                                                modelAttribute="product" enctype="multipart/form-data">
                                                <div class="col mb-3">
                                                    <label class="form-label">ID</label>
                                                    <form:input path="id" class="form-control" disabled="true" />
                                                    <form:hidden path="id" />
                                                </div>
                                                <div class="row">
                                                    <div class="col mb-3">
                                                        <c:set var="errorName">
                                                            <form:errors path="name" cssClass="invalid-feedback" />
                                                        </c:set>

                                                        <label class="form-label">Name</label>
                                                        <form:input type="text"
                                                            class="form-control  ${not empty errorName?'is-invalid':''}"
                                                            path="name" />
                                                        ${errorName}
                                                    </div>


                                                    <div class="col mb-3">
                                                        <c:set var="errorPrice">
                                                            <form:errors path="price" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Price</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorPrice?'is-invalid':''}"
                                                            path="price" />
                                                        ${errorPrice}
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col mb-3">
                                                        <c:set var="errorShortDescription">
                                                            <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Short Description</label>
                                                        <form:input type="text" class="form-control
                                                        ${not empty errorShortDescription?'is-invalid':''}"
                                                            path="shortDesc" />
                                                        ${errorShortDescription}
                                                    </div>


                                                    <div class="col mb-3">
                                                        <c:set var="errorQuantity">
                                                            <form:errors path="quantity" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Quantity</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorQuantity?'is-invalid':''}"
                                                            path="quantity" />
                                                        ${errorQuantity}
                                                    </div>
                                                </div>
                                                <div class="col mb-3">
                                                    <c:set var="errorDetailDescription">
                                                        <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Detail Description</label>
                                                    <form:textarea
                                                        class="form-control ${not empty errorDetailDescription?'is-invalid':''}"
                                                        type="text" path="detailDesc" />
                                                    ${errorDetailDescription}


                                                </div>

                                                <div class="row">
                                                    <div class="col-12 col-md-6">
                                                        <label class="form-label">Factory</label>
                                                        <form:select class="col form-select  mb-3" path="factory">
                                                            <form:option value="Apple">Apple</form:option>
                                                            <form:option value="SamSung">SamSung</form:option>
                                                            <form:option value="MSI">MSI</form:option>
                                                            <form:option value="DELL">DELL</form:option>
                                                            <form:option value="ASUS">ASUS</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-12 col-md-6">
                                                        <label class="form-label">Target</label>
                                                        <form:select class="col form-select  mb-3" path="target">
                                                            <form:option value="Gamming">Gamming</form:option>
                                                            <form:option value="Office">Office</form:option>
                                                            <form:option value="Student">Student</form:option>
                                                            <form:option value="Old">Old</form:option>

                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="col-12 col-md-6">
                                                    <div class="mb-3">
                                                        <label for="avatarFile" class="form-label">Image</label>
                                                        <input class="form-control" type="file" id="productFile"
                                                            accept=".png, .jpg, .jpeg" name="gicungdcFile" />
                                                    </div>
                                                </div>
                                                <div class="row"><img style="max-height:250px;  " alt="image preview"
                                                        id="imagePreview">
                                                </div>


                                                <div class="d-flex justify-content-between">
                                                    <button class="btn btn-primary">Button</button>
                                                    <a href="/admin/product" class="btn btn-success "> Back</a>
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