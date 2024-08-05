package com.petcoco.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.petcoco.app.dao.JdbcUserPetDao;

@RestController
public class UserPetController {
	@Autowired
	private JdbcUserPetDao userPetDao;
	

}
