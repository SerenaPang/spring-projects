package com.batchsong.app.repo;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.batchsong.app.model.Song;

@Repository
public class BatchSongRepository implements SongRepository {
	private JdbcTemplate jdbcTemplate;

	public BatchSongRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public void saveAll(List<Song> songs) {
		jdbcTemplate.batchUpdate("INSERT INTO Song (name) " + "VALUES (?)", songs, 100,
				(PreparedStatement ps, Song song) -> {
					ps.setString(1, song.getName());
				});
	}
}
