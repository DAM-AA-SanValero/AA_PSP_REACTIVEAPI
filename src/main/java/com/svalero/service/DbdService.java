package com.svalero.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.svalero.model.Characters;
import com.svalero.model.Perks;
import com.svalero.model.PerksCollection;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbdService {

    static final String BASE_URL = "https://dbd.tricky.lol/";

    private DbdAPI dbdAPI;

    public DbdService(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gsonParser = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gsonParser))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.dbdAPI = retrofit.create(DbdAPI.class);

    }

    public Observable<List<Characters>> getSimpleCharacters() {
        return this.dbdAPI.getCharactersInfo()
                .map(characters -> {
                    if (characters != null) {
                        List<Characters> charactersList = new ArrayList<>();
                        for (Map.Entry<String, Characters> entry : characters.entrySet()) {
                            Characters character = entry.getValue();
                            charactersList.add(character);
                        }
                        return charactersList;
                    } else {
                        throw new NullPointerException("Character list is null");
                    }
                });
    }
    public Observable<List<Perks>> getPerks() {
        return this.dbdAPI.getPerksInfo()
                .map(perks -> {
                    if (perks != null) {
                        List<Perks> perksList = new ArrayList<>();
                        for (Map.Entry<String, Perks> entry : perks.entrySet()) {
                            Perks perk = entry.getValue();
                            perksList.add(perk);
                        }
                        return perksList;
                    } else {
                        throw new NullPointerException("Perk list is null");
                    }
                });
    }






}
