package com.apirest.webflux;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;

import reactor.core.publisher.Mono;

//@Component
public class PlaylistHandler {

	
	@Autowired
	PlaylistService service;

	public Mono<ServerResponse> findAll(ServerRequest request) {

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll(), Playlist.class);

	}

	public Mono<ServerResponse> findById(ServerRequest request) {

		String id = request.pathVariable("id");

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(service.findById(id), Playlist.class);

	}

	public Mono<ServerResponse> save(ServerRequest request) {

		final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);

		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromPublisher(playlist.flatMap(service::save), Playlist.class));
				
		
		
	}

}
