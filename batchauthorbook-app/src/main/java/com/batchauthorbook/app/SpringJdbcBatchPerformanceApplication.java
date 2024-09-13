package com.batchauthorbook.app;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.batchauthorbook.app.service.AuthorService;

@SpringBootApplication
public class SpringJdbcBatchPerformanceApplication implements CommandLineRunner {

	@Autowired
	@Qualifier("batchAuthorBookService")
	private AuthorService batchAuthorService;
	//private BookService batchBookService;
	
	@Autowired
	@Qualifier("simpleAuthorService")
	private AuthorService simpleAuthorService;
	
//	@Autowired
//	@Qualifier("simpleBookService")
//	private BookService simpleBookService;

	public static void main(String[] args) {
		System.out.println("SpringJdbcBatchPerformanceApplication.main() starting ........");
		SpringApplication.run(SpringJdbcBatchPerformanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		int[] recordCounts = { 1, 10, 100, 1000, 10_000, 100_000, 1000_000 };

		for (int recordCount : recordCounts) {
			long regularElapsedTime = simpleAuthorService.createAuthors(recordCount);
			long batchElapsedTime = batchAuthorService.createAuthors(recordCount);

			System.out.println(String.join("", Collections.nCopies(50, "-")));
			System.out.format("%-20s%-5s%-10s%-5s%8sms\n", "Regular inserts", "|", recordCount, "|",
					regularElapsedTime);
			System.out.format("%-20s%-5s%-10s%-5s%8sms\n", "Batch inserts", "|", recordCount, "|", batchElapsedTime);
			System.out.printf("Total gain: %d %s\n", calculateGainInPercent(regularElapsedTime, batchElapsedTime), "%");
			
//			long regularElapsedTime2 = simpleBookService.createBooks(recordCount);
//			long batchElapsedTime2 = batchBookService.createBooks(recordCount);
//
//			System.out.println(String.join("", Collections.nCopies(50, "-")));
//			System.out.format("%-20s%-5s%-10s%-5s%8sms\n", "Regular inserts", "|", recordCount, "|",
//					regularElapsedTime);
//			System.out.format("%-20s%-5s%-10s%-5s%8sms\n", "Batch inserts", "|", recordCount, "|", batchElapsedTime);
//			System.out.printf("Total gain: %d %s\n", calculateGainInPercent(regularElapsedTime, batchElapsedTime), "%");
		}

	}

	int calculateGainInPercent(long before, long after) {
		return (int) Math.floor(100D * (before - after) / before);
	}
}
