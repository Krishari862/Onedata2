package com.pokemon.entity;

import java.util.ArrayList;
import java.util.List;

public class Pokemons 
{
    private List<Pokemon> pokemonList;
    
    public List<Pokemon> getPokemonList() {
        if(pokemonList == null) {
        	pokemonList = new ArrayList<>();
        }
        return pokemonList;
    }
 
    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }
}
