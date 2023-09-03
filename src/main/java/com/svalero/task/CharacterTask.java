package com.svalero.task;

import com.svalero.model.Characters;
import com.svalero.service.DbdService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;

public class CharacterTask extends Task<List<Characters>> {
    private ObservableList<String> results;
    private List<Characters> allCharacters;
    private int counter;

    public CharacterTask(ObservableList<String> results) {
        this.results = results;
        this.counter = 0;
    }

    @Override
    protected List<Characters> call() throws Exception {
        try {
            DbdService dbdService = new DbdService();

            dbdService.getSimpleCharacters().subscribe(response -> {
                System.out.println("Response in JSON: " + response);
            }, throwable -> {
                System.out.println("Error: " + throwable.getMessage());
            });


            Consumer<List<Characters>> user = (characters) -> {
                if (characters != null) {
                    int totalCharacters = characters.size();
                    allCharacters = characters;
                    for (Characters character : characters) {
                        this.counter++;
                        Thread.sleep(300);
                        String characterName
                                = "Character: " + character.getName()
                                + " ("+ character.getGender() +") -> "
                                + character.getRole().toUpperCase() + "  |  Height: "
                                + character.getHeight();
                        Platform.runLater(() -> results.add(characterName));
                        updateProgress(counter, totalCharacters);
                    }
                }
            };


            Consumer<Throwable> errorHandler = (throwable) -> {
                System.out.println("Error: " + throwable.getMessage());
            };

            dbdService.getSimpleCharacters().subscribe(user, errorHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allCharacters;
    }

}
