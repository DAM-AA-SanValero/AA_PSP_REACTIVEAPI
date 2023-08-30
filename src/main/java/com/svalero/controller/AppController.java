package com.svalero.controller;

import com.svalero.util.R;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class AppController {

    @FXML
    private ImageView imageChar;
    @FXML
    private TextField inputInfo;
    @FXML
    private TabPane tpInfo;

    @FXML
    public void listCharacters(ActionEvent event){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        CharactersController charactersController = new CharactersController();
        loader.setController(charactersController);
        try{
            VBox dbdApp = loader.load();
            if(tpInfo != null){
                tpInfo.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

                Tab newTab = new Tab("Characters");
                newTab.setContent(dbdApp);
                tpInfo.getTabs().add(newTab);
            } else{
                System.out.println("No muestro nada");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void listPerks(ActionEvent event){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI("results.fxml"));
        PerksController perksController = new PerksController();
        loader.setController(perksController);
        try{
            VBox dbdApp = loader.load();
            if(tpInfo != null){
                tpInfo.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

                Tab newTab = new Tab("Perks");
                newTab.setContent(dbdApp);
                tpInfo.getTabs().add(newTab);
            } else{
                System.out.println("No muestro nada");
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
