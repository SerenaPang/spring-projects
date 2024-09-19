package com.batchsong.app;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.batchsong.app.service.SongService;


@SpringBootApplication
public class SpringJdbcBatchPerformanceApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("batchSongService")
	private SongService batchSongService;
	@Autowired
	@Qualifier("simpleSongService")
	private SongService simpleSongService;

	public static void main(String[] args) {
		System.out.println("SpringJdbcBatchPerformanceApplication.main() starting ........");
		SpringApplication.run(SpringJdbcBatchPerformanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int[] recordCounts = { 1, 10, 100, 1000, 10_000, 100_000, 1000_000 };

		for (int recordCount : recordCounts) {
			long regularElapsedTime = simpleSongService.createProducts(recordCount);
			long batchElapsedTime = batchSongService.createProducts(recordCount);

			System.out.println(String.join("", Collections.nCopies(50, "-")));
			System.out.format("%-20s%-5s%-10s%-5s%8sms\n", "Regular inserts", "|", recordCount, "|",
					regularElapsedTime);
			System.out.format("%-20s%-5s%-10s%-5s%8sms\n", "Batch inserts", "|", recordCount, "|", batchElapsedTime);
			System.out.printf("Total gain: %d %s\n", calculateGainInPercent(regularElapsedTime, batchElapsedTime), "%");
		}

	}

	int calculateGainInPercent(long before, long after) {
		return (int) Math.floor(100D * (before - after) / before);
	}
}