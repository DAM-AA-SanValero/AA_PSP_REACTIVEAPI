package com.svalero.controller;

import com.svalero.model.CharacterManager;
import com.svalero.model.Characters;
import com.svalero.model.Perks;
import com.svalero.task.CharacterTask;
import com.svalero.task.PerkTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.util.List;
import java.util.stream.Collectors;

public class PerksController {


    @FXML
    private ProgressBar loadAllPerks;
    @FXML
    private Label percentageProgress;
    @FXML
    private ListView<String> resultList;
    private ObservableList<String> results;

    private PerkTask perkTask;

    private ObservableList<Perks> allPerks;
    private FilteredList<Perks> filteredPerks;

    private CharacterManager characterManager;

    public PerksController() {
        this.results = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        this.characterManager = new CharacterManager();

        this.results.clear();
        this.resultList.setItems(this.results);
        this.perkTask = new PerkTask(this.results, characterManager);

        loadAllPerks.progressProperty().bind(perkTask.progressProperty());
        this.perkTask.progressProperty().addListener((observableValue, oldValue, newValue) -> {
            double percentage;
            if (newValue == null) {
                percentage = 0.0;
            } else {
                percentage = newValue.doubleValue();
            }
            percentageProgress.setText(String.format("%.0f%%", percentage * 100));
        });
        this.perkTask.setOnSucceeded(event -> {
            allPerks = FXCollections.observableArrayList(perkTask.getValue());
            filteredPerks = new FilteredList<>(allPerks);
        });
        new Thread(perkTask).start();
    }

    @FXML
    public void filterBySurvivor(){
        filteredPerks.setPredicate(perk -> {
            if(perk.getRole().equals("survivor")){
                return true;
            }
            return false;
        });

        List<String> stringPerks = filteredPerks
                .stream()
                .map(perk
                        -> perk.getName()
                        + " ("+ characterManager.getNameById(perk.getCharacter()) +") -> "
                        + perk.getRole().toUpperCase())
                .collect(Collectors.toList());
        resultList.setItems(FXCollections.observableArrayList(stringPerks));
    }

    @FXML
    public void filterByKiller(){
        filteredPerks.setPredicate(perk -> {
            if(perk.getRole().equals("killer")){
                return true;
            }
            return false;
        });

        List<String> stringPerks = filteredPerks
                .stream()
                .map(perk
                        -> perk.getName()
                        + " ("+ characterManager.getNameById(perk.getCharacter()) +") -> "
                        + perk.getRole().toUpperCase())
                .collect(Collectors.toList());
        resultList.setItems(FXCollections.observableArrayList(stringPerks));
    }

}
