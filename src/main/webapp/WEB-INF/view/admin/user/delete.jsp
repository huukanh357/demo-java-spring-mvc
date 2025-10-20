<%@ page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=, initial-scale=1.0">
                <title>User detail ${id}</title>
                <!-- 
            <link rel="stylesheet" href="/css/demo.css"> -->
                <!-- Bootstrap CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- Bootstrap JS + Popper -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                <!-- jQuery -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

            </head>

            <body>
                <div class="container mt-5">
                    <div class="Row">
                        <div class="col-md-10 col-12 mx-auto">
                            <h1>User detail id= ${id}</h1>
                            <hr>
                            <div class="card col-12">
                                <div class="card-header ">
                                    User information
                                </div>
                                <form:form method="post" action="/admin/user/delete" modelAttribute="user">

                                    <div class="mb-3" style="display: none;">
                                        <label class="form-label">ID</label>
                                        <form:input path="id" class="form-control" disabled="true" />
                                        <form:hidden path="id" />
                                    </div>

                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">ID: ${id}</li>
                                        <li class="list-group-item">Email: ${user.email} </li>
                                        <li class="list-group-item">FullName: ${user.fullName}</li>
                                    </ul>
                            </div>
                            <div class="alert alert-danger" role="alert">
                                Are you sure to delete this User!!!
                            </div>
                            <button class="btn btn-primary">Button</button>
                            </form:form>
                        </div>
                    </div>
                </div>

            </body>

            </html>