package wigm.pokemon.clien;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import wigm.pokemon.client.PokemonClientImpl;
import wigm.pokemon.exception.PokemonException;

@RunWith(SpringRunner.class)
@RestClientTest(PokemonClientImpl.class)
public class PokemonClientImplTest {

    @Autowired
    private PokemonClientImpl client;
    @Autowired
    private MockRestServiceServer server;
    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        String response = "{\n" +
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
                "  \"name\": \"charmander\",\n" +
                "  \"weight\": 85\n" +
                "}";

        this.server.expect(requestTo("/api/v2/pokemon/charmander/"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));
    }

    @Test
    public void getInfoPokemonOk()
            throws Exception {

        JsonNode response = this.client.pokemonInfo("charmander");
        String name= response.get("name").toString();
        Assertions.assertEquals(name, "\"charmander\"");
    }
    git a
}
