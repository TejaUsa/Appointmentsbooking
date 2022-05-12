package com.teja.appointment.database;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.teja.appointment.beans.Appointment;

@Repository
public class DatabaseAccess {
	
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	//Inserts appointments into database
	public void insertAppointment() {													//Inserts appointments into database 
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO appointment(firstName, email, appointmentDate, appointmentTime) VALUES ('Arianna', 'arianna@arianna.com', '2020-10-24', '03:20')";
		int rowsAffected = jdbc.update(query,  namedParameters);
		if (rowsAffected > 0)
			System.out.println("Inserted appointment into database.");
	}
	
	//Inserts user's input into database
	public void insertAppointment(Appointment appointment) {							//METHOD: Inserts appointments into database DYNAMICALLY
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO appointment(firstName, email, appointmentDate, appointmentTime) VALUES (:firstName, :email, :appointmentDate, :appointmentTime)";
		namedParameters.addValue("firstName",  appointment.getFirstName());
		namedParameters.addValue("email",  appointment.getEmail());
		namedParameters.addValue("appointmentDate",  appointment.getAppointmentDate());
		namedParameters.addValue("appointmentTime",  appointment.getAppointmentTime());
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Inserted appointment into database.");
	}
	
	//Calls the list appointments and displays them on the screen
	public List<Appointment> getAppointmentList(){										//Stores appointments in a list
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM appointment";
		return jdbc.query(query,  namedParameters, 
				new BeanPropertyRowMapper<Appointment>(Appointment.class));				//Grabs the SELECT query and displays list on the screen
	}
	
	//Method that allows row be deleted by id
	public void deleteAppointmentById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "DELETE FROM appointment WHERE id = :id";
		namedParameters.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Deleted appointment " + id + " from the database.");
	}
	
	
	public List<Appointment> getAppointmentListById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM appointment WHERE id = :id";
		namedParameters.addValue("id",  id);
		return jdbc.query(query,  namedParameters, new BeanPropertyRowMapper<Appointment>(Appointment.class));
	}

}
