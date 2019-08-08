<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Uaktualnij Administratora| Charity</title>

    <!-- Custom fonts for this template-->
    <link href="/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="/resources/admin/css/custom-admin.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <%@include file="adminSidebar.jsp"%>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <%@ include file="adminTopbar.jsp"%>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
<%--                <h1 class="h3 mb-4 text-gray-800">Zaktualizuj dane administratora</h1>--%>

            </div>
            <!-- /.container-fluid -->
            <div class="container">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Zaktualizuj dane administratora:</h1>
                                    </div>
                                    <form:form class="user" method="post" modelAttribute="userDTO">
                                        <form:hidden path="id" />
                                        <div class="form-group row">
                                            <div class="col-sm-4 mb-3 mb-sm-0">
                                                Imię:
                                                <form:input path="firstName" type="text" class="form-control form-control-user" id="exampleFirstName" />
                                                <form:errors path="firstName" cssClass="errorCustom" element="p" />
                                            </div>
                                            <div class="col-sm-4">
                                                Nazwisko:
                                                <form:input path="lastName" type="text" class="form-control form-control-user" id="exampleLastName" />
                                                <form:errors path="lastName" cssClass="errorCustom" element="p" />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-6">
                                                Email
                                                <form:input path="email" type="email" class="form-control form-control-user" id="exampleInputEmail" />
                                                <form:errors path="password" cssClass="errorCustom" element="p" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-1 mb-3 mb-sm-0">
                                                Role:
                                            </div>
                                            <div class="col-sm-2 mb-3 mb-sm-0">
                                                <form:hidden path="roles" />
                                                <form:checkboxes path="rolesIdList" items="${userDTO.roles}" itemLabel="name" element="p" checked="true"/>
                                                <c:forEach items="${remainingRoles}" var="remainingRole" >
                                                    <p><form:checkbox path="rolesIdList" label="${remainingRole.name}" value="${remainingRole.id}" /></p>
                                                </c:forEach>
                                            </div>
                                            <div class="col-sm-1">
                                            </div>
                                            <div class="col-sm-4">
                                                Użytkownik aktywny: <form:checkbox path="enabled" />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-sm-2 mb-3 mb-sm-0">
                                                <input type="submit" value="Zapisz" class="btn btn-primary btn-user btn-block">
                                            </div>
                                            <div class="col-sm-2 mb-3 mb-sm-0">
                                                <a href="/admin/list" class="cancelCustom">
                                                    <button type="button" class="btn btn-danger btn-user btn-block">
                                                        Anuluj
                                                    </button>
                                                </a>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <%@ include file="adminFooter.jsp"%>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="/resources/admin/vendor/jquery/jquery.min.js"></script>
<script src="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/resources/admin/js/sb-admin-2.min.js"></script>

</body>

</html>