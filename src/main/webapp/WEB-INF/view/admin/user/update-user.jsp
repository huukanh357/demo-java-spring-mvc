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
                <title>Update User</title>
                <!-- 
            <link rel="stylesheet" href="/css/demo.css"> -->
                <!-- Bootstrap CSS -->

                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>


                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        const orgImage = "${user.avatar}";
                        if (orgImage) {
                            const urlImage = "/images/avatar/" + orgImage;
                            $("#avatarPreview").attr("src", urlImage);
                            $("#avatarPreview").css({ "display": "block" });
                        }
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
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
                                <h1 class="mt-4">Manager User</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item active"><a href="/admin">DashBoard</a> / User</li>
                                </ol>

                                <div class="container mt-5">
                                    <div class="Row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h1>Update User</h1>
                                            <hr>
                                            <form:form method="post" action="/admin/user/update" modelAttribute="user"
                                                enctype="multipart/form-data">
                                                <div class=" row">
                                                    <div class="col mb-3">
                                                        <label class="form-label">ID</label>
                                                        <form:input path="id" class="form-control" disabled="true" />
                                                        <form:hidden path="id" />
                                                    </div>

                                                    <div class="col mb-3">
                                                        <label class="form-label">Email</label>
                                                        <form:input type="email" class="form-control" path="email" />
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class=" col">
                                                        <label class="form-label">PhoneNumber</label>
                                                        <form:input type="text" class="form-control" path="phone" />
                                                    </div>
                                                    <div class="col mb-3">
                                                        <label class="form-label">Fullname</label>
                                                        <form:input type="text" class="form-control" path="fullName" />
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Adress</label>
                                                    <form:input type="text" class="form-control" path="address" />
                                                </div>

                                                <div class="row">
                                                    <div class="col-12 col-md-6">
                                                        <label class="form-label">Role</label>
                                                        <form:select class="col form-select  mb-3" path="role.name">
                                                            <form:option value="Admin">ADMIN</form:option>
                                                            <form:option value="User">USER</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-12 col-md-6">
                                                        <div class="mb-3">
                                                            <label for="avatarFile" class="form-label">Avatar</label>
                                                            <input class="form-control" type="file" id="avatarFile"
                                                                accept=".png, .jpg, .jpeg" name="gicungdcFile" />
                                                        </div>
                                                    </div>
                                                    <div class="row"><img style="max-height:250px;  "
                                                            alt="avatar preview" id="avatarPreview">
                                                    </div>
                                                </div>

                                                <div class="d-flex justify-content-between">
                                                    <button class="btn btn-primary">Button</button>
                                                    <a href="/admin/user" class="btn btn-success s"> Back</a>
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