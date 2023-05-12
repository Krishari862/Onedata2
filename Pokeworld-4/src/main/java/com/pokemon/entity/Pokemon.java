package com.pokemon.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String name;

    
    @Enumerated(EnumType.STRING)
    private PokemonType type;

    @NotNull
    @Min(1)
    private int hp;

	public Pokemon() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pokemon( String name,  PokemonType type,int hp) {
		super();
		this.name = name;
		this.type = type;
		this.hp = hp;
	}

	public Pokemon(int id,  String name,  PokemonType type,  int hp) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.hp = hp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public PokemonType getType() {
		return type;
	}

	public void setType(PokemonType type) {
		this.type = type;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", type=" + type + ", hp=" + hp + "]";
	}

}