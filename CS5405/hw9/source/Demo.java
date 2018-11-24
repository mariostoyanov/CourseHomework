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
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.io.File;
import javafx.collections.FXCollections;

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
    
    Text t1,t2,t3,t4;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13;

    ImageView authorImage;

    File a1,a2,a3,a4,v1,v2,v3,v4;
    Media m1,m2;
    MediaPlayer mp1,mp2;
    MediaView mv1,mv2; 
    ListView<String> alv,vlv;

    boolean pause = true;

    public void start(Stage stage) throws Exception { 
        root = homePage();
        scene = new Scene(root,900,900,Color.GRAY);
        stage.setTitle("Homework 9");
        stage.setScene(scene);
        stage.show();
    }

    public Pane homePage() throws Exception {

        a1 = new File("audio/Despacito.mp3");
        a2 = new File("audio/Better Now.mp3");
        a3 = new File("audio/Delicate.mp3");
        a4 = new File("audio/Sunflower.mp3");

        v1 = new File("video/Despacito.mp4");
        v2 = new File("video/Better Now.mp4");
        v3 = new File("video/Delicate.mp4");
        v4 = new File("video/Sunflower.mp4");

        String[] songTitles = {"Despacito","Better Now","Delicate","Sunflower"};
        File[] songList = {a1,a2,a3,a4};

        alv = new ListView<> (FXCollections.observableArrayList(songTitles));
        alv.setPrefSize(300,200);

        alv.getSelectionModel().selectedItemProperty().addListener(ov ->{
            for (Integer i:
                    alv.getSelectionModel().getSelectedIndices()){
                        //System.out.println(songList[i]);
                        m1 = new Media(songList[i].toURI().toString());
                        mp1 = new MediaPlayer(m1);
                    }
        });
        
        mv1 = new MediaView(mp1);
        mv1.setFitWidth(300);
        mv1.setFitHeight(300);

        String[] videoTitles = {"Despacito","Better Now","Delicate","Sunflower"};
        File[] videoList = {v1,v2,v3,v4};

        vlv = new ListView<> (FXCollections.observableArrayList(videoTitles));
        vlv.setPrefSize(150,200);
        vlv.relocate(600,80);

        vlv.getSelectionModel().selectedItemProperty().addListener(ov ->{
            for (Integer i:
                    vlv.getSelectionModel().getSelectedIndices()){
                        m2 = new Media(videoList[i].toURI().toString());
                        mp2 = new MediaPlayer(m2);
                    }
        });
        
        mv2 = new MediaView(mp2);
        mv2.setFitWidth(300);
        mv2.setFitHeight(300);
        //mv2.relocate(500,500);

        b1 = new Button("Author", new ImageView("images/author.png"));        
        b2 = new Button("Description", new ImageView("images/description.jpg"));        
        b3 = new Button("Reference", new ImageView("images/reference.png")); 
        b4 = new Button("Audio Demo", new ImageView("images/demo.jpg"));
        b9 = new Button("Video Demo", new ImageView("images/demo.jpg"));
        b5 = new Button("Start", new ImageView("images/start.png"));
        b6 = new Button("Stop", new ImageView("images/stop.jpg"));
        b7 = new Button("Pause", new ImageView("images/pause.png"));
        b8 = new Button("Replay", new ImageView("images/reverse.png"));
        b10 = new Button("Start", new ImageView("images/start.png"));
        b11 = new Button("Stop", new ImageView("images/stop.jpg"));
        b12 = new Button("Pause", new ImageView("images/pause.png"));
        b13 = new Button("Replay", new ImageView("images/reverse.png"));      

        t1 = new Text(10,90, "Author Text");
        t1.setText("Homework 9 \nAuthor: Mario Stoyanov \nEmail: msfp6@mst.edu \nPhone: 314-814-7234\nI own all the material in this GUI under copyright");
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
        t3.setText("The Author button should have the author name and email. \nThe Description button should contain problem description \nThe Reference button should list any references used \nThe Audio Demo button should provide an audio demo\nThe Video Demo button should provide a video demo\nEach demo pane should have buttons to control the media");        
        
        t4 = new Text(0, 20, "I used the lecture handouts and JavaFX documentation for reference.");
        t4.setFont(new Font(20));
        t4.setFill(Color.GREEN);
        t4.setWrappingWidth(600);       

        // //MEDIA BUTTONS
        b5.setOnAction(ae->{
            mp1.play();
        });
        
        b6.setOnAction(ae->{
            mp1.stop();
        });

        b7.setOnAction(ae->{
            pause = !pause;
            if(pause){
                mp1.play();
            }
            else{
                mp1.pause();
            }
        });

        b8.setOnAction(ae->{
            mp1.stop();
            mp1.play();
        });

        b10.setOnAction(ae->{
            mp2.play();
        });
        
        b11.setOnAction(ae->{
            mp2.stop();
        });

        b12.setOnAction(ae->{
            pause = !pause;
            if(pause){
                mp2.play();
            }
            else{
                mp2.pause();
            }
        });

        b13.setOnAction(ae->{
            mp2.stop();
            mp2.play();
        });

        //PANE ORGANIZATION
        pane1.relocate(10,50);        
        pane2.relocate(10,50);        
        pane3.relocate(10,50);        
        pane4.relocate(10,100);       
        
        b1.relocate(0,20);        
        b2.relocate(170,20);        
        b3.relocate(365,20);
        b4.relocate(550,20);
        b5.relocate(0,350);
        b6.relocate(150,350);
        b7.relocate(300,350);
        b8.relocate(450,350);
        b9.relocate(750,20);
        b10.relocate(0,350);
        b11.relocate(150,350);
        b12.relocate(300,350);
        b13.relocate(450,350);

        pane1.getChildren().addAll(t1,authorImage);        
        pane2.getChildren().addAll(t2,t3);        
        pane3.getChildren().addAll(t4);
        pane5.getChildren().addAll(b5,b6,b7,b8,mv1,alv);
        pane6.getChildren().addAll(b10,b11,b12,b13,mv2,vlv);       
        pane4.getChildren().addAll(pane1);

        root.getChildren().addAll(b1, b2, b3, b4, b9, pane4);        
        b1.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane1);});        
        b2.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane2);});        
        b3.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane3);});
        b4.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane5);}); 
        b9.setOnAction(ae->{pane4.getChildren().clear();pane4.getChildren().add(pane6);});        
        
        return root; 
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}