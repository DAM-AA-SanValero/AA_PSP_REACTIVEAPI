package com.svalero.controller;

import com.svalero.model.CharacterManager;
import com.svalero.model.Perks;
import com.svalero.task.CharacterTask;
import com.svalero.task.PerkTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

public class PerksController {


    @FXML
    private ProgressBar loadAllPerks;
    @FXML
    private Label percentageProgress;
    @FXML
    private ListView<String> resultList;
    private ObservableList<String> results;

    private PerkTask perkTask;

    public PerksController() {
        this.results = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize(){
        CharacterManager characterManager = new CharacterManager();

        this.results.clear();
        this.resultList.setItems(this.results);
        this.perkTask = new PerkTask(this.results, characterManager);

        loadAllPerks.progressProperty().bind(perkTask.progressProperty());
        this.perkTask.progressProperty().addListener((observableValue, oldValue, newValue) -> {
            double percentage;
            if(newValue == null){
                percentage = 0.0;
            } else {
                percentage = newValue.doubleValue();
            }
            percentageProgress.setText(String.format("%.0f%%", percentage * 100));
        });
        new Thread(perkTask).start();
    }

}
