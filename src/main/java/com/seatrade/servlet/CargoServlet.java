package com.seatrade.servlet;


import com.seatrade.dao.CargoDaoImplementation;
import com.seatrade.entity.Cargo;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/cargo")
public class CargoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Assuming you have a method getCargos() that returns a list of Cargo objects
        List<Cargo> cargos = getCargos();
        request.setAttribute("cargos", cargos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cargoList.jsp");
        dispatcher.forward(request, response);
    }

    private List<Cargo> getCargos() {
    CargoDaoImplementation     cargoDaoImplementation = new CargoDaoImplementation();

    List<Cargo> cargos = cargoDaoImplementation.listAll();
    return cargos;

    }
}
