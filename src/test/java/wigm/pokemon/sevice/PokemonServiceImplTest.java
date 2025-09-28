package wigm.pokemon.sevice;

import co.pokeapi.api.v2.pokemon.GetBaseExperienceResponse;
import co.pokeapi.api.v2.pokemon.GetIdResponse;
import co.pokeapi.api.v2.pokemon.GetNameResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import wigm.pokemon.client.PokemonClient;
import wigm.pokemon.configuration.ObjectMapperConfig;
import wigm.pokemon.service.PokemonServiceImpl;

import java.io.IOException;


@SpringBootTest( classes = {ObjectMapperConfig.class})
public class PokemonServiceImplTest {

    @Mock
    private ObjectMapper mapper;
    @Mock
    private PokemonClient pokemonClient;
    @InjectMocks
    private PokemonServiceImpl pokemonService;

    private JsonNode info;

    @BeforeEach
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
        ObjectMapper mapper = new ObjectMapper();
        this.info = mapper.readTree(response);
    }

    @Test
    public void getNameOk() throws IOException {
        GetNameResponse response = new GetNameResponse();
        response.setName("charmander");
        Mockito.when(pokemonClient.pokemonInfo(Mockito.anyString())).thenReturn(info);
        Mockito.when(mapper.convertValue(Mockito.any(JsonNode.class), Mockito.any(Class.class)))
                .thenReturn(response);
        GetNameResponse nameResponse = pokemonService.name("charmander");
        Assertions.assertEquals(nameResponse.getName(), "charmander");
    }

    @Test
    public void getBaseExperienceOk() throws IOException {
        GetBaseExperienceResponse response = new GetBaseExperienceResponse();
        response.setBaseExperience(62);
        Mockito.when(pokemonClient.pokemonInfo(Mockito.anyString())).thenReturn(info);
        Mockito.when(mapper.convertValue(Mockito.any(JsonNode.class), Mockito.any(Class.class)))
                .thenReturn(response);
        GetBaseExperienceResponse nameResponse = pokemonService.baseExperience("charmander");
        Assertions.assertEquals(nameResponse.getBaseExperience(), 62);
    }

    @Test
    public void getIdOk() throws IOException {
        GetIdResponse response = new GetIdResponse();
        response.setId(4);
        Mockito.when(pokemonClient.pokemonInfo(Mockito.anyString())).thenReturn(info);
        Mockito.when(mapper.convertValue(Mockito.any(JsonNode.class), Mockito.any(Class.class)))
                .thenReturn(response);
        GetIdResponse nameResponse = pokemonService.id("charmander");
        Assertions.assertEquals(nameResponse.getId(), 4);
    }


}
