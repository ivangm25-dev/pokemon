package wigm.pokemon.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import wigm.pokemon.exception.PokemonException;

@Service
public class PokemonClientImpl implements PokemonClient {

    private final String Path = "https://pokeapi.co/api/v2/pokemon/%s/";
    private ObjectMapper mapper;

    public PokemonClientImpl(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public JsonNode pokemonInfo(String name) {
        RestClient restClient = RestClient.create();
        JsonNode result = restClient.get()
                .uri(String.format(Path, name))
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    throw new PokemonException("NotFound");
                })
                .body(JsonNode.class);
        return result;
    }
}
