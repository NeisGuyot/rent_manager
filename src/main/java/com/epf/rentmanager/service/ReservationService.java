package com.epf.rentmanager.service;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.dao.ReservationDao;
import com.epf.rentmanager.model.Reservation;

import java.util.List;

public class ReservationService {
    private ReservationDao reservationDao;
    public static ReservationService instance;

    private ReservationService() {
        this.reservationDao = ReservationDao.getInstance();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }

        return instance;
    }


    public long create(Reservation reservation) throws ServiceException {
        try {
            reservationDao.create(reservation);
        }catch (DaoException daoException){
            throw new ServiceException("Le client n'a pas été créé");
        }
        return 0;
    }

    public Reservation findById(long id) throws ServiceException {
        Reservation reservation;
        try {
            reservation = reservationDao.findById(id);
        }catch (DaoException daoException){
            throw new ServiceException("La reservation n'a pas été trouvée");
        }
        return reservation;
    }

    public List<Reservation> findAll() throws ServiceException {
        List<Reservation> listReservation;
        try {
            listReservation = reservationDao.findAll();
        }catch (DaoException daoException){
            throw new ServiceException("Les reservations n'ont pas été trouvés");
        }
        return listReservation;
    }

    public int count() throws ServiceException {
        int count;
        try {
            count = reservationDao.number_of_reservations();
        }catch (DaoException daoException){
            throw new ServiceException("Problème pour compter les reservations");
        }
        return count;
    }
}
