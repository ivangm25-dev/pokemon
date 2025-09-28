package wigm.pokemon.service;

import co.pokeapi.api.v2.pokemon.GetAbilitiesResponse;
import co.pokeapi.api.v2.pokemon.GetBaseExperienceResponse;
import co.pokeapi.api.v2.pokemon.GetHeldItemsResponse;
import co.pokeapi.api.v2.pokemon.GetIdResponse;
import co.pokeapi.api.v2.pokemon.GetLocationResponse;
import co.pokeapi.api.v2.pokemon.GetNameResponse;

public interface PokemonService {

    /**
     *
     * @param name
     * @return
     */
    GetAbilitiesResponse abilities(String name);

    /**
     *
     * @param name
     * @return
     */
    GetBaseExperienceResponse baseExperience(String name);

    /**
     *
     * @param name
     * @return
     */
    GetHeldItemsResponse heldItems(String name);

    /**
     *
     * @param name
     * @return
     */
    GetIdResponse id(String name);

    /**
     *
     * @param name
     * @return
     */
    GetNameResponse name(String name);

    /**
     *
     * @param name
     * @return
     */
    GetLocationResponse locationAreaEncounters(String name);
}
