package wigm.pokemon.service;

import co.pokeapi.api.v2.pokemon.GetAbilitiesResponse;
import co.pokeapi.api.v2.pokemon.GetBaseExperienceResponse;
import co.pokeapi.api.v2.pokemon.GetHeldItemsResponse;
import co.pokeapi.api.v2.pokemon.GetIdResponse;
import co.pokeapi.api.v2.pokemon.GetLocationResponse;
import co.pokeapi.api.v2.pokemon.GetNameResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import wigm.pokemon.client.PokemonClient;
import wigm.pokemon.exception.ServiceFailException;

@Service
public class PokemonServiceImpl implements PokemonService{

    private PokemonClient pokemonClient;
    private  ObjectMapper mapper;

    public PokemonServiceImpl(PokemonClient pokemonClient, ObjectMapper mapper){
        this.pokemonClient = pokemonClient;
        this.mapper = mapper;
    }


    @SneakyThrows
    @Override
    public GetAbilitiesResponse abilities(String name) {
        try {
            GetAbilitiesResponse response =
                    mapper.convertValue(pokemonClient.pokemonInfo(name),
                            GetAbilitiesResponse.class);

            return response;

        }catch (Exception exception){
            throw new ServiceFailException(exception.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public GetBaseExperienceResponse baseExperience(String name) {
        try {
            GetBaseExperienceResponse response =
                    mapper.convertValue(pokemonClient.pokemonInfo(name),
                            GetBaseExperienceResponse.class);

            return response;

        }catch (Exception exception){
            throw new ServiceFailException(exception.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public GetHeldItemsResponse heldItems(String name) {
        try {
            GetHeldItemsResponse response =
                    mapper.convertValue(pokemonClient.pokemonInfo(name),
                            GetHeldItemsResponse.class);

            return response;

        }catch (Exception exception){
            throw new ServiceFailException(exception.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public GetIdResponse id(String name) {
        try {
            GetIdResponse response =
                    mapper.convertValue(pokemonClient.pokemonInfo(name),
                            GetIdResponse.class);

            return response;

        }catch (Exception exception){
            throw new ServiceFailException(exception.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public GetNameResponse name(String name) {
        try {
            GetNameResponse response =
                    mapper.convertValue(pokemonClient.pokemonInfo(name),
                            GetNameResponse.class);

            return response;

        }catch (Exception exception){
            throw new ServiceFailException(exception.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public GetLocationResponse locationAreaEncounters(String name) {
        try {
            GetLocationResponse response =
                    mapper.convertValue(pokemonClient.pokemonInfo(name),
                            GetLocationResponse.class);

            return response;

        }catch (Exception exception){
            throw new ServiceFailException(exception.getMessage());
        }
    }
}
