package com.pokemon.entity;

public enum PokemonType {
Fire("fire"),
Water("water"),
Ground("ground"),
Poison("poison"),
Ghost("ghost"),
Electric("electric");

private String value;

PokemonType(String value) {
	this.value=value;// TODO Auto-generated constructor stub
}

public String getValue() {
	return value;
}

public void setValue(String value) {
	this.value = value;
}
	
public static PokemonType getPokemonType(String gvalue)
{
	if(gvalue==null||gvalue.trim().isEmpty())
	{
		throw new IllegalArgumentException("Pokemontype cannot be null or Empty");
	}
	for(PokemonType type1: PokemonType.values()) {
		if(type1.getValue().equalsIgnoreCase(gvalue)) {
			return type1;
		}
	}
	throw new IllegalArgumentException("Pokemon type is invalid");
}
}
