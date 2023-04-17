package com.epf.rentmanager.service;

import java.util.List;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.Exception.ServiceException;
import com.epf.rentmanager.dao.ClientDao;
import com.epf.rentmanager.model.Client;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	private ClientDao clientDao;
	private ClientService(ClientDao clientDao){this.clientDao = clientDao;}
	
	
	public long create(Client client) throws ServiceException {
		try {
			clientDao.create(client);
		}catch (DaoException daoException){
			throw new ServiceException("Le client n'a pas été créé");
		}
		return 0;
	}

	public Client findById(long id) throws ServiceException {
		Client client;
		try {
			client = clientDao.findById(id);
		}catch (DaoException daoException){
			throw new ServiceException("Le client n'a pas été trouvé");
		}
		return client;
	}

	public List<Client> findAll() throws ServiceException {
		List<Client> listClient;
		try {
			listClient = clientDao.findAll();
		}catch (DaoException daoException){
			throw new ServiceException("Les clients n'ont pas été trouvés");
		}
		return listClient;
	}

	public int count() throws ServiceException {
		int count;
		try {
			count = clientDao.number_of_clients();
		}catch (DaoException daoException){
			throw new ServiceException("Problème pour compter les clients");
		}
		return count;
	}

    public void delete(Client client) throws ServiceException {
		try {
			clientDao.delete(client);
		}catch (DaoException daoException){
			throw new ServiceException("Problème pour supprimer le client");
		}
    }
}
