package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.service.VehicleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/cars")
public class VehicleListServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                request.setAttribute("vehicles", VehicleService.getInstance().findAll());
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }

            this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response);
    }
}
