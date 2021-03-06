/**
	Mario Stoyanov
	CS5405 - Java GUIs
	Homework 6
**/

package code; 

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.*;
import javafx.scene.input.KeyCode;
import javafx.scene.control.TextField;

public class Demo extends Application{
    Pane pane1 = new Pane();
    Pane pane2 = new Pane();
    Pane pane3 = new Pane();
    Pane pane4 = new Pane();
    Pane pane5 = new Pane();
    Pane root = new Pane();

    Scene scene;
    Stage stage;
    
    Text t1,t2,t3,t4,t5,t6;
    Button b1, b2, b3, b4;
    Circle c1,c2,c3;
    Arc a1,a2,a3,a4;

    public void start(Stage stage) throws Exception { 
        root = homePage();
        scene = new Scene(root,700,600,Color.GRAY);
        stage.setTitle("Homework 6");
        stage.setScene(scene);
        stage.show();
    }

    public Pane homePage() throws Exception {

        t1 = new Text(10,90, "Author Text");
        t1.setText("Homework 6 \nAuthor: Mario Stoyanov \nEmail: msfp6@mst.edu");
        t1.setFont(new Font(20));
        t1.setFill(Color.BLUE);
        t1.setWrappingWidth(600);
        t1.setTextAlignment(TextAlignment.CENTER);

        t2 = new Text(0, 20, "Description Text");
        t2.setFont(new Font(20));
        t2.setFill(Color.RED);              
        t2.setWrappingWidth(500);               
        t2.setText("Homework 6 should contain a four buttons.");
        
        t3 = new Text(20,40, "Description of Description");
        t3.setFont(new Font(15));
        t3.setFill(Color.RED); 
        t3.setWrappingWidth(600);
        t3.setText("The Author button should have the author name and email. \nThe Description button should contain problem description \nThe Reference button should list any references used \nThe Demo button should provide a demo of the fan");        
        
        t4 = new Text(0, 20, "I did not use any reference");
        t4.setFont(new Font(20));
        t4.setFill(Color.GREEN);
        t4.setWrappingWidth(600);       

        t5 = new Text(0, -10, "Fan Demo");
        t5.setFont(new Font(20));
        t5.setFill(Color.CYAN);
        t5.setWrappingWidth(600);

        //Begin fan creation
        c1 = new Circle();
        c1.setCenterX(150);
        c1.setCenterY(150);
        c1.setRadius(150);
        c1.setFill(new Color(0,0,0,0));
        c1.setStroke(Color.FUCHSIA);
        c1.setStrokeWidth(3);

        c2 = new Circle();
        c2.setCenterX(150);
        c2.setCenterY(150);
        c2.setRadius(100);
        c2.setFill(Color.PINK);
        c2.setStroke(Color.FUCHSIA);
        c2.setStrokeWidth(3);

        c3 = new Circle();
        c3.setCenterX(150);
        c3.setCenterY(150);
        c3.setRadius(50);
        c3.setFill(Color.BLUE);
        c3.setStroke(Color.FUCHSIA);
        c3.setStrokeWidth(3);

        a1 = new Arc(150,150,125,125,50,30);
        a1.setFill(Color.RED);
        a1.setType(ArcType.ROUND);

        a2 = new Arc(150,150,125,125,50 + 90,30);
        a2.setFill(Color.RED);
        a2.setType(ArcType.ROUND);

        a3 = new Arc(150,150,125,125,50 + 180,30);
        a3.setFill(Color.RED);
        a3.setType(ArcType.ROUND);

        a4 = new Arc(150,150,125,125,50 + 270,30);
        a4.setFill(Color.RED);
        a4.setType(ArcType.ROUND);
        //End fan creation

        pane1.getChildren().addAll(t1);        
        pane2.getChildren().addAll(t2,t3);        
        pane3.getChildren().addAll(t4);
        pane5.getChildren().addAll(t5,c1,c2,c3,a1,a2,a3,a4);        
        pane4.getChildren().addAll(pane1);

        pane1.relocate(10,50);        
        pane2.relocate(10,50);        
        pane3.relocate(10,50);        
        pane4.relocate(10,100);        
        b1 = new Button("Author");        
        b2 = new Button("Description");        
        b3 = new Button("Reference"); 
        b4 = new Button("Demo");       
        b1.relocate(100,20);        
        b2.relocate(250,20);        
        b3.relocate(400,20);
        b4.relocate(550,20);

        root.getChildren().addAll(b1, b2, b3, b4, pane4);        
        b1.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane1);});        
        b2.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane2);});        
        b3.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane3);});
        b4.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane5);});        
        
        return root; 
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}