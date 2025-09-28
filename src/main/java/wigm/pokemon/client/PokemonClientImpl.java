package wigm.pokemon.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import wigm.pokemon.exception.PokemonException;

@Service
public class PokemonClientImpl implements PokemonClient {

    private final String Path = "https://pokeapi.co/api/v2/pokemon/{name}";
    @Autowired
    private RestClient restClient;

    @Override
    public JsonNode pokemonInfo(String name) {
        this.restClient = RestClient.create();
        JsonNode result = restClient.get()
                .uri(Path, name)
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    throw new PokemonException("NotFound");
                })
                .body(JsonNode.class);
        return result;
    }


}
