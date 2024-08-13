package com.tucyticket.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tucyticket.app.model.Event;

@Repository
public class JdbcEventDao implements EventDao{
	@Autowired
	private  JdbcDataSource dataSource;

	@Override
	public Event saveEvent(Event event) {
		System.out.println("jdbc save event");
		Event eventExist = findEventById(event.getIdEvent());
		if (eventExist != event) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO EVENT(name_event, type_event, date_event) " + "VALUES(?,?,?)");
				ps.setString(1, event.getName());
				ps.setString(2, event.getType());
				ps.setDate(3, event.getDate());
				int i = ps.executeUpdate();

				if (i == 1) {
					// ps.getGeneratedKeys()
					System.out.println("jdbc saved Event info to database");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Event findEventById(Integer id) {
		System.out.println("jdbc find Event by id " + id);
		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement ps = connection.prepareStatement("SELECT id_event, name_event, type_event, date_event FROM EVENT WHERE id_event =?");
			ps.setInt(1, id);
			Event event = new Event();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					event.setIdEvent(rs.getInt("id_event"));
					event.setName(rs.getString("name_event"));
					event.setType(rs.getString("type_event"));
					event.setDate(rs.getDate("date_event"));	
					event.setIdEvent(rs.getInt("id_event"));
					System.out.println(event.toString());
				}
				return event;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Event> findAllEvents() {
		System.out.println("jdbc find all Events");
		List<Event> events = new ArrayList<>();
		try (Connection connection = dataSource.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id_event, name_event, type_event, date_event FROM EVENT");

			while (rs.next()) {
				Event event = new Event();
				event.setIdEvent(rs.getInt("id_event"));
				event.setName(rs.getString("name_event"));
				event.setType(rs.getString("type_event"));
				event.setDate(rs.getDate("date_event"));	
				event.setIdEvent(rs.getInt("id_event"));
				events.add(event);
			}
			System.out.println(events);
			return events;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return events;
	}

	@Override
	public Event deleteEvent(Integer idEvent) {
		System.out.println("jdbc delete User");

		Event target = findEventById(idEvent);
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("DELETE FROM Event WHERE id_event= ?");
				ps.setInt(1, idEvent);
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc delete Event " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Event updateEvent(Event event) {
		System.out.println("jdbc update event");
		Event target = findEventById(event.getIdEvent());
		if (target != null) {
			try (Connection connection = dataSource.getConnection()) {
				PreparedStatement ps = connection.prepareStatement("UPDATE Event SET name_event=?, type_event=?, date_event=? WHERE id_event=?");
				ps.setString(1, event.getName());
				ps.setString(2, event.getType());
				ps.setDate(3, event.getDate());
				ps.setInt(4, event.getIdEvent());
				int i = ps.executeUpdate();
				if (i == 1) {
					System.out.println("jdbc update Event " + target.toString());
					return target;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
}
