<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Reservations
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="car" class="col-sm-2 control-label">Voiture</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="car" name="car">
                                                <c:forEach items="${voitures}" var="voiture">
                                                    <option value="${voiture.id}">${voiture.constructeur}</option>
                                                </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="client" class="col-sm-2 control-label">Client</label>

                                    <div class="col-sm-10">
                                        <select class="form-control" id="client" name="client">
                                            <c:forEach items="${clients}" var="client">
                                                <option value="${client.id}">${client.nom} ${client.prenom}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="begin" class="col-sm-2 control-label">Date de debut</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="begin" name="begin" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="end" class="col-sm-2 control-label">Date de fin</label>

                                    <div class="col-sm-10">
                                        <input type="date" class="form-control" id="end" name="end" required>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right" id="addBtn" onmouseover='verifyDates()'>Ajouter</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/input-mask/jquery.inputmask.extensions.js"></script>

<script>
    $(function () {
        $('[data-mask]').inputmask()
    });
</script>
<script>
    function verifyDates() {
        const reservations = [
        <c:forEach var="reservation" items="${reservations}">
            '${reservation}',
        </c:forEach>
        ];
        var car = document.getElementById('car').value;
        var date1 = new Date($('#end').val());
        var date2 = new Date($('#begin').val());
        for (reservation of reservations){
            if (reservation.vehicule_id == car) {
                if (!(date2 >= reservation.debut && date2 <= reservation.fin)){
                    alert('Date de debut non valide');
                    break;
                }
            }
        }
        for (reservation of reservations){
            if (reservation.vehicule_id == car) {
                if (!(reservation.vehicule_id == car && date1 >= reservation.debut && date1 <= reservation.fin)){
                    alert('Date de fin non valide');
                    break;
                }
            }
        }
        return true;
    }

    $('#begin').on('change',()=>{
        if ($('#end').val()){
            var date1 = new Date($('#end').val());
            var date2 = new Date($('#begin').val());
            var diffTime = Math.abs(date2 - date1);
            var diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24));
            if(diffDays>=7){
                document.getElementById('addBtn').disabled = true;
            } else {
                document.getElementById('addBtn').disabled = false;
            }
        }
    });

    $('#end').on('change',()=>{
        if ($('#begin').val()){
            var date1 = new Date($('#end').val());
            var date2 = new Date($('#begin').val());
            var diffTime = Math.abs(date2 - date1);
            var diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24));
            if(diffDays>=7){
                document.getElementById('addBtn').disabled = true;
            } else {
                document.getElementById('addBtn').disabled = false;
            }
        }
    });
</script>
</body>
</html>
