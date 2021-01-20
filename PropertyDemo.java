package propertydemo;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;


public class PropertyDemo extends Application
{
    public void start(Stage stage) throws Exception
    {
        Button b=new Button("Click _Me");
        b.setTextFill(Color.BLUE);
        b.setMnemonicParsing(true);
        Tooltip tp=new Tooltip("Click this Button to Save Data");
        b.setTooltip(tp);
       // b.setStyle("-fx-border-color:yellow;-fx-background-color:red
        Alert a=new Alert(AlertType.INFORMATION,"Button is Clicked");
       
       b.setOnAction(e->a.show());
       
        FlowPane fp=new FlowPane(b);
        
        Scene sc=new Scene(fp,400,400);
        stage.setScene(sc);
        stage.show();
        
        
    }
    public static void main(String[] args) 
    {
        launch(args);
    }   
}
