package com.example.pokemon2.apires;

import com.example.pokemon2.model.PokemonRes;
import com.example.pokemon2.model.PokemonRes2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Apires {
    @GET("pokemon?limit=151")
    Call<PokemonRes> obtenerPokemon();
    @GET("{id}")
    Call<PokemonRes2> obtenerPokemon2(@Path("id") int groupId);
}
