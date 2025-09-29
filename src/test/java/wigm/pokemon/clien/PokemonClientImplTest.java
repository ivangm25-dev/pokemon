package wigm.pokemon.clien;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClient;
import wigm.pokemon.client.PokemonClientImpl;
import wigm.pokemon.exception.PokemonException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

public class PokemonClientImplTest {

    @Mock
    private RestClient restClient;

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @InjectMocks
    private PokemonClientImpl pokemonClient;

    private  String response = "{\n" +
            "  \"abilities\": [\n" +
            "    {\n" +
            "      \"ability\": {\n" +
            "        \"name\": \"blaze\",\n" +
            "        \"url\": \"https://pokeapi.co/api/v2/ability/66/\"\n" +
            "      },\n" +
            "      \"is_hidden\": false,\n" +
            "      \"slot\": 1\n" +
            "    },\n" +
            "    {\n" +
            "      \"ability\": {\n" +
            "        \"name\": \"solar-power\",\n" +
            "        \"url\": \"https://pokeapi.co/api/v2/ability/94/\"\n" +
            "      },\n" +
            "      \"is_hidden\": true,\n" +
            "      \"slot\": 3\n" +
            "    }\n" +
            "  ],\n" +
            "  \"base_experience\": 62,\n" +
            "  \"height\": 6,\n" +
            "  \"held_items\": [],\n" +
            "  \"id\": 4,\n" +
            "  \"is_default\": true,\n" +
            "  \"location_area_encounters\": \"https://pokeapi.co/api/v2/pokemon/4/encounters\",\n" +
            "  \"name\": \"ditto\",\n" +
            "  \"weight\": 85\n" +
            "}";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPokemonOk() {

        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode result = objectMapper.convertValue(this.response, JsonNode.class);
        given(restClient.get()).willReturn(requestHeadersUriSpec);
        given(requestHeadersUriSpec.uri(Mockito.any(String.class), Mockito.any(String.class))).willReturn(requestHeadersSpec);
        given(requestHeadersSpec.retrieve()).willReturn(responseSpec);
        given(responseSpec.onStatus(status -> status.value() == 404, (request, response) -> {
            throw new PokemonException("NotFound");
        })).willReturn(responseSpec);
        given(responseSpec.body(JsonNode.class)).willReturn(result);
        JsonNode response = pokemonClient.pokemonInfo("ditto");

        assertEquals("ditto", response.get("name").textValue());
    }
    @Test
    void testPokemonFail() {

        ObjectMapper objectMapper= new ObjectMapper();
        JsonNode result = objectMapper.convertValue(this.response, JsonNode.class);
        given(restClient.get()).willReturn(requestHeadersUriSpec);
        given(requestHeadersUriSpec.uri(Mockito.any(String.class), Mockito.any(String.class))).willReturn(requestHeadersSpec);
        given(requestHeadersSpec.retrieve()).willReturn(responseSpec);
        given(responseSpec.onStatus(status -> status.value() == 404, (request, response) -> {
            throw new PokemonException("NotFound");
        })).willReturn(responseSpec);
        given(responseSpec.body(JsonNode.class)).willReturn(result);
        assertThrows(PokemonException.class, () -> pokemonClient.pokemonInfo("dtt"));

    }

}