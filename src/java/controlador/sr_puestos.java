package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Puesto;

public class sr_puestos extends HttpServlet {

   Puesto puesto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            puesto = new Puesto();
            puesto.setIdpuesto(Integer.parseInt(request.getParameter("txt_idpuesto")));
            puesto.setPuestos(request.getParameter("txt_puestos"));

            if ("agregar".equals(request.getParameter("btn_agregar_puestos"))) {
                if (puesto.agregar() > 0) {
                    response.sendRedirect("index.jsp");
                } else {
                    out.print("<h1>Error al agregar el puesto.</h1>");
                    out.print("<a href='index.jsp'>Regresar</a>");
                }
            }
            if ("modificar".equals(request.getParameter("btn_modificar_puestos"))) {
                if (puesto.modificar() > 0) {
                    response.sendRedirect("index.jsp");
                } else {
                    out.print("<h1>No se pudo modificar el puesto.</h1>");
                    out.print("<a href='index.jsp'>Regresar</a>");
                }
            }
            if ("eliminar".equals(request.getParameter("btn_eliminar_puestos"))) {
                if (puesto.eliminar() > 0) {
                    response.sendRedirect("index.jsp");
                } else {
                    out.print("<h1>No se pudo eliminar el puesto.</h1>");
                    out.print("<a href='index.jsp'>Regresar</a>");
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
