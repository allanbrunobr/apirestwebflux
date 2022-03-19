package com.apirest.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.apirest.webflux.document.Playlist;

@Repository
public interface PlaylistRepository extends ReactiveMongoRepository<Playlist, String>{

	
	
}
