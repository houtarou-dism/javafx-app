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



public class reports extends Application
{
	private Canvas cv;
	private Color clr;
	
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
		
		/******************************ラジオボタン＆キャンバス end******************************/
		
		
		/******************************top-label******************************/
		
		Label lb_top_left = new Label("正解");
		Label lb_top_left_ans = new Label("xss");
		Label lb_top_right = new Label("残り");
		Label lb_top_right_rem = new Label("xss");
		
		lb_top_left.setFont(new Font(15));
		lb_top_left_ans.setFont(new Font(25));
		lb_top_right.setFont(new Font(15));
		lb_top_right_rem.setFont(new Font(25));
		
		lb_top_left.setPrefSize(70, 0);
		lb_top_left_ans.setPrefSize(220, 80);
		lb_top_right.setPrefSize(70, 0);
		lb_top_right_rem.setPrefSize(100, 80);
		
		HBox hb_top = new HBox();
		hb_top.getChildren().add(lb_top_left);
		hb_top.getChildren().add(lb_top_left_ans);
		hb_top.getChildren().add(lb_top_right);
		hb_top.getChildren().add(lb_top_right_rem);
		
		hb_top.setPadding(new Insets(5, 15, 0, 15));
		
		/******************************top-label end******************************/
		
		
		/******************************problem-label******************************/
		
		Label lb_problem = new Label("90 + 30");
		lb_problem.setFont(new Font(70));
		lb_problem.setMaxWidth(Double.MAX_VALUE);
        lb_problem.setAlignment(Pos.CENTER);
		
		/******************************problem-label end******************************/
		
		
		/******************************textfile******************************/
		
		TextField text = new TextField();
		text.setMaxWidth(380);
		text.setMinHeight(30);
		
		VBox vb_text = new VBox();
		vb_text.getChildren().add(text);
		vb_text.setAlignment(Pos.CENTER);
		vb_text.setPadding(new Insets(15, 0, 0, 0));
		
		/******************************textfile end******************************/
		
		
		/******************************key button gridpane******************************/
		
		GridPane gp = new GridPane();
		BorderPane bp = new BorderPane();
		Button[] btn = new Button[11];
		
		for(int i=0;i<10;i++){
			btn[i] = new Button(String.valueOf(i));
			btn[i].setFont(new Font(30));
			btn[i].setPrefSize(80, 80);
		}
		
		btn[10] = new Button("enter!!!");
		btn[10].setFont(new Font(30));
		btn[10].setPrefSize(160, 80);
		
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
		
		
		Button btn_bottom_left = new Button("s\nt\na\nr\nt");
		Button btn_bottom_right = new Button("s\nt\na\nr\nt");
		
		btn_bottom_left.setFont(new Font(30));
		btn_bottom_right.setFont(new Font(30));
		btn_bottom_left.setPrefSize(70, 329);
		btn_bottom_right.setPrefSize(70, 329);
		
		
		VBox vb_btn_left = new VBox();
		VBox vb_btn_right = new VBox();
		vb_btn_left.getChildren().add(btn_bottom_left);
		vb_btn_right.getChildren().add(btn_bottom_right);
		
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

	private void drawCanvas(){
		
		GraphicsContext gc = cv.getGraphicsContext2D();
		
		//gc.setFill(Color.WHITE);
		//gc.fillRect(0, 0, cv.getWidth(), cv.getHeight());
		
		gc.setFill(clr);
		gc.setFont(new Font(30));
		gc.fillText("＼(^o^)／ 正解 ＼(^o^)／", 93, 25);
	}

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

	public static void main(String[] args)
	{
		launch(args);
	}
}