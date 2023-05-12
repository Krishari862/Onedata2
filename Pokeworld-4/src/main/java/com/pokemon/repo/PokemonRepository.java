package com.pokemon.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemon.entity.Pokemon;
import com.pokemon.entity.PokemonType;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

List<Pokemon> findByType(PokemonType type);

}
