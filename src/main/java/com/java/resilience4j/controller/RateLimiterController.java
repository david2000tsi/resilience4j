package com.java.resilience4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.resilience4j.service.RateLimiterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/rl")
@RequiredArgsConstructor
public class RateLimiterController {

	private final RateLimiterService rateLimiterService;

	@GetMapping
	public ResponseEntity<String> executarAcao() {
		return ResponseEntity.ok(this.rateLimiterService.run());
	}

}
