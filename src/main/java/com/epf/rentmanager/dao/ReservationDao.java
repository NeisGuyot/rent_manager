package com.epf.rentmanager.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.epf.rentmanager.Exception.DaoException;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.persistence.ConnectionManager;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDao {
	private ReservationDao() {}

	
	private static final String CREATE_RESERVATION_QUERY = "INSERT INTO Reservation(client_id, vehicle_id, debut, fin) VALUES(?, ?, ?, ?);";
	private static final String DELETE_RESERVATION_QUERY = "DELETE FROM Reservation WHERE id=?;";
	private static final String FIND_RESERVATIONS_BY_CLIENT_QUERY = "SELECT id, vehicle_id, debut, fin FROM Reservation WHERE client_id=?;";
	private static final String FIND_RESERVATIONS_BY_VEHICLE_QUERY = "SELECT id, client_id, debut, fin FROM Reservation WHERE vehicle_id=?;";
	private static final String FIND_RESERVATIONS_QUERY = "SELECT id, client_id, vehicle_id, debut, fin FROM Reservation;";

	public long create(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(CREATE_RESERVATION_QUERY, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, reservation.getClient_id());
			preparedStatement.setInt(2, reservation.getVehicule_id());
			preparedStatement.setDate(3, reservation.toSQLDebut());
			preparedStatement.setDate(4, reservation.toSQLFin());

			preparedStatement.execute();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if(resultSet.next()){
				int id = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return 0;
	}

	public long delete(Reservation reservation) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(DELETE_RESERVATION_QUERY);
			preparedStatement.setLong(1, reservation.getVehicule_id());

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	
	public List<Reservation> findResaByClientId(long clientId) throws DaoException {
		return new ArrayList<Reservation>();
	}
	
	public List<Reservation> findResaByVehicleId(long vehicleId) throws DaoException {
		return new ArrayList<Reservation>();
	}

	public List<Reservation> findAll() throws DaoException {
		List<Reservation> reservation = new ArrayList<Reservation>();
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()){
				int id = resultSet.getInt("id");
				int client_id = resultSet.getInt("client_id");
				int vehicle_id = resultSet.getInt("vehicle_id");
				Date debut = resultSet.getDate("debut");
				Date fin = resultSet.getDate("fin");

				reservation.add(new Reservation(id, client_id, vehicle_id, debut.toLocalDate(), fin.toLocalDate()));
			}
			return reservation;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public int number_of_reservations() throws DaoException {
		int reservations = 0;
		try {
			Connection connection = ConnectionManager.getConnection();
			Statement statement =
					connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS total FROM Reservation");
			if(resultSet.next()){
				reservations = resultSet.getInt("total");
			}
			return reservations;
		} catch (SQLException e) {
			throw new DaoException();
		}
	}

	public Reservation findById(long id) throws DaoException {
		try {
			Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement =
					connection.prepareStatement(FIND_RESERVATIONS_QUERY);
			preparedStatement.setLong(1, id);

			ResultSet resultSet =  preparedStatement.executeQuery();
			resultSet.next();
			int client_id = resultSet.getInt("client_id");
			int vehicle_id = resultSet.getInt("vehicle_id");
			Date debut = resultSet.getDate("debut");
			Date fin = resultSet.getDate("fin");
			return new Reservation((int) id, client_id, vehicle_id, debut.toLocalDate(), fin.toLocalDate());
		} catch (SQLException e) {
			throw new DaoException();
		}
	}
}
