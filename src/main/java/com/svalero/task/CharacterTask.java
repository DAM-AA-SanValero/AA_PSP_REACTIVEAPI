package com.svalero.task;

import com.svalero.model.Characters;
import com.svalero.service.DbdService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;

public class CharacterTask extends Task<Integer> {

    private String requestedCharacter;

    private ObservableList<String> results;

    private int counter;

    public CharacterTask(ObservableList<String> results) {
        this.results = results;
        this.counter = 0;
    }

    @Override
    protected Integer call() throws Exception {
        try {
            DbdService dbdService = new DbdService();

            dbdService.getSimpleCharacters().subscribe(response -> {
                System.out.println("Response JSON: " + response); // Imprime la respuesta JSON
            }, throwable -> {
                System.out.println("Error: " + throwable.getMessage());
            });


            Consumer<List<Characters>> user = (characters) -> {
                if (characters != null) {
                    for (Characters character : characters) {
                        this.counter++;
                        Thread.sleep(300);
                        String characterName
                                = character.getName() + " - "
                                + character.getRole().toUpperCase() + " - "
                                + character.getGender() + " - "
                                + character.getHeight();
                        updateMessage(this.counter + " characters found");
                        Platform.runLater(() -> results.add(characterName));
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
        return null;
    }

}
