package com.svalero.task;

import com.svalero.model.CharacterManager;
import com.svalero.model.Characters;
import com.svalero.model.Perks;
import com.svalero.service.DbdService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;
import java.util.Map;

public class PerkTask extends Task<Integer> {

    private ObservableList<String> results;
    private int counter;

    private CharacterManager characterManager;

    public PerkTask(ObservableList<String> results, CharacterManager characterManager) {
        this.results = results;
        this.characterManager = characterManager;
        this.counter = 0;

    }
    @Override
    protected Integer call() throws Exception {
        try {
            DbdService dbdService = new DbdService();
            dbdService.getPerks().subscribe(response -> {
                System.out.println("Response in JSON: " + response);
            }, throwable -> {
                System.out.println("Error: " + throwable.getMessage());
            });

            Consumer<List<Perks>> user = (perks) -> {
                if (perks != null) {
                    int totalPerks = perks.size();
                    for (Perks perk : perks) {
                        this.counter++;
                        Thread.sleep(300);
                        String perkName
                                = "Perk: " + perk.getName()
                                + " ("+ characterManager.getNameById(perk.getCharacter())+") -> "
                                + perk.getRole().toUpperCase();

                        updateMessage(this.counter + " Perks found");
                        Platform.runLater(() -> results.add(perkName));
                        updateProgress(counter, totalPerks);
                    }
                }
            };

            Consumer<Throwable> errorHandler = (throwable) -> {
                System.out.println("Error: " + throwable.getMessage());
            };

            dbdService.getPerks().subscribe(user, errorHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
