import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.scene.input.*;
import javafx.scene.canvas.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Random;



public class reports extends Application
{
	
	private int a = 0, b = 0, arithmetic_ope = 0;
	private int ans = 0;
	private int count = 0, sum_count = 0, sum = 0;
	private int time = 0;
	
	private Canvas cv;
	private Color clr;
	private boolean flags;
	private boolean menu_flag = false;
	private String judgment = "default";			//正解、不正解判定 初期値；initial，正解：correct，不正解：incorrect, エラー：fraud;
	
	/**********menubar**********/
	Menu menu = new Menu("時間設定");
	RadioMenuItem mi1 = new RadioMenuItem("30秒");
	RadioMenuItem mi2 = new RadioMenuItem("45秒");
	RadioMenuItem mi3 = new RadioMenuItem("60秒");
	/**********menubar end**********/
	
	/**********top-label**********/
	Label lb_top_left_ans = new Label("");
	Label lb_top_right_rem = new Label("30");
	/**********top-label end**********/
	
	/**********problem-label**********/
	Label lb_problem = new Label("ここに問題が出ます");
	/**********problem-label end**********/
	
	/**********start btn**********/
	Button btn_left = new Button("s\nt\na\nr\nt");
	Button btn_right_top = new Button("C");
	Button btn_right_bottom = new Button("ー");
	/**********start btn end**********/
	
	/**********key button gridpane**********/
	Button[] btn = new Button[11];
	/**********key button gridpane end**********/
	
	/**********text**********/
	Label text = new Label();
	String instr = "";
	/**********text end**********/
	
	
	public void start(Stage stage) throws Exception
	{
		
		/******************************メニューバー******************************/
		MenuBar menu_bar = new MenuBar();
		
		mi1.setId("30");
		mi2.setId("45");
		mi3.setId("60");
		
		ToggleGroup tog = new ToggleGroup();
		mi1.setToggleGroup(tog);
		mi2.setToggleGroup(tog);
		mi3.setToggleGroup(tog);
		
		menu_bar.getMenus().add(menu);
		menu.getItems().add(mi1);
		menu.getItems().add(mi2);
		menu.getItems().add(mi3);
		
		mi1.setSelected(true);
		HBox hb_menu = new HBox();
		hb_menu.getChildren().addAll(menu_bar);
		
		hb_menu.setPadding(new Insets(0, 60, 0, 0));
		hb_menu.setAlignment(Pos.CENTER);
		
		menu_bar.getStyleClass().add("menu_bar");
		
		EventHandler_time_setting actionhandler_time_setting = new EventHandler_time_setting();
		menu.addEventHandler(ActionEvent.ANY, actionhandler_time_setting);
		/******************************メニューバー end******************************/
		
		
		/******************************ラジオボタン＆キャンバス******************************/
		RadioButton[] rbs = new RadioButton[7];
		rbs[0] = new RadioButton("白色");
		rbs[1] = new RadioButton("黒色");
		rbs[2] = new RadioButton("赤色");
		rbs[3] = new RadioButton("橙色");
		rbs[4] = new RadioButton("青色");
		rbs[5] = new RadioButton("黄色");
		rbs[6] = new RadioButton("緑色");
		rbs[0].setId("white");
		rbs[1].setId("black");
		rbs[2].setId("red");
		rbs[3].setId("orange");
		rbs[4].setId("blue");
		rbs[5].setId("yellow");
		rbs[6].setId("green");
		
		ToggleGroup tg = new ToggleGroup();
		rbs[0].setToggleGroup(tg);
		rbs[1].setToggleGroup(tg);
		rbs[2].setToggleGroup(tg);
		rbs[3].setToggleGroup(tg);
		rbs[4].setToggleGroup(tg);
		rbs[5].setToggleGroup(tg);
		rbs[6].setToggleGroup(tg);
		
		radio_button actionhandler = new radio_button();
		rbs[0].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[1].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[2].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[3].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[4].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[5].addEventHandler(ActionEvent.ANY, actionhandler);
		rbs[6].addEventHandler(ActionEvent.ANY, actionhandler);
		
		cv = new Canvas(600,30);
		clr = Color.BLACK;
		judgment = "initial";
		drawCanvas();
		
		HBox hb_rbs = new HBox();
		hb_rbs.getChildren().addAll(rbs);
		hb_rbs.setPadding(new Insets(10));
		hb_rbs.setSpacing(10);
		
		HBox hb_top_menu = new HBox();
		hb_top_menu.getChildren().addAll(hb_menu);
		hb_top_menu.getChildren().addAll(hb_rbs);
			
		hb_top_menu.getStyleClass().add("hb_top_menu");
		
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
		lb_top_left_ans.setPrefSize(70, 80);
		lb_top_left_q.setPrefSize(170, 80);
		lb_top_right.setPrefSize(70, 0);
		lb_top_right_rem.setPrefSize(70, 80);
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
		
		
		/******************************text******************************/
		
		text.setMaxWidth(380);
		text.setMinHeight(35);
		text.setFont(new Font(25));
		text.setAlignment(Pos.CENTER);
		
		VBox vb_text = new VBox();
		vb_text.getChildren().add(text);
		vb_text.setAlignment(Pos.CENTER);
		vb_text.setPadding(new Insets(15, 0, 0, 0));
		
		text.getStyleClass().add("text_back");
		
		/******************************text end******************************/
		
		
		/******************************key button gridpane******************************/
		
		GridPane gp = new GridPane();
		BorderPane bp = new BorderPane();
		
		for(int i=0;i<10;i++){
			btn[i] = new Button(String.valueOf(i));
			btn[i].setFont(new Font(30));
			btn[i].setPrefSize(80, 80);
		}
		
		btn[10] = new Button("enter！");
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
		
		text.addEventHandler(ActionEvent.ANY, actionhandler_btn);			//textfieldイベント追加
		
		/******************************left rightt btn******************************/
		
		btn_left.setFont(new Font(25));
		btn_right_top.setFont(new Font(25));
		btn_right_bottom.setFont(new Font(25));
		btn_left.setPrefSize(70, 329);
		btn_right_top.setPrefSize(70, 164);
		btn_right_bottom.setPrefSize(70, 164);
		
		VBox vb_btn_left = new VBox();
		VBox vb_btn_right = new VBox();
		vb_btn_left.getChildren().add(btn_left);
		vb_btn_right.getChildren().add(btn_right_top);
		vb_btn_right.getChildren().add(btn_right_bottom);
		
		vb_btn_right.setSpacing(3);
		
		btn_right_top.setId("12");
		btn_right_bottom.setId("13");
		
		btn_right_top.addEventHandler(ActionEvent.ANY, actionhandler_btn);
		btn_right_bottom.addEventHandler(ActionEvent.ANY, actionhandler_btn);
			
		EventHandler_start_btn actionhandler_start_btn = new EventHandler_start_btn();
		btn_left.addEventHandler(ActionEvent.ANY, actionhandler_start_btn);
		
		/******************************left right btn end******************************/
		
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
		vb.getChildren().add(hb_top_menu);
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
		stage.setHeight(670);
		
		stage.show();
}

	/******************************isnumber******************************/
	public void isNumber(String s)
	{
		flags = true;
		
	    try{
			Integer.parseInt(s);
	     }catch (NumberFormatException e) {
			flags = false;
	     }
	}
	/******************************isnumber end******************************/
	
	
	/******************************canvas******************************/
	private void drawCanvas(){
		
		GraphicsContext gc = cv.getGraphicsContext2D();
		
		gc.setFill(clr);
		gc.setFont(new Font(30));
		
		if(judgment == "correct"){
			gc.clearRect(0,0, cv.getWidth(), cv.getHeight());
			gc.fillText("＼(^o^)／ 正解 ＼(^o^)／", 93, 25);					//正解
		}
		else if(judgment == "incorrect"){
			gc.clearRect(0,0, cv.getWidth(), cv.getHeight());
			gc.fillText("(´・ω・｀) 不正解 (´・ω・｀)", 100, 25);		//不正解
		}
		else if(judgment == "fraud"){
			gc.clearRect(0,0, cv.getWidth(), cv.getHeight());
			gc.fillText("", 93, 25);		//エラー表示
		}
		else if(judgment == "initial"){
			gc.clearRect(0,0, cv.getWidth(), cv.getHeight());
			gc.fillText("ここに回答結果がでます", 130, 25);		//初期
		}
		else{
			gc.clearRect(0,0, cv.getWidth(), cv.getHeight());
			gc.fillText("", 93, 25);		//デフォルト
		}
		
	}
	/******************************canvas end******************************/
	
	
	/******************************radio_button******************************/
	private class radio_button implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			RadioButton target = (RadioButton)e.getTarget();
			String id = target.getId();
			if(id.equals("white")) clr = Color.WHITE;
			if(id.equals("black")) clr = Color.BLACK;
			if(id.equals("red")) clr = Color.RED;
			if(id.equals("orange")) clr = Color.ORANGE;
			if(id.equals("blue")) clr = Color.BLUE;
			if(id.equals("yellow")) clr = Color.YELLOW;
			if(id.equals("green")) clr = Color.GREENYELLOW;
			drawCanvas();
		}
	}
	/******************************radio_button end******************************/
	
	
	/******************************calculation******************************/
	public void calculation(){
		
		Random rand = new Random();
				
		a = rand.nextInt(100);
		b = rand.nextInt(100);
		
		arithmetic_ope = rand.nextInt(2);
				
		if(arithmetic_ope == 0){
			lb_problem.setText(a + " + " + b);
			ans = a + b;
		}
		else{
			lb_problem.setText(a + " - " + b);
			ans = a - b;
		}
		
	}
	/******************************calculation end******************************/
	
	
	/******************************time_setting******************************/
	
	private class EventHandler_time_setting implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			if(mi1.isSelected()){
				time = 0;
				lb_top_right_rem.setText("30");
			}
			else if(mi2.isSelected()){
				time = 1;
				lb_top_right_rem.setText("45");
			}
			else if(mi3.isSelected()){
				time = 2;
				lb_top_right_rem.setText("60");
			}
		}
	}
	/******************************time_setting end******************************/
	
	
	/******************************enter btn******************************/
	private class EventHandler_btn implements EventHandler<ActionEvent>
	{
		int num = 0;
		
		public void handle(ActionEvent e)
		{
			Button bt = (Button)e.getTarget();
			
			judgment = "default";
			drawCanvas();
						
			for(int i=0;i<10;i++){
				if(bt.getId().equals(String.valueOf(i))) instr += String.valueOf(i);
			}
			
			if(bt.getId().equals("12")){
				instr = "";
				text.setText(instr);
			}else if((instr.length() == 0) && bt.getId().equals("13")){
				instr += "-";
			}else{
				isNumber(instr);
			}
			
			if(flags){
				
				text.setText(instr);
				
				if(bt.getId().equals("10")){
					
					sum_count++;
					
					num = Integer.parseInt(instr);
					
					if(num == ans){
						judgment = "correct";
						drawCanvas();
						count++;
					}
					else{
						judgment = "incorrect";
						drawCanvas();
					}
					
					calculation();
					instr = "";
					text.setText(instr);
					lb_top_left_ans.setText(count + " / " + sum_count);
				}
			}else{
				instr = "";
				text.setText(instr);
				judgment = "fraud";
				drawCanvas();
			}
		}
	}
	/******************************enter btn end******************************/
	
	
	/******************************timer start btn event******************************/
	
	private class EventHandler_start_btn implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			
			calculation();
			lb_problem.setTextFill(Color.BLACK);
			lb_top_left_ans.setText(count + "  / " + sum_count);
			
			instr = "";
			text.setText(instr);
			
			btn[10].setDisable(false);
			btn_right_top.setDisable(false);
			btn_right_bottom.setDisable(false);
			
			for(int i=0;i<11;i++){
				btn[i].setDisable(false);
			}
						
			Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>(){
	        @Override
	        public void handle(ActionEvent event) {
				
	        		lb_top_right_rem.setText(String.valueOf(Integer.parseInt(lb_top_right_rem.getText()) - 1));
					
					if(time == 0 && ((Integer.parseInt(lb_top_right_rem.getText()) - 1) < 30)){			//start btnの無効化
						btn_left.setDisable(true);
						menu.setDisable(true);
					}else if(time == 1 && ((Integer.parseInt(lb_top_right_rem.getText()) - 1) < 45)){			//start btnの無効化
						btn_left.setDisable(true);
						menu.setDisable(true);
					}else if(time == 2 && ((Integer.parseInt(lb_top_right_rem.getText()) - 1) < 60)){			//start btnの無効化
						btn_left.setDisable(true);
						menu.setDisable(true);
					}
					
					if((Integer.parseInt(lb_top_right_rem.getText()) - 1) <= -1){			//textfiled clear minus key-btnの無効化
						
						sum = count;
						lb_problem.setText(sum +  " 問 正解！");
						lb_problem.setTextFill(Color.BLUE);
						btn_right_top.setDisable(true);
						btn_right_bottom.setDisable(true);
						
						for(int i=0;i<11;i++){
							btn[i].setDisable(true);
						}
					}
					
					if((Integer.parseInt(lb_top_right_rem.getText())) == 0){			//start btnの有効化
						
						time = 0;
						count = 0; sum_count = 0;
						
						menu.setDisable(false);
						btn_left.setDisable(false);
						mi1.setSelected(true);
						lb_top_right_rem.setText("30");
						judgment = "fraud";
						drawCanvas();
					}
	            }
	        }));
			
			if(time == 0) timer.setCycleCount(30);
			else if(time == 1) timer.setCycleCount(45);
			else if(time == 2) timer.setCycleCount(60);
	    	timer.play();
			
		}
	}
	
	/******************************timer start btn event end******************************/
	
	public static void main(String[] args)
	{
		launch(args);
	}
}