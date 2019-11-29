package com.h2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.h2.service.CommonService;

@RestController
public class CommonController {

	@Autowired
	private CommonService service;

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		service.save();
		return ResponseEntity.ok("saved Successfully");
	}
	
	@GetMapping("/errorTest")
	public ResponseEntity<String> error() {
		String res = service.saveWithError();
		return ResponseEntity.ok(res);
	}
}
