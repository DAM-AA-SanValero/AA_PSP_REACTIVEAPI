package com.svalero.controller;

import com.svalero.task.CharacterTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


public class CharactersController {

    @FXML
    private Label lbFounded;
    @FXML
    private ListView<String> resultList;
    private ObservableList<String> results;
    private CharacterTask characterTask;

    public CharactersController() {
        this.results = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize(){
        this.results.clear();
        this.resultList.setItems(this.results);
        this.characterTask = new CharacterTask(this.results);
        this.characterTask.messageProperty().addListener((observableValue, oldValue, newValue) -> this.lbFounded.setText(newValue));
        new Thread(characterTask).start();
    }
}
