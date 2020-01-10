import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.scene.effect.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.collections.*;
import javax.swing.*;
import java.awt.BorderLayout;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.util.Duration;
import java.util.Random;



public class reports extends Application
{
	
	private Canvas cv;
	private Color clr;
	private boolean flag = false;
	
	/**********top-label**********/
	Label lb_top_left_ans = new Label("xss");
	Label lb_top_right_rem = new Label("10");
	/**********top-label end**********/
	
	/**********problem-label**********/
	Label lb_problem = new Label("ここに問題が出ます");
	/**********problem-label end**********/
	
	/**********start btn**********/
	Button btn_bottom_left = new Button("s\nt\na\nr\nt");
	Button btn_bottom_right = new Button("s\nt\na\nr\nt");
	/**********start btn end**********/
	
	/**********key button gridpane**********/
	Button[] btn = new Button[11];
	/**********key button gridpane end**********/
	
	/**********textfile**********/
	TextField text = new TextField();
	/**********textfile end**********/
	
	public void start(Stage stage) throws Exception
	{
		
		/******************************ラジオボタン＆キャンバス******************************/
		RadioButton[] rbs = new RadioButton[5];
		rbs[0] = new RadioButton("赤色");
		rbs[1] = new RadioButton("橙色");
		rbs[2] = new RadioButton("青色");
		rbs[3] = new RadioButton("黄色");
		rbs[4] = new RadioButton("緑色");
		rbs[0].setId("red");
		rbs[1].setId("orange");
		rbs[2].setId("blue");
		rbs[3].setId("yellow");
		rbs[4].setId("green");
		
		ToggleGroup tg = new ToggleGroup();
		rbs[0].setToggleGroup(tg);
		rbs[1].setToggleGroup(tg);
		rbs[2].setToggleGroup(tg);
		rbs[3].setToggleGroup(tg);
		rbs[4].setToggleGroup(tg);
		
		radio_button actionhandler = new radio_button();
		rbs[0].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[1].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[2].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[3].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[4].addEventHandler(ActionEvent.ANY, actionhandler);
		
		cv = new Canvas(600,30);
		clr = Color.BLACK;
		drawCanvas();
		
		HBox hb_rbs = new HBox();
		hb_rbs.getChildren().addAll(rbs);
		hb_rbs.setPadding(new Insets(10));
		hb_rbs.setSpacing(10);
		
		hb_rbs.getStyleClass().add("hb_rbs");
		
		/******************************ラジオボタン＆キャンバス end******************************/
		
		
		/******************************top-label******************************/
		
		Label lb_top_left = new Label("正解");
		Label lb_top_left_q = new Label("問");
		
		Label lb_top_right = new Label("残り");
		Label lb_top_right_q = new Label("秒");
		
		lb_top_left.setFont(new Font(15));
		lb_top_left_ans.setFont(new Font(25));
		lb_top_left_q.setFont(new Font(25));
		lb_top_right.setFont(new Font(15));
		lb_top_right_rem.setFont(new Font(25));
		lb_top_right_q.setFont(new Font(25));
		
		lb_top_left.setPrefSize(70, 0);
		lb_top_left_ans.setPrefSize(50, 80);
		lb_top_left_q.setPrefSize(170, 80);
		lb_top_right.setPrefSize(70, 0);
		lb_top_right_rem.setPrefSize(50, 80);
		lb_top_right_q.setPrefSize(30, 80);
		
		HBox hb_top = new HBox();
		hb_top.getChildren().add(lb_top_left);
		hb_top.getChildren().add(lb_top_left_ans);
		hb_top.getChildren().add(lb_top_left_q);
		hb_top.getChildren().add(lb_top_right);
		hb_top.getChildren().add(lb_top_right_rem);
		hb_top.getChildren().add(lb_top_right_q);
		
		hb_top.setPadding(new Insets(5, 15, 0, 30));
		
		/******************************top-label end******************************/
		
		
		/******************************problem-label******************************/
		
		lb_problem.setFont(new Font(70));
		lb_problem.setMaxWidth(Double.MAX_VALUE);
        lb_problem.setAlignment(Pos.CENTER);
		
		/******************************problem-label end******************************/
		
		
		/******************************textfile******************************/
		
		text.setMaxWidth(380);
		text.setMinHeight(30);
		text.setFont(new Font(15));
		text.setAlignment(Pos.TOP_RIGHT);
		text.setPromptText("キーボードから入力する場合はこちらから");
		
		VBox vb_text = new VBox();
		vb_text.getChildren().add(text);
		vb_text.setAlignment(Pos.CENTER);
		vb_text.setPadding(new Insets(15, 0, 0, 0));
		
		/******************************textfile end******************************/
		
		
		/******************************key button gridpane******************************/
		
		GridPane gp = new GridPane();
		BorderPane bp = new BorderPane();
		
		for(int i=0;i<10;i++){
			btn[i] = new Button(String.valueOf(i));
			btn[i].setFont(new Font(30));
			btn[i].setPrefSize(80, 80);
		}
		
		btn[10] = new Button("enter!!!");
		btn[10].setFont(new Font(30));
		btn[10].setPrefSize(160, 80);
		btn[10].setDisable(true);
		
		gp.add(btn[1], 0, 0);
		gp.add(btn[2], 1, 0);
		gp.add(btn[3], 2, 0);
		gp.add(btn[4], 0, 1);
		gp.add(btn[5], 1, 1);
		gp.add(btn[6], 2, 1);
		gp.add(btn[7], 0, 2);
		gp.add(btn[8], 1, 2);
		gp.add(btn[9], 2, 2);
		gp.add(btn[0], 0, 3);
		gp.add(btn[10], 1, 3, 2, 1);
		
		EventHandler_btn actionhandler_btn = new EventHandler_btn();
		for(int i=0;i<11;i++){
			
			btn[i].setId(String.valueOf(i));
			btn[i].addEventHandler(ActionEvent.ANY, actionhandler_btn);
		}
		
		/******************************start btn******************************/
		
		btn_bottom_left.setFont(new Font(30));
		btn_bottom_right.setFont(new Font(30));
		btn_bottom_left.setPrefSize(70, 329);
		btn_bottom_right.setPrefSize(70, 329);
		
		
		VBox vb_btn_left = new VBox();
		VBox vb_btn_right = new VBox();
		vb_btn_left.getChildren().add(btn_bottom_left);
		vb_btn_right.getChildren().add(btn_bottom_right);
		
		
		EventHandler_start_btn actionhandler_start_btn = new EventHandler_start_btn();
		vb_btn_left.addEventHandler(ActionEvent.ANY, actionhandler_start_btn);
		vb_btn_right.addEventHandler(ActionEvent.ANY, actionhandler_start_btn);
		
		/******************************start btn end******************************/
		
		bp.setCenter(gp);
		bp.setLeft(vb_btn_left);
		bp.setRight(vb_btn_right);
		
		gp.setHgap(3);			//ボタン間の空白
		gp.setVgap(3);

		bp.setPadding(new Insets(20, 0, 30, 0));
		vb_btn_left.setPadding(new Insets(0, 39, 0, 39));
		vb_btn_right.setPadding(new Insets(0, 39, 0, 39));
		
		gp.setAlignment(Pos.CENTER);
		vb_btn_left.setAlignment(Pos.CENTER);
		vb_btn_right.setAlignment(Pos.CENTER);
		
		
		/******************************key button gridpane end******************************/
				
		
		VBox vb = new VBox();
		vb.getChildren().add(hb_rbs);
		vb.getChildren().add(hb_top);
		vb.getChildren().add(lb_problem);
		vb.getChildren().add(cv);
		vb.getChildren().add(vb_text);
		vb.getChildren().add(bp);
		
		vb.getStyleClass().add("vb");
		
		Scene scene = new Scene(vb);
		
		scene.getStylesheets().add("style.css");
		
		stage.setScene(scene);
		stage.setTitle("二則演算ゲーム");
		stage.setWidth(550);
		stage.setHeight(650);
		
		stage.show();
}


	/******************************canvas******************************/
	private void drawCanvas(){
		
		GraphicsContext gc = cv.getGraphicsContext2D();
		
		gc.setFill(clr);
		gc.setFont(new Font(30));
		gc.fillText("＼(^o^)／ 正解 ＼(^o^)／", 93, 25);
	}
	/******************************canvas end******************************/
	
	
	/******************************problem******************************/
	public void problem(){
		lb_problem.setText("xss");
	
	}
	/******************************problem end******************************/
	
	
	/******************************enter btn******************************/
	private class EventHandler_btn implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			Button bt = (Button)e.getTarget();
			
			for(int i=0;i<10;i++){
				if(bt.getId().equals(String.valueOf(i))) System.out.println(String.valueOf(i));
			}
			
			if(bt.getId().equals("10")) {
				
				Random rand = new Random();
				
				int a = rand.nextInt(100);
				int b = rand.nextInt(100);
				
				int arithmetic_ope = rand.nextInt(2);
				
				
				if(arithmetic_ope == 0) lb_problem.setText(a + " + " + b);
				else lb_problem.setText(a + " - " + b);
			}
		}
	}
	/******************************enter btn end******************************/
	
	
	/******************************start btn event******************************/
	
	private class EventHandler_start_btn implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			
			btn[10].setDisable(false);
			
			Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
	        @Override
	        public void handle(ActionEvent event) {
				
	        		lb_top_right_rem.setText(String.valueOf(Integer.parseInt(lb_top_right_rem.getText()) - 1));
					
					if((Integer.parseInt(lb_top_right_rem.getText()) - 1) < 30){			//start btnの無効化
						
						btn_bottom_left.setDisable(true);
						btn_bottom_right.setDisable(true);
					}
					
					if((Integer.parseInt(lb_top_right_rem.getText()) - 1) <= -1){			//textfiled key-btnの無効化
						
						text.setDisable(true);
						
						for(int i=0;i<11;i++){
							btn[i].setDisable(true);
						}
					}
	            }
	        }));
			
			timer.setCycleCount(10);
	    	timer.play();
			
		}
	}
	
	/******************************start btn event end******************************/
	
	/******************************radio_button******************************/
	private class radio_button implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			RadioButton target = (RadioButton)e.getTarget();
			String id = target.getId();
			if(id.equals("red")) clr = Color.RED;
			if(id.equals("orange")) clr = Color.ORANGE;
			if(id.equals("blue")) clr = Color.BLUE;
			if(id.equals("yellow")) clr = Color.YELLOW;
			if(id.equals("green")) clr = Color.MEDIUMSPRINGGREEN;
			drawCanvas();
		}
	}
	/******************************radio_button end******************************/

	public static void main(String[] args)
	{
		launch(args);
	}
}