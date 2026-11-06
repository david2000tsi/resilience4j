package com.java.resilience4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.resilience4j.service.CircuitBreakerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/cb")
@RequiredArgsConstructor
public class CircuitBreakerController {

	private final CircuitBreakerService circuitBreakerService;

	@GetMapping(value = "/{str}")
	public ResponseEntity<String> executarAcao(@PathVariable String str) {
		return ResponseEntity.ok(this.circuitBreakerService.run(str));
	}
}
