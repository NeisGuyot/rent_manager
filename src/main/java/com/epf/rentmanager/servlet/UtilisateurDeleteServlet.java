package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ClientDeleteServlet", urlPatterns = "/users/delete")
public class UtilisateurDeleteServlet extends HttpServlet {
    @Autowired
    ClientService clientService;


    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Client client = new Client(Long.parseLong(request.getParameter("id")),"", "", "", LocalDate.now());
        try {
            clientService.delete(client);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        //request.getServletContext().getRequestDispatcher("/WEB-INF/views/users/list.jsp").forward(request, response);
        response.sendRedirect("/rentmanager/users");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //request.getParameter("url");
        doGet(request, response);
    }
}
