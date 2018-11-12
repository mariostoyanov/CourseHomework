/**
	Mario Stoyanov
	CS5405 - Java GUIs
	Homework 8
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
import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import javafx.geometry.Orientation;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Demo extends Application{
    Pane pane1 = new Pane();
    Pane pane2 = new Pane();
    Pane pane3 = new Pane();
    Pane pane4 = new Pane();
    Pane pane5 = new Pane();
    Pane pane6 = new Pane();
    Pane root = new Pane();

    Scene scene;
    Stage stage;
    
    Text t1,t2,t3,t4,nft,nst;
    Button b1,b2,b3,b4,b5,b6,b7,b8;
    Circle c1,c2,c3;
    Arc a1,a2,a3,a4;
    int numberFanBlades, speedOfFan;
    int direction = 360;
    boolean pause = true;

    ImageView authorImage; 

    Slider numFans, speed;
    
    RotateTransition fanRotation;

    public void start(Stage stage) throws Exception { 
        root = homePage();
        scene = new Scene(root,800,700,Color.GRAY);
        stage.setTitle("Homework 8");
        stage.setScene(scene);
        stage.show();
    }

    public Pane homePage() throws Exception {

        b1 = new Button("Author", new ImageView("images/author.png"));        
        b2 = new Button("Description", new ImageView("images/description.jpg"));        
        b3 = new Button("Reference", new ImageView("images/reference.png")); 
        b4 = new Button("Demo", new ImageView("images/demo.jpg"));
        b5 = new Button("Start", new ImageView("images/start.png"));
        b6 = new Button("Stop", new ImageView("images/stop.jpg"));
        b7 = new Button("Pause", new ImageView("images/pause.png"));
        b8 = new Button("Reverse", new ImageView("images/reverse.png"));    

        t1 = new Text(10,90, "Author Text");
        t1.setText("Homework 8 \nAuthor: Mario Stoyanov \nEmail: msfp6@mst.edu \nPhone: 314-814-7234\nI own all the material in this GUI under copyright");
        t1.setFont(new Font(20));
        t1.setFill(Color.BLUE);
        t1.setWrappingWidth(600);
        t1.setTextAlignment(TextAlignment.CENTER);

        authorImage = new ImageView("images/mario.jpg");
        authorImage.setFitHeight(250);
        authorImage.setFitWidth(200);
        authorImage.relocate(210,210);

        t2 = new Text(0, 20, "Description Text");
        t2.setFont(new Font(20));
        t2.setFill(Color.RED);              
        t2.setWrappingWidth(500);               
        t2.setText("Homework 8 should contain a four buttons.");
        
        t3 = new Text(20,40, "Description of Description");
        t3.setFont(new Font(15));
        t3.setFill(Color.RED); 
        t3.setWrappingWidth(600);
        t3.setText("The Author button should have the author name and email. \nThe Description button should contain problem description \nThe Reference button should list any references used \nThe Demo button should provide a demo of the fan\nThe fan should have start, stop, pause, and reverse buttons for the animation\nThere should be a slider to control the number of blades\nThere should be a slider to control the fan speed");        
        
        t4 = new Text(0, 20, "I used the lecture handouts and JavaFX documentation for reference.");
        t4.setFont(new Font(20));
        t4.setFill(Color.GREEN);
        t4.setWrappingWidth(600);       

        c1 = new Circle();
        c2 = new Circle(); 
        c3 = new Circle();
        drawFan(0);

        //DEFAULT ANIMATION VALUES 
        fanRotation = new RotateTransition(Duration.seconds(10), pane6);
        fanRotation.setByAngle(direction);
        fanRotation.setCycleCount(Animation.INDEFINITE);

        //SLIDER FOR NUMBER OF BLADES
        nft = new Text(350, 60, "Number of fan blades: ");
        nft.setFont(new Font(15));
        nft.setFill(Color.BLUE);
        numFans = new Slider(3,10,0);
        numFans.setMajorTickUnit(1);
        numFans.setMinorTickCount(0);
        numFans.setOrientation(Orientation.HORIZONTAL);
        numFans.setSnapToTicks(true);
        numFans.setShowTickLabels(true);
        numFans.setShowTickMarks(true);
        numFans.relocate(550,50);
        numFans.setScaleX(1.5);
        numFans.setScaleY(1.5);

        numFans.valueProperty().addListener(nfb->{
            pane6.getChildren().clear();
            numberFanBlades = (int) numFans.getValue();
            drawFan(numberFanBlades);
        });

        //SLIDER FOR ROTATION SPEED
        nst = new Text (350, 120, "Speed of fan: ");
        nst.setFont(new Font(15));
        nst.setFill(Color.BLUE);
        speed = new Slider(1,10,0);
        speed.setMajorTickUnit(1);
        speed.setMinorTickCount(0);
        speed.setOrientation(Orientation.HORIZONTAL);
        speed.setSnapToTicks(true);
        speed.setShowTickLabels(true);
        speed.setShowTickMarks(true);
        speed.relocate(550,120);
        speed.setScaleX(1.5);
        speed.setScaleY(1.5);

        speed.valueProperty().addListener(ss->{
            speedOfFan = (int) speed.getValue();
            fanRotation.setRate(speedOfFan);
        });

        //ANIMATION BUTTONS
        b5.setOnAction(ae->{
            fanRotation.play();
        });
        
        b6.setOnAction(ae->{
            fanRotation.stop();
        });

        b7.setOnAction(ae->{
            pause = !pause;
            if(pause){
                fanRotation.play();
            }
            else{
                fanRotation.pause();
            }
        });

        b8.setOnAction(ae->{
            fanRotation.stop();
            direction = direction * -1; 
            fanRotation.setByAngle(direction);
            fanRotation.play();
        });

        //PANE ORGANIZATION
        pane1.relocate(10,50);        
        pane2.relocate(10,50);        
        pane3.relocate(10,50);        
        pane4.relocate(10,100);
        pane6.relocate(25,25);        
        
        b1.relocate(0,20);        
        b2.relocate(170,20);        
        b3.relocate(365,20);
        b4.relocate(550,20);
        b5.relocate(0,350);
        b6.relocate(150,350);
        b7.relocate(300,350);
        b8.relocate(450,350);

        pane1.getChildren().addAll(t1,authorImage);        
        pane2.getChildren().addAll(t2,t3);        
        pane3.getChildren().addAll(t4);
        pane5.getChildren().addAll(c1,c2,c3,pane6,b5,b6,b7,b8,nft,numFans,nst,speed);        
        pane4.getChildren().addAll(pane1);

        root.getChildren().addAll(b1, b2, b3, b4, pane4);        
        b1.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane1);});        
        b2.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane2);});        
        b3.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane3);});
        b4.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane5);});        
        
        return root; 
    }

    public void drawFan(int numberFanBlades){

        c1.setCenterX(150);
        c1.setCenterY(150);
        c1.setRadius(150);
        c1.setFill(new Color(0,0,0,0));
        c1.setStroke(Color.FUCHSIA);
        c1.setStrokeWidth(3);

        c2.setCenterX(150);
        c2.setCenterY(150);
        c2.setRadius(100);
        c2.setFill(Color.PINK);
        c2.setStroke(Color.FUCHSIA);
        c2.setStrokeWidth(3);

        c3.setCenterX(150);
        c3.setCenterY(150);
        c3.setRadius(50);
        c3.setFill(Color.BLUE);
        c3.setStroke(Color.FUCHSIA);
        c3.setStrokeWidth(3);

        for(int i = 0; i < numberFanBlades; i++){
            int bladeLocation = (360/numberFanBlades) * i;
            Arc arc = new Arc(125, 125, 125, 125, bladeLocation, 30); //125
            arc.setFill(Color.RED);
            arc.setType(ArcType.ROUND);
            pane6.getChildren().add(arc);
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}