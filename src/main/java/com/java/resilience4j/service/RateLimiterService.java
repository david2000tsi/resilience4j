package com.java.resilience4j.service;

import org.springframework.stereotype.Service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateLimiterService {

	@RateLimiter(name = "rl", fallbackMethod = "fallback")
	public String run() {
		log.debug("Iniciando execução");
		return "Success";
	}

	public String fallback(Throwable t) {
		String fb = "Fallback: " + t.getMessage();
		log.warn(fb);
		return fb;
	}
}
