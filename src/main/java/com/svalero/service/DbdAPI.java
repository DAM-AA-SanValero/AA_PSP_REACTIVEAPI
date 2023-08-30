package com.svalero.service;

import com.svalero.model.Characters;
import com.svalero.model.Perks;
import com.svalero.model.PerksCollection;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface DbdAPI {
   @GET("/api/characters")
   Observable<Map<String, Characters>> getCharactersInfo();
   @GET("/api/perks")
   Observable<Map<String, Perks>> getPerksInfo();
}
