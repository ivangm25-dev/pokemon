package wigm.pokemon.controller;

import co.pokeapi.api.v2.pokemon.GenericComponent;
import co.pokeapi.api.v2.pokemon.GetHeldItemsRequest;
import co.pokeapi.api.v2.pokemon.GetHeldItemsResponse;
import co.pokeapi.api.v2.pokemon.GetNameResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import wigm.pokemon.client.PokemonClient;
import wigm.pokemon.client.PokemonClientImpl;
import wigm.pokemon.entity.enums.TypeRequest;
import wigm.pokemon.entity.repository.LogbookEntity;
import wigm.pokemon.repository.LogbookRepository;
import wigm.pokemon.service.LogbookService;
import wigm.pokemon.service.LogbookServiceImpl;
import wigm.pokemon.service.PokemonService;
import wigm.pokemon.service.PokemonServiceImpl;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;

public class PokemonControllerTest {

    private ObjectMapper objectMapper;
    private PokemonClient pokemonClient;
    private PokemonService pokemonService;
    private PokemonController pokemonController;
    private LogbookService logbookService;
    private HttpServletRequest httpServletRequest;
    private LogbookRepository logbookRepository;
    private GetHeldItemsRequest getHeldItemsRequest;
    private GetHeldItemsResponse getHeldItemsResponse;

    public PokemonControllerTest(){
        objectMapper = mock(ObjectMapper.class);
        pokemonClient = mock(PokemonClientImpl.class);
        logbookRepository = mock(LogbookRepository.class);
        pokemonService = new PokemonServiceImpl(pokemonClient, objectMapper);
        logbookService = new LogbookServiceImpl(logbookRepository);
        httpServletRequest = mock(HttpServletRequest.class);
        pokemonController = new PokemonController(pokemonService, logbookService, httpServletRequest);
    }
    @Given("Get name pokemon is {string}")
    public void get_name_pokemon_is(String string) {
        getHeldItemsRequest = new GetHeldItemsRequest();
        getHeldItemsRequest.setName(string);
    }
    @When("The controller find pokemon")
    public void the_controller_find_pokemon() {
        GetHeldItemsResponse response = new GetHeldItemsResponse();
        GenericComponent genericComponent = new GenericComponent();
        genericComponent.setName("ditto");
        genericComponent.setUrl("url");
        response.setItem(genericComponent);
        Mockito.when(pokemonService.heldItems(Mockito.anyString())).thenReturn(response);
        LogbookEntity entity = new LogbookEntity();
        entity.setIp("1.1.1.1");
        LocalDateTime now = LocalDateTime.now();
        entity.setCreatedDate(now);
        entity.setTypeRequest(TypeRequest.Name);
        entity.setResponse(new GenericComponent());
        Mockito.when(logbookRepository.save(Mockito.any())).thenReturn(entity);
        getHeldItemsResponse = pokemonController.getHeldItems(getHeldItemsRequest);
    }
    @Then("Must return the help items")
    public void must_return_the_help_items() {
        Assertions.assertEquals(getHeldItemsResponse.getItem().getName(), "ditto");
        Assertions.assertNotNull(getHeldItemsResponse.getItem());
    }
}
