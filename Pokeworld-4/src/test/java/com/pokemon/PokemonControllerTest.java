package com.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import com.pokemon.entity.*;
import com.pokemon.controller.PokemonController;
import com.pokemon.repo.PokemonRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PokemonControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @InjectMocks
    PokemonController pokemonController;
    @Mock
    private PokemonRepository pokemonRepository;

    @Test
    public void createPokemonSuccess() {
        Pokemon newPokemon = new Pokemon("Pikachu", PokemonType.Electric, 35);
        ResponseEntity<?> response = restTemplate.postForEntity("/api/pokemons", newPokemon, Void.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getHeaders().getLocation().toString().contains("/api/pokemons"));
        assertTrue(pokemonRepository.findById(1).isPresent());
    }

    @Test
    public void createPokemonBadRequest() {
        Pokemon invalidPokemon = new Pokemon("", null, -1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Pokemon> requestEntity = new HttpEntity<>(invalidPokemon, headers);
        ResponseEntity<ApiError> response = restTemplate.exchange(
                "/api/pokemons",
                HttpMethod.POST,
                requestEntity,
                ApiError.class
            );

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            assertTrue(response.getBody().getMessage().contains("name"));
            assertTrue(response.getBody().getMessage().contains("type"));
            assertTrue(response.getBody().getMessage().contains("hp"));
            assertFalse(pokemonRepository.findById(1).isPresent());
            /*
        ResponseEntity<ApiError> response = restTemplate.postForEntity("/api/pokemons", invalidPokemon, ApiError.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().getMessage().contains("name"));
        assertTrue(response.getBody().getMessage().contains("type"));
        assertTrue(response.getBody().getMessage().contains("hp"));
        assertFalse(pokemonRepository.findById(1L).isPresent());*/
    }
    @Test
	public void testFindByTypeerror1() 
	{
		// given
		Pokemon pokemon1 = new Pokemon(1, "Gengar", PokemonType.Ghost,1);
		Pokemon pokemon2 = new Pokemon(2,"Charmander",PokemonType.Fire,2);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(pokemon1, pokemon2));

		when(pokemonRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		 MockHttpServletRequest request = new MockHttpServletRequest();
		    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		    ResponseEntity<List<Pokemon>>  response = pokemonController.getPokemontype1(PokemonType.Fire);

		    // then
		    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		  //  assertThat(response.getBody().getEmployeeList().size()).isEqualTo(2);
		  //  assertThat(response.getBody().getEmployeeList().get(0).getName()).isEqualTo(employee1.getName());
		}
    @Test
	public void testFindByTypeerror11() 
	{
		// given
		Pokemon pokemon1 = new Pokemon(1, "Gengar", PokemonType.Ghost,100);
		Pokemon pokemon2 = new Pokemon(2, "Pickachu",PokemonType.Electric,100);
		List<Pokemon> list = new ArrayList<Pokemon>();
		list.addAll(Arrays.asList(pokemon1, pokemon2));

		when(pokemonRepository.findByType(PokemonType.Ghost)).thenReturn(list);

		// when
		 MockHttpServletRequest request = new MockHttpServletRequest();
		    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		    ResponseEntity<List<Pokemon>> response = pokemonController.getPokemontype1(PokemonType.Fire);

		    // then
		    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		  //  assertThat(response.getBody().getEmployeeList().size()).isEqualTo(2);
		  //  assertThat(response.getBody().getEmployeeList().get(0).getName()).isEqualTo(employee1.getName());
		}
}