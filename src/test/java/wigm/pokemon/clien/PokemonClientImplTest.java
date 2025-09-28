package wigm.pokemon.clien;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClient;
import wigm.pokemon.client.PokemonClientImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


}