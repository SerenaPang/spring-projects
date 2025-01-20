package com.example.theatre.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.theatre.model.Ticket;

@Repository
public class TicketDaoImpl implements TicketDao {
	@Autowired
	private JdbcDataSource dataSource;

	@Override
	public Ticket purchaseTicket(int userId, int showtimeId, int quantity) {
		System.out.println("jdbc purchase Ticket");

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO TICKET(user_id, showtime_id, quantity) " + "VALUES(?,?,?)");
			ps.setInt(1, userId);
			ps.setInt(2, showtimeId);
			ps.setInt(3, quantity);
			int i = ps.executeUpdate();

			if (i == 1) {
				System.out.println("jdbc saved Ticket info to database");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
