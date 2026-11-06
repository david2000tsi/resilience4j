package com.java.resilience4j.service;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CircuitBreakerService {

	private void sleep(long t) {
		try {
			Thread.sleep(t);
		} catch(InterruptedException e) {
			log.error("sleep interrupt: " + e.getMessage());
			Thread.currentThread().interrupt();
		} catch(Exception e) {
			log.error("sleep error: " + e.getMessage());
		}
	}

	@CircuitBreaker(name = "cb", fallbackMethod = "fallback")
	public String run(String s) {
		log.debug("Iniciando execução, recebido [" + s + "]");
		if(s.equals("wait")) {
			// Waiting 1500ms...
			// the circuit breaker configuration time is 1000ms.
			this.sleep(1500);
			// Throwing an exception to trigger the circuit breaker...
			throw new RuntimeException();
		}
		return "Success";
	}

	public String fallback(String s, Throwable t) {
		String fb = "Fallback : [" + s + "]: " + t.getMessage();
		log.warn(fb);
		return fb;
	}
}
