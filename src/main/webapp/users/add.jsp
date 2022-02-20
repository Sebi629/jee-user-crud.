
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/header.jsp" %>


<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading-->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Dodaj użytkownika</h1>
        <a href="<c:url value="/users/list.jsp"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm">
            <i class="fas fa-download fa-sm text-white-50">
            </i> Lista użytkowników </a>
    </div>

    <!--Content Row -->
    <div class="row">

        <form action="/useradd" method="post">

            <div class="form-group">

                <label for="userName">Nazwa</label>

                <input name="userName" type="text" class="form-control" id="userName" placeholder="Nazwa Użytkownika">

            </div>

            <div class="form-group">

                <label for="userEmail">Email</label>

                <input name="userEmail" type="email" class="form-control" id="userEmail" placeholder="Email Użytkownika ">

            </div>

            <div class="form-group">

                <label for="userPassword">Hasło</label>

                <input name="userPassword" type="password" class="form-control" id="userPassword" placeholder="Hasło użytkownika">

            </div>



            <button type="submit" class="btn btn-primary">Zapisz</button>

        </form>









        <!-- Content Column trzeba zostawić-->
        <div class="col-lg-6 mb-4">
        </div>
    </div>

</div>
<!-- /.container-fluid -->


<%@ include file="/footer.jsp" %>
