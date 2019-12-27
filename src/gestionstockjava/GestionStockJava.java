package gestionstockjava;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GestionStockJava extends Application {

    Pane root = new Pane();
    Scene scene = new Scene(root, 1024, 560);
    
    GridPane grid;
    VBox container;
    Label title;
    
    Button category, product, client, sale;

    public void init(){
        grid = new GridPane();
        container = new VBox();
        title = new Label("Application de Gestion de Magazin");
        category = new Button("Catégories");
        product = new Button("Produits");
        client = new Button("Clients");
        sale = new Button("Ventes");
    }
    
    public void draw(){
        title.getStyleClass().add("app_title");
        
        grid.add(category, 0, 0);
        grid.add(product, 1, 0);
        grid.add(client, 0, 1);
        grid.add(sale, 1, 1);
        
        category.setPrefWidth(200);
        product.setPrefWidth(200);
        client.setPrefWidth(200);
        sale.setPrefWidth(200);
        
        category.setPrefHeight(100);
        product.setPrefHeight(100);
        client.setPrefHeight(100);
        sale.setPrefHeight(100);
        
        category.getStyleClass().add("menu_buttons");
        product.getStyleClass().add("menu_buttons");
        client.getStyleClass().add("menu_buttons");
        sale.getStyleClass().add("menu_buttons");
        
        grid.setHgap(10);
        grid.setVgap(10);
        
        grid.getStyleClass().add("magrid");
        
        container.getStyleClass().add("center_elem");
        container.setPrefWidth(1040);
        container.setPrefHeight(575);
        container.setSpacing(50);
        
        container.getChildren().addAll(title, grid);
        root.getChildren().addAll(container);
    }
    
    public void actions(Stage window){
        category.setOnAction(e -> {
            CategoryManagement.IHM catIhm = new CategoryManagement.IHM();
            catIhm.start(window);
        });
        
        product.setOnAction(e -> {
            ProductManagement.IHM proIhm = new ProductManagement.IHM();
            proIhm.start(window);
        });
        
        client.setOnAction(e -> {
            ClientManagement.IHM cliIhm = new ClientManagement.IHM();
            cliIhm.start(window);
       });
        
        sale.setOnAction(e -> {
            SalesManagement.IHM venIhm = new SalesManagement.IHM();
            venIhm.start(window);
        });
    }
    
    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Gestion de magazin - Menu Principale");
        window.setResizable(false);
        
        init();
        draw();
        actions(window);
        
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
