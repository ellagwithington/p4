import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane; 
import javafx.geometry.Insets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import javafx.scene.paint.Color;

public class CustomerScene extends SceneBasic {
	private Socket connection;
	Button viewOrdersButton = new Button("View Orders");
	Button placeOrderButton = new Button("Place Order");
	Button showProfileButton = new Button("Show Profile");
	Button logoutButton = new Button("Logout");
	Button passwordButton = new Button("Change password");
	Button chatButton = new Button("Chat");
	CustomerChat chat = new CustomerChat();
		

	public CustomerScene() {
        super("Customer Menu");
        int WIDTH = 200;
		viewOrdersButton.setMinWidth(WIDTH);
        placeOrderButton.setMinWidth(WIDTH);
        showProfileButton.setMinWidth(WIDTH);
        logoutButton.setMinWidth(WIDTH);
        passwordButton.setMinWidth(WIDTH);
        chatButton.setMinWidth(WIDTH);
		 root.getChildren().addAll(viewOrdersButton);
        viewOrdersButton.setOnAction(e -> SceneManager.setViewOrdersScene());
        root.getChildren().addAll(placeOrderButton);
        placeOrderButton.setOnAction(e -> SceneManager.setPlaceOrderScene());
        root.getChildren().addAll(showProfileButton);
        showProfileButton.setOnAction(e -> SceneManager.setProfileScene()); 
        root.getChildren().addAll(chatButton);
        chatButton.setOnAction(e -> chat.start(new Stage()));
        root.getChildren().addAll(passwordButton);
        passwordButton.setOnAction(e -> SceneManager.setChangePasswordScene());
        root.getChildren().addAll(logoutButton);
        logoutButton.setOnAction(e -> logout()); 
	}
}