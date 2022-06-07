package frontend;

import backend.Builder;
import backend.BuilderIF;
import backend.GameMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{
	
	private static final int TileSize=50;
	private static int rows=10;
	private static int columns=10;
	private static int nPlayers=4;
	private static Button automatic, stop, pause, next;
	private static Label randResult;
	
	private static BuilderIF builder= new Builder();
	private GameMap map;
	
	private Group tileGroup= new Group();
	
	private static Circle[] players;
	
	private static boolean gameStart=false;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(createContent());
		primaryStage.setTitle("Scale e Serpenti");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private Pane initialize() {
		Pane root = new Pane();
		root.setPrefSize(rows*TileSize, (columns*TileSize)+80);
		root.getChildren().addAll(tileGroup);
		
		int total=TileSize; /**100/(rows*columns);*/
		
		int cardinal=1;
		for(int i=0; i<columns; i++) {
			for( int j=0; j<rows; j++) {
				Tile tile = new Tile(total,total);
				tile.setTranslateX(j*total);
				tile.setTranslateY(i*total);
				tileGroup.getChildren().add(tile);
				tile.setCardinal(cardinal);
				cardinal++;
			}
		}
		
		builder.buildPlayers(nPlayers);
		players=new Circle[nPlayers];
		for(int i=0; i<nPlayers; i++) {
			players[i]=new Circle(12);
			players[i].setId("player "+i);
			players[i].setTranslateX(25);
			players[i].setTranslateY(475);
			
			tileGroup.getChildren().add(players[i]);
		}
		
		automatic= new Button("AUTO");
		next = new Button("NEXT");
		pause = new Button("PAUSE");
		stop = new Button ("STOP");
		
		automatic.setTranslateX(40);
		automatic.setTranslateY(TileSize*rows+40);
		
		next.setTranslateX(TileSize*columns-80);
		next.setTranslateY(TileSize*rows+40);
		
		pause.setTranslateX(120);
		pause.setTranslateY(TileSize*rows+40);
		
		stop.setTranslateX(TileSize*columns-160);
		stop.setTranslateY(TileSize*rows+40);
		
		automatic.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				
			}
			
		});
		
		randResult=new Label("0");
		randResult.setTranslateX(150);
		randResult.setTranslateY(TileSize*rows+20);
		
		ImageView snake = new ImageView();
		snake.setImage(new Image("snake.png"));
		snake.setFitHeight(TileSize*rows);
		snake.setFitWidth(TileSize*columns);
		
		tileGroup.getChildren().addAll(snake, automatic, next, pause, stop, randResult);
		
		return root;
	}
	
	private Parent createContent() {
		
		Pane root=initialize();
		
		return root;
	}
	
	public static void main(String[]args) { launch(args);}
	

}
