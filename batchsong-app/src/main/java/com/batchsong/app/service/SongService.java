package com.batchsong.app.service;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;

import com.batchsong.app.model.Song;
import com.batchsong.app.repo.SongRepository;

public class SongService {
	private SongRepository songRepository;
	private Random random;
	private Clock clock;

	public SongService(SongRepository songRepository, Random random, Clock clock) {
		this.songRepository = songRepository;
		this.random = random;
		this.clock = clock;

	}

	@Transactional
	public long createProducts(int count) {
		List<Song> songs = generate(count);
		long startTime = clock.millis();
		songRepository.saveAll(songs);
		return clock.millis() - startTime;

	}

	private List<Song> generate(int count) {
		final String[] names = { "ab", "cd", "ef", "gh" };

		final List<Song> songs = new ArrayList<>(count);

		for (int i = 0; i < count; i++) {
			Song song = new Song();
			song.setName(names[random.nextInt(4)]);
			songs.add(song);
		}
		return songs;
	}
}
