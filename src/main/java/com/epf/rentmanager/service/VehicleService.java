package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.dao.VehicleDao;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

	private VehicleDao vehicleDao;
	private VehicleService(VehicleDao vehicleDao){this.vehicleDao = vehicleDao;}
	
	
	public long create(Vehicle vehicle) throws ServiceException {
		try {
			vehicleDao.create(vehicle);
		}catch (DaoException daoException){
			throw new ServiceException("Le vehicule n'a pas été créé");
		}
		return 0;
	}

	public Vehicle findById(long id) throws ServiceException {
		Vehicle vehicule;
		try {
			vehicule = vehicleDao.findById(id);
		}catch (DaoException daoException){
			throw new ServiceException("Le vehicule n'a pas été trouvé");
		}
		return vehicule;
	}

	public List<Vehicle> findAll() throws ServiceException {
		List<Vehicle> listVehicule;
		try {
			listVehicule = vehicleDao.findAll();
		}catch (DaoException daoException){
			throw new ServiceException("Les vehicules n'ont pas été trouvés");
		}
		return listVehicule;
	}

	public int count() throws ServiceException {
		int count;
		try {
			count = vehicleDao.number_of_vehicules();
		}catch (DaoException daoException){
			throw new ServiceException("Problème pour compter les véhicules");
		}
		return count;
	}

	public void delete(Vehicle vehicle) throws ServiceException {
		try {
			vehicleDao.delete(vehicle);
		} catch (DaoException daoException) {
			throw new ServiceException("Problème pour supprimer le véhicule");
		}
	}
}
