package com.svalero.controller;

import com.opencsv.CSVWriter;
import com.svalero.model.Characters;
import com.svalero.task.CharacterTask;
import com.svalero.util.ZipFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CharactersController {
    @FXML
    private ProgressBar loadAllCharacters;
    @FXML
    private Label percentageProgress;
    @FXML
    private ListView<String> resultList;
    private ObservableList<String> results;
    private CharacterTask characterTask;
    private ObservableList<Characters> allCharacters;
    private FilteredList<Characters> filteredCharacters;

    public CharactersController() {
        this.results = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize(){
        this.results.clear();
        this.resultList.setItems(this.results);
        this.characterTask = new CharacterTask(this.results);

        loadAllCharacters.progressProperty().bind(characterTask.progressProperty());
        this.characterTask.progressProperty().addListener((observableValue, oldValue, newValue) -> {
            double percentage;
            if(newValue == null){
                percentage = 0.0;
            } else {
                percentage = newValue.doubleValue();
            }
           percentageProgress.setText(String.format("%.0f%%", percentage * 100));
        });
        this.characterTask.setOnSucceeded(event -> {
            allCharacters = FXCollections.observableArrayList(characterTask.getValue());
            filteredCharacters = new FilteredList<>(allCharacters);
    });
        new Thread(characterTask).start();
    }

    @FXML
    public void filterByGenderMale(){
        filteredCharacters.setPredicate(characters -> {
            if(characters.getGender().equals("male")){
                return true;
            }
            return false;
        });

        List<String> stringCharacters = filteredCharacters
                .stream()
                .map(character
                        -> character.getName()
                        + " ("+ character.getRole() +") -> "
                        + character.getGender().toUpperCase())
                .collect(Collectors.toList());
        resultList.setItems(FXCollections.observableArrayList(stringCharacters));
    }

    @FXML
    public void filterByGenderFemale(){
        filteredCharacters.setPredicate(characters -> {
            if(characters.getGender().equals("female")){
                return true;
            }
            return false;
        });

        List<String> stringCharacters = filteredCharacters
                .stream()
                .map(character
                        -> character.getName()
                        + " ("+ character.getRole() +") -> "
                        + character.getGender().toUpperCase())
                .collect(Collectors.toList());
        resultList.setItems(FXCollections.observableArrayList(stringCharacters));
    }

    @FXML
    public void exportCSV(ActionEvent event) {
        String outputFileName = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "Characters.csv";

        File outputFile = new File(outputFileName);
        try {
            FileWriter writer = new FileWriter(outputFile);
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> data = new ArrayList<String[]>();
            for (String definition : this.results){
                data.add(new String[] {definition, String.valueOf(Math.random())});
            }
            csvWriter.writeAll(data);
            csvWriter.close();
            ZipFile.createZipFile(outputFileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
