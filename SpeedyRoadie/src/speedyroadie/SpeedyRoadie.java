/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyroadie;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Louis Dhanis
 */
public class SpeedyRoadie extends Application {
    private Stage stage;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(SpeedyRoadie.class, args);
    }
    
    @Override

    public void start(Stage primaryStage) {
        stage = primaryStage;
        Scene scene = accueil();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Scene accueil(){
        Pane root = new Pane();
        Button newRandomGame = new Button("Nouvelle partie aléatoire");
        newRandomGame.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(RandomGame());
            }
        });
        root.getChildren().add(newRandomGame);
        return new Scene(root);
    }
    
    protected Scene RandomGame(){
        Pane root = new Pane();
        Label newGame = new Label("NOUVELLE PARTIE");
        root.getChildren().add(newGame);
        return new Scene(root);
    }
    
}
