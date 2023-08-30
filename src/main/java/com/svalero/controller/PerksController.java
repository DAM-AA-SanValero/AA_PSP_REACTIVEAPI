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

public class PerksController {

    @FXML
    private Label lbFounded;
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
        this.perkTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lbFounded.setText(newValue));
        new Thread(perkTask).start();
    }

}
