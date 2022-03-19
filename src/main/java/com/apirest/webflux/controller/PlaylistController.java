package com.apirest.webflux.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.service.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RequestMapping("/api/webflux/v1")
@RestController
public class PlaylistController {

	
	@Autowired
	PlaylistService service;
	
	
	@GetMapping(value="/playlist")
	public Flux<Playlist> getPlaylist(){
	
		return service.findAll();
	}
	
	@GetMapping(value="/playlist/{id}")
	public Mono<Playlist> getPlaylistId(@PathVariable String id){
		return service.findById(id);
		
	}
	
	@PostMapping(value="/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist){
	//public Mono<Playlist> save(Playlist playlist){
		
		return service.save(playlist);
	}
	
	@GetMapping(value="/playlist/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistEvents(){
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
		Flux<Playlist> events = service.findAll();
		return Flux.zip(interval, events);
		
		
		
	}
}
