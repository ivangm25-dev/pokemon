package wigm.pokemon.client;


import com.fasterxml.jackson.databind.JsonNode;

public interface PokemonClient {
    JsonNode pokemonInfo(String name);
}
