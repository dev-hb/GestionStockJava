package gestionstockjava;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class CommonHeader {
    private String activeWindow;
    private Stage window;
    Pane root = new Pane();
    HBox links;
    Button categories, products, clients, ventes;
    Label stateLabel;
    
    public CommonHeader(Stage window, String activeWindow){
        this.activeWindow = activeWindow;
        this.window = window;
    }
    
    public void initHeader(){
        links = new HBox();
        categories = new Button("Catégories");
        products = new Button("Produits");
        clients = new Button("Clients");
        ventes = new Button("Ventes");
        stateLabel = new Label();
    }

    public Label getStateLabel() {
        return stateLabel;
    }

    public void applyStyles(){
        this.categories.getStyleClass().add("header_btn");
        this.products.getStyleClass().add("header_btn");
        this.clients.getStyleClass().add("header_btn");
        this.ventes.getStyleClass().add("header_btn");
        this.stateLabel.getStyleClass().add("server_state");
        
        if(this.activeWindow.equals("category")){
            this.categories.getStyleClass().add("btn_active");
        }else if(this.activeWindow.equals("product")){
            this.products.getStyleClass().add("btn_active");
        }else if(this.activeWindow.equals("client")){
            this.clients.getStyleClass().add("btn_active");
        }else if(this.activeWindow.equals("sale") || this.activeWindow.equals("payement")){
            this.ventes.getStyleClass().add("btn_active");
        }
    }
    
    public void actions(){
        categories.setOnAction(e -> {
            CategoryManagement.IHM catIhm = new CategoryManagement.IHM();
            catIhm.start(window);
        });
        
        products.setOnAction(e -> {
            ProductManagement.IHM proIhm = new ProductManagement.IHM();
            proIhm.start(window);
        });
        
        clients.setOnAction(e -> {
            ClientManagement.IHM cliIhm = new ClientManagement.IHM();
            cliIhm.start(window);
        });
        
        ventes.setOnAction(e -> {
            SalesManagement.IHM venIhm = new SalesManagement.IHM();
            venIhm.start(window);
        });
    }
    
    public void draw(){
        links.getChildren().addAll(categories, products, clients, ventes);
        BorderPane bp = new BorderPane();
        bp.setLeft(links);
        bp.setRight(new HBox(stateLabel));
        root.getChildren().add(bp);
    }

    public Pane getHeader(){
        initHeader();
        applyStyles();
        draw();
        actions();
        return root;
    }
    
}
