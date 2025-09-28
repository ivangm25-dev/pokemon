package wigm.pokemon.controller;

import co.pokeapi.api.v2.pokemon.GetAbilitiesRequest;
import co.pokeapi.api.v2.pokemon.GetAbilitiesResponse;
import co.pokeapi.api.v2.pokemon.GetBaseExperienceRequest;
import co.pokeapi.api.v2.pokemon.GetBaseExperienceResponse;
import co.pokeapi.api.v2.pokemon.GetHeldItemsRequest;
import co.pokeapi.api.v2.pokemon.GetHeldItemsResponse;
import co.pokeapi.api.v2.pokemon.GetIdRequest;
import co.pokeapi.api.v2.pokemon.GetIdResponse;
import co.pokeapi.api.v2.pokemon.GetLocationRequest;
import co.pokeapi.api.v2.pokemon.GetLocationResponse;
import co.pokeapi.api.v2.pokemon.GetNameRequest;
import co.pokeapi.api.v2.pokemon.GetNameResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import wigm.pokemon.entity.enums.TypeRequest;
import wigm.pokemon.entity.repository.LogbookEntity;
import wigm.pokemon.service.LogbookService;
import wigm.pokemon.service.PokemonService;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

@Endpoint
public class PokemonController {
    private static final String NAMESPACE_URI = "https://pokeapi.co/api/v2/pokemon";
    private PokemonService pokemonService;
    private HttpServletRequest httpServletRequest;
    private LogbookService logbookService;

    public PokemonController(PokemonService pokemonService,
                             LogbookService logbookService,
                             HttpServletRequest httpServletRequest) {
        this.pokemonService = pokemonService;
        this.logbookService = logbookService;
        this.httpServletRequest = httpServletRequest;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getNameRequest")
    @ResponsePayload
    public GetNameResponse getName(@RequestPayload GetNameRequest request) {
        GetNameResponse response = pokemonService.name(request.getName());
        logbookService.save(getIp(), LocalDateTime.now(), TypeRequest.Name, response);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getIdRequest")
    @ResponsePayload
    public GetIdResponse getId(@RequestPayload GetIdRequest request) {
        GetIdResponse response = pokemonService.id(request.getName());
        logbookService.save(getIp(), LocalDateTime.now(), TypeRequest.Id, response);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAbilitiesRequest")
    @ResponsePayload
    public GetAbilitiesResponse getAbilities(@RequestPayload GetAbilitiesRequest request) {
        GetAbilitiesResponse response = pokemonService.abilities(request.getName());
        logbookService.save(getIp(), LocalDateTime.now(), TypeRequest.Abilities, response);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHeldItemsRequest")
    @ResponsePayload
    public GetHeldItemsResponse getHeldItems(@RequestPayload GetHeldItemsRequest request) {
        GetHeldItemsResponse response = pokemonService.heldItems(request.getName());
        logbookService.save(getIp(), LocalDateTime.now(), TypeRequest.HeldItems, response);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBaseExperienceRequest")
    @ResponsePayload
    public GetBaseExperienceResponse getBaseExperience(@RequestPayload GetBaseExperienceRequest request) {
        GetBaseExperienceResponse response = pokemonService.baseExperience(request.getName());
        logbookService.save(getIp(), LocalDateTime.now(), TypeRequest.BaseExperience, response);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLocationRequest")
    @ResponsePayload
    public GetLocationResponse getLocationResponse(@RequestPayload GetLocationRequest request) {
        GetLocationResponse response = pokemonService.locationAreaEncounters(request.getName());
        logbookService.save(getIp(), LocalDateTime.now(), TypeRequest.LocationAreaEncounters, response);
        return response;
    }

    private String getIp(){
        String clientIp = httpServletRequest.getHeader("X-Forwarded-For");
        if (clientIp == null || clientIp.isEmpty() || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = httpServletRequest.getRemoteAddr();
        } else {
            clientIp = clientIp.split(",")[0].trim();
        }
        return clientIp;
    }
}
