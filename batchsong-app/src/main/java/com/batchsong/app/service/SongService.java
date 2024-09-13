package com.batchsong.app.service;

import java.time.Clock;
import java.util.Random;

import com.batchsong.app.repo.BatchSongRepository;
import com.batchsong.app.repo.SimpleSongRepository;

public class SongService {

	public SongService(SimpleSongRepository simpleSongRepository, Random random, Clock systemUTC) {
		// TODO Auto-generated constructor stub
	}

	public SongService(BatchSongRepository batchSongRepository, Random random, Clock systemUTC) {
		// TODO Auto-generated constructor stub
	}

	public long createProducts(int recordCount) {
		// TODO Auto-generated method stub
		return 0;
	}

}
