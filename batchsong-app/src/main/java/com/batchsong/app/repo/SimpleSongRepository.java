package com.batchsong.app.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.batchsong.app.model.Song;

@Repository
public class SimpleSongRepository implements SongRepository {
	private final JdbcTemplate jdbcTemplate;
	
	public SimpleSongRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	@Transactional
	public void saveAll(List<Song> songs) {
		for (Song song : songs) {			
			jdbcTemplate.update("INSERT INTO Song (name) " + "VALUES (?)",
					song.getName());
		}
	}
}
