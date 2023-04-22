package com.epf.rentmanager.servlet;

import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet("/rents/create")
public class ReservationCreateServlet extends HttpServlet {

    @Autowired
    VehicleService vehicleService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    ClientService clientService;
    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("clients", clientService.findAll());
            request.setAttribute("voitures", vehicleService.findAll());
            request.setAttribute("reservations", reservationService.findAll());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/create.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int client_id = Integer.valueOf(request.getParameter("client"));
        int vehicule_id = Integer.valueOf(request.getParameter("car"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String beginStr = request.getParameter("begin");
        LocalDate begin = LocalDate.parse(beginStr, formatter);

        String endStr = request.getParameter("end");
        LocalDate end = LocalDate.parse(endStr, formatter);

        try {
            Reservation tempReservation = new Reservation(client_id, vehicule_id, begin, end);
            boolean test = canRentDays(vehicule_id, tempReservation);
            request.setAttribute("alert", test);
            if (test) {
                reservationService.create(tempReservation);
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/rentmanager/rents");
    }

    public boolean canRentDays(int vehicule_id, Reservation tempReservation) {
        try {
            // Récupération des réservations pour le véhicule
            List<Reservation> reservations = reservationService.findResaByVehicleId(vehicule_id);
            reservations.add(tempReservation);
            Collections.sort(reservations, Comparator.comparing(Reservation::getDebut));

            LocalDate today = LocalDate.now();
            LocalDate prevEndDate = today;
            int consecutiveDays = 0;

            for (Reservation r : reservations) {
                LocalDate rStart = r.getDebut();
                LocalDate rEnd = r.getFin();
                if (!prevEndDate.isBefore(rStart) && !today.isAfter(rEnd)) {
                    return false;
                }
                if (prevEndDate.until(rStart, ChronoUnit.DAYS) > 1) {
                    consecutiveDays = 0;
                }
                consecutiveDays += rStart.until(rEnd, ChronoUnit.DAYS) + 1;
                if (consecutiveDays > 7) {
                    return false;
                }
                if (rStart.until(today, ChronoUnit.DAYS) >= 30 && prevEndDate.until(rStart, ChronoUnit.DAYS) > 1) {
                    return false;
                }
                prevEndDate = rEnd;
            }
            return prevEndDate.until(today, ChronoUnit.DAYS) > 1;
        } catch (ServiceException e) {
            e.printStackTrace();
            return false;
        }
    }

}