package ProductManagement;

import CategoryManagement.Category;
import java.util.List;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author virus00x
 */
public class IHM extends Application {

    BorderPane root = new BorderPane();
    Scene scene = new Scene(root, 1024, 560);

    private VBox boxTop;
    private HBox bottom;
    private VBox leftBox;
    private GridPane centerPane;
    private VBox rightBox;
    private TextField searchTextField;
    Label gestionLabel;
    Label idLabel;
    Label desLabel;
    Label prixLabel;
    Label categoryLabel;
    ComboBox<Category> categorieComboBox;
    TextField idTextField;
    TextField desTextField;
    TextField prixTextField;
    Button addButton;
    Button editButton;
    Button deleteButton;
    ProductDAOIMPL dao;
    ObservableList<Category> categories;
    Label statusLabel;
    // Attributs de la table view
    TableView<Product> table;
    // Les columns   
    TableColumn<Product, Integer> idColumn;
    TableColumn<Product, String> desColumn;
    TableColumn<Product, Double> prixColumn;
    TableColumn<Product, Category> categoryColumn;

    ObservableList<Product> listOfProducts;
    List<Product> products;

    private void initPane() {
        this.bottom = new HBox();
        searchTextField = new TextField();
        this.centerPane = new GridPane();
        this.boxTop = new VBox();
        this.leftBox = new VBox();
        this.idLabel = new Label("Id");
        this.rightBox = new VBox();
    }

    private void initElement(Stage window) {
        idColumn = new TableColumn<Product, Integer>("Id");
        desColumn = new TableColumn<Product, String>("Désignation");
        prixColumn = new TableColumn<Product, Double>("Prix");
        categoryColumn = new TableColumn<Product, Category>("Catégorie");

        this.categoryLabel = new Label("Catégorie");
        this.statusLabel = new Label();
        this.gestionLabel = new Label("Gestion de produits");
        this.idTextField = new TextField();
        this.desTextField = new TextField();
        this.prixTextField = new TextField();
        this.addButton = new Button("Ajouter");
        this.editButton = new Button("Modifier");
        this.deleteButton = new Button("Supprimer");
        this.desLabel = new Label("Designation");
        this.prixLabel = new Label("Prix");
        boxTop.getChildren().addAll(gestionLabel, (new gestionstockjava.CommonHeader(window, "product")).getHeader());
        boxTop.setAlignment(Pos.CENTER);

        leftBox.getChildren().addAll(addButton, editButton, deleteButton);
        leftBox.setSpacing(10);

        centerPane.add(idLabel, 0, 0);
        centerPane.add(idTextField, 1, 0);
        centerPane.add(desLabel, 0, 1);
        centerPane.add(desTextField, 1, 1);
        centerPane.add(prixLabel, 0, 2);
        centerPane.add(prixTextField, 1, 2);
        centerPane.add(categoryLabel, 0, 3);
        categorieComboBox = new ComboBox<>();
        categories = FXCollections.observableList(dao.getCategories());
        categorieComboBox.setItems(categories);
        categorieComboBox.getSelectionModel().select(2);
        centerPane.add(categorieComboBox, 1, 3);

        centerPane.setPadding(new Insets(10));

        gestionLabel.getStyleClass().add("gestion_label");

        idLabel.getStyleClass().add("labels");
        desLabel.getStyleClass().add("labels");
        prixLabel.getStyleClass().add("labels");

        addButton.getStyleClass().add("buttons");
        editButton.getStyleClass().add("buttons");
        deleteButton.getStyleClass().add("buttons");

        centerPane.setVgap(10);
        centerPane.setHgap(10);
        addButton.setPrefWidth(100);
        editButton.setPrefWidth(100);
        deleteButton.setPrefWidth(100);
        editButton.setPrefHeight(40);
        addButton.setPrefHeight(40);
        deleteButton.setPrefHeight(40);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        leftBox.setPadding(new Insets(10));
        boxTop.getStyleClass().add("top_box_style");
        leftBox.getStyleClass().add("custom_back");
        this.bottom.getChildren().add(statusLabel);
        this.rightBox.getChildren().add(searchTextField);
        this.rightBox.setPadding(new Insets(10));
        this.rightBox.getStyleClass().add("right");

        this.searchTextField.setPromptText("Chercher par designation");
        this.statusLabel.setAlignment(Pos.CENTER);
        this.table = new TableView<>();

        table.getColumns().addAll(idColumn, desColumn, prixColumn, categoryColumn);
        rightBox.getChildren().add(table);
        bottom.setAlignment(Pos.CENTER);
        bottom.getStyleClass().add("button_box");
        this.products = dao.findAll();
        listOfProducts = getUserList();

        root.getStyleClass().add("bg_coloring");
    }

    private ObservableList<Product> getUserList() {
        ObservableList<Product> list = FXCollections.observableArrayList();

        this.products = dao.findAll();

        products.forEach((p) -> {
            list.add(p);
        });
        return list;
    }

    private void updateListItems() {

        listOfProducts.removeAll(listOfProducts);
        listOfProducts.addAll(getUserList());

    }

    private void initTable() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.desColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        this.prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        this.categoryColumn.setCellValueFactory(new PropertyValueFactory<>("catid"));
        this.table.setItems(listOfProducts);
    }

    private void clearFields() {
        this.idTextField.setText("");
        this.desTextField.setText("");
        this.prixTextField.setText("");
    }

    @Override
    public void start(Stage primaryStage) {
        this.dao = new ProductDAOIMPL();
        initPane();
        initElement(primaryStage);
        initTable();
        root.setTop(boxTop);
        root.setLeft(leftBox);
        root.setCenter(centerPane);
        root.setRight(rightBox);
        root.setBottom(bottom);

        // Récupération de la ligne courante
        table.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Product rowData = row.getItem();
                    idTextField.setText(Long.toString(rowData.getId()));
                    idTextField.setDisable(true);
                    desTextField.setText(rowData.getDesignation());
                    prixTextField.setText(Double.toString(rowData.getPrix()));
                    for (Category c : categorieComboBox.getItems()) {
                        if (c.getId() == rowData.getCatid().getId()) {
                            categorieComboBox.getSelectionModel().select(c);
                        }
                    }
                }
            });
            return row;
        });

        // Mise à jour de la table après la recherche
        FilteredList<Product> filteredReports = new FilteredList<>(listOfProducts);
        searchTextField.textProperty().addListener((observavleValue, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                filteredReports.setPredicate(null);
            } else {
                final String lowerCaseFilter = newValue.toLowerCase();

                filteredReports.setPredicate((Predicate<? super Product>) Product -> {
                    return Product.getDesignation().contains(newValue);
                });
            }
        });
        SortedList<Product> sortedProducts = new SortedList<>(filteredReports);
        sortedProducts.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedProducts);

        addButton.setOnAction(e -> {
            Product p = new Product(Integer.parseInt(idTextField.getText()), desTextField.getText(), Double.parseDouble(prixTextField.getText()), categorieComboBox.getValue());

            dao.create(p);
            clearFields();

            this.statusLabel.setText("Le produit est inséré avec succès !");
            this.statusLabel.getStyleClass().add("custom_message");
            updateListItems();

        });

        editButton.setOnAction(e -> {

            Product produtResult = dao.find(Integer.parseInt(idTextField.getText()));
            dao.update(produtResult, desTextField.getText(), Double.parseDouble(prixTextField.getText()), categorieComboBox.getValue());
            updateListItems();
            clearFields();
            this.statusLabel.setText("Le produit est bien modifé !");

        });

        deleteButton.setOnAction(e -> {
            Product rs = dao.find(Integer.parseInt(idTextField.getText()));
            if (rs == null) {
                this.statusLabel.setText("Oops ! Ce produit n'existe pas !");
            } else {

                dao.delete(rs);
                updateListItems();
                clearFields();
                this.statusLabel.setText("Le produit est bien été supprimé !");
            }
        });

        primaryStage.setTitle("Gestion des produits");
        
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);

        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
