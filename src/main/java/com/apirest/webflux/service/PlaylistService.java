package com.apirest.webflux.service;

import org.springframework.stereotype.Service;

import com.apirest.webflux.document.Playlist;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface PlaylistService {

	
	Flux<Playlist> findAll();
	Mono<Playlist> findById(String id);
	Mono<Playlist> save(Playlist playlist);
	
	
}
