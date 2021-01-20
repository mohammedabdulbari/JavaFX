/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inputform;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import javafx.geometry.Pos;
import javafx.scene.text.*;

/**
 *
 * @author abdulbari
 */
class Customer
{
    private int custid;
    private String name;
    private String address;
    
    public Customer(int c,String n,String a)
    {
        custid=c;
        name=n;
        address=a;
    }
    public int getID(){return custid;}
    public String getName(){return name;}
    public String getAddress(){return address;}
    public void setAddress(String add){address=add;}
}

        
public class InputForm extends Application {
    
    int count=0;
    @Override
    public void start(Stage primaryStage) {
        HashMap<Integer,Customer> data=new HashMap<>();
        ComboBox<Integer> cb=new ComboBox<>();
        
        Label l1=new Label("Cutomer ID");
        Label l2=new Label("Name");
        Label l3=new Label("Address");

        TextField name=new TextField();
        name.setPrefColumnCount(15);
        TextField add=new TextField();
        add.setPrefColumnCount(20);
        Button create=new Button("Create");
        create.setOnAction(e->{name.setText("");
        add.setText("");
        cb.setValue(++count);
        });
        Button save=new Button("Save");
        save.setOnAction(e->{data.put(count, new Customer(count,name.getText(),add.getText()));
        try(PrintStream ps=new PrintStream(new FileOutputStream("Customer.txt")))
        {
            for(Customer cust:data.values())
            {
                ps.println(cust.getID());
                ps.println(cust.getName());
                ps.println(cust.getAddress());
                
            }
        }
        catch(Exception ex){}
        });
        Button search=new Button("Search");
        search.setOnAction(e->{
        if(cb.getValue()!=null)
        {
            int c=cb.getValue();
            Customer cust=data.get(c);
            name.setText(cust.getName());
            add.setText(cust.getAddress());
        }
        });
        cb.valueProperty().addListener(e->{
        if(cb.getValue()!=null)
        {
            int c=cb.getValue();
            Customer cust=data.get(c);
            name.setText(cust.getName());
            add.setText(cust.getAddress());
        }
        });
        
        
        try(Scanner sc=new Scanner(new FileInputStream("Customer.txt")))
        {
            int c;
            String n=null,a;
            while(sc.hasNext())
            {
                c=sc.nextInt();
                n=sc.next();
                a=sc.next();
                data.put(c, new Customer(c,n,a));
                cb.getItems().add(c);
                if(c>count)count=c;
            }
        }
        catch(Exception ex){}
        
        Font f=new Font("Ariel",20);
        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        name.setFont(f);
        add.setFont(f);
        create.setFont(f);
        save.setFont(f);
        search.setFont(f);
        cb.setStyle("-fx-font-size:20");
        VBox vb=new VBox();
        HBox hb1=new HBox();
        hb1.getChildren().addAll(l1,cb);
        HBox hb2=new HBox();
        hb2.getChildren().addAll(l2,name);
        HBox hb3=new HBox();
        hb3.getChildren().addAll(l3,add);
        HBox hb4=new HBox();
        hb4.getChildren().addAll(create,save,search);
        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);
        hb4.setAlignment(Pos.CENTER);
        vb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(hb1,hb2,hb3,hb4);
        
        Scene scene = new Scene(vb, 500, 500);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
