<%@page import="modelo.Puesto" %>
<%@page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Puestos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
</head>
<body>
    <h1>Puestos</h1>
    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_puestos" onclick="limpiar()">Nuevo puesto</button>
    <div class="container">
        <div class="modal fade" id="modal_puestos" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <form action="sr_puestos" method="post" class="form-group">
                            <label for="lbl_idpuesto"><b>ID Puesto:</b></label>
                            <input type="text" name="txt_idpuesto" id="txt_idpuesto" class="form-control" value="0" readonly>
                            <label for="lbl_puestos"><b>Puesto:</b></label>
                            <input type="text" name="txt_puestos" id="txt_puestos" class="form-control" placeholder="Ejemplo: Puesto" required>
                            <br>
                            <button name="btn_agregar_puestos" id="btn_agregar_puestos" value="agregar" class="btn btn-primary">Agregar Puesto</button>
                            <button name="btn_modificar_puestos" id="btn_modificar_puestos" value="modificar" class="btn btn-success">Modificar Puesto</button>
                            <button name="btn_eliminar_puestos" id="btn_eliminar_puestos" value="eliminar" class="btn btn-outline-warning" onclick="javascript:if(!confirm('¿Desea eliminar este Puesto?')) return false">Eliminar Puesto</button>
                            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">Cerrar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
                <tr>
                    <th>Puesto</th>
                </tr>
            </thead>
            <tbody id="tbl_puesto">
               <%
       
  Puesto puesto= new Puesto();
   DefaultTableModel tabla = new DefaultTableModel();
    tabla = puesto.leer();
    for(int t =0; t<tabla.getRowCount();t++){
       out.println("<tr data-id="+tabla.getValueAt(t,0)+">");
       out.println("<td>"+tabla.getValueAt(t,1)+"</td>");
out.println("</tr>");
       }
   %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
    <script type="text/javascript">
        function limpiar() {
            $("#txt_idpuesto").val(0);
            $("#txt_puestos").val('');
        }

        $('#tbl_puestos').on('click', 'tr', function (evt) {
            var target,idpuesto,puestos;
            var target = $(event.target);
            var idpuesto = target.parent().data('id');
            var puestos = target.parent("tr").find("td").eq(0).html();
            $("#txt_idpuesto").val(idpuesto);
            $("#txt_puestos").val(puestos);
            $("#modal_puestos").modal('show');
        });
    </script>
</body>
</html>
