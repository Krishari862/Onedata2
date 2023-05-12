package com.pokemon.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokemon.entity.Pokemon;
import com.pokemon.entity.PokemonType;
import com.pokemon.entity.Pokemons;
import com.pokemon.repo.PokemonRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {
	@Autowired
	private PokemonRepository pokemonRepository;

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Pokemons> createPokemon(@Valid @RequestBody Pokemon pokemon) {
		PokemonType.getPokemonType(pokemon.getType().toString());
		Pokemon savedPokemon = pokemonRepository.save(pokemon);
		return ResponseEntity.created(URI.create("/api/pokemons/" + savedPokemon.getId())).build();
	}

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Pokemon>> getPokemontype1(@RequestParam(required = false) PokemonType type) {
		if (type == null) {
			List<Pokemon> pokemon = pokemonRepository.findAll();
			return ResponseEntity.status(200).body(pokemon);
		}

		List<Pokemon> pokemon = pokemonRepository.findByType(type);

		return ResponseEntity.status(200).body(pokemon);
	}

	@PostMapping(path = "/add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Pokemon> createPokemon1(@Valid @RequestBody Pokemon pokemon) {

		Pokemon savedPokemon = pokemonRepository.save(pokemon);

		return ResponseEntity.status(201).body(savedPokemon);
	}
}