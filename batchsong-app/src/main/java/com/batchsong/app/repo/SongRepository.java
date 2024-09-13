package com.batchsong.app.repo;

import java.util.List;

import com.batchsong.app.model.Song;

public interface SongRepository {
	void saveAll(List<Song> songs);
}
