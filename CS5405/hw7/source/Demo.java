/**
	Mario Stoyanov
	CS5405 - Java GUIs
	Homework 7
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.beans.property.Property;
import javafx.scene.text.*;
import javafx.scene.shape.Path;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Line;

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

    Text t1,t2,t3,t4,t5,t6;
    Button b1, b2, b3, b4;

    Line line = new Line(); 
    Polyline polyline = new Polyline(); 
 
    double sx,sy,ex,ey,raduius; 

    boolean first = true;

    int isDone = 0;

    public void start(Stage stage) throws Exception { 
        root = homePage();
        scene = new Scene(root,700,600,Color.GRAY);
        stage.setTitle("Homework 7");
        stage.setScene(scene);
        stage.show();
    }

    public Pane homePage() throws Exception {

        t1 = new Text(10,90, "Author Text");
        t1.setText("Homework 7 \nAuthor: Mario Stoyanov \nEmail: msfp6@mst.edu");
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
        t3.setText("The Author button should have the author name and email. \nThe Description button should contain problem description \nThe Reference button should list any references used \nThe Demo button should provide a demo of the polylines drawing");        
        
        t4 = new Text(0, 20, "I did not use any reference");
        t4.setFont(new Font(20));
        t4.setFill(Color.GREEN);
        t4.setWrappingWidth(600);       

        t5 = new Text(0, -10, " ");
        t5.setFont(new Font(20));
        t5.setFill(Color.CYAN);
        t5.setWrappingWidth(600);

        pane1.getChildren().addAll(t1);        
        pane2.getChildren().addAll(t2,t3);        
        pane3.getChildren().addAll(t4);
        pane5.getChildren().addAll(t5);        
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
        
        polyline.setStrokeWidth(3);
        polyline.setStroke(Color.BLUE);
        line.setStroke(Color.RED);

        pane5.getChildren().add(pane6);

        pane5.setOnMouseMoved(e->
            {if(isDone < 2)
                {
                    if (first==false)
                    {
                        ex=e.getX();
                        ey=e.getY();
                        line.setStartX(sx);
                        line.setStartY(sy);
                        line.setEndX(ex);
                        line.setEndY(ey);
                    }
                }
            }
        );
        pane5.setOnMouseClicked(e->{
            double x = e.getX();
            double y = e.getY();
            
           if (isDone < 2)
                if (first)
                {
                    first=false;
                    sx= x;  sy= y;
                    line.setStartX(sx);
                    line.setStartY(sy);
                    line.setEndX(x);
                    line.setEndY(y);
                    polyline.getPoints().addAll(x,y);
                    pane6.getChildren().add(polyline);
                    pane6.getChildren().add(line);
                }
                else
                {
                    ex=x;ey=y;
                    polyline.getPoints().addAll(x,y);
                    sx=x;sy=y;
                }

            if (e.getClickCount()>1)
            {
                ex=e.getX();ey=e.getY();
                isDone = isDone + 1;
                
            }
        }); 

        return root; 
    }
    
    public static void main(String[] args){
        Application.launch(args);
    }
}