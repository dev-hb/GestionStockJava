package ClientManagement;

import java.util.List;
import java.util.function.Predicate;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    // Les labels
    Label gestionLabel;
    Label idLabel;
    Label nameLabel;
    Label lnameLabel;
    Label adresseLabel;
    Label cityLabel;

    // Les champs
    TextField idTextField;
    TextField nameTextField;
    TextField lnameTextField;
    TextField adressTextField;
    TextField cityTextField;

    // Les buttons
    Button addButton;
    Button editButton;
    Button deleteButton;
    ClientDAOIMPL dao;
    Label statusLabel;
    // Attributs de la table view
    TableView<Client> table;
    // Les columns   
    TableColumn<Client, Integer> idColumn;
    TableColumn<Client, String> nameColumn;
    TableColumn<Client, String> lnameColumn;
    TableColumn<Client, String> adresseColumn;
    TableColumn<Client, String> cityColumn;

    // Les resultats
    ObservableList<Client> listOfClients;
    List<Client> clients;

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
        idColumn = new TableColumn<>("Id");
        nameColumn = new TableColumn<>("Nom");
        lnameColumn = new TableColumn<>("Prenom");
        adresseColumn = new TableColumn<>("Adresse");
        cityColumn = new TableColumn<>("Ville");
        this.statusLabel = new Label();
        this.gestionLabel = new Label("Gestion de clients");
        this.idTextField = new TextField();
        this.nameTextField = new TextField();
        this.lnameTextField = new TextField();
        this.adressTextField = new TextField();
        this.cityTextField = new TextField();

        this.addButton = new Button("Ajouter");
        this.editButton = new Button("Modifier");
        this.deleteButton = new Button("Supprimer");
        this.nameLabel = new Label("Nom");
        this.lnameLabel = new Label("Prenom");
        this.adresseLabel = new Label("Adresse");
        this.cityLabel = new Label("Ville");
        boxTop.getChildren().addAll(gestionLabel, (new gestionstockjava.CommonHeader(window, "client")).getHeader());
        boxTop.setAlignment(Pos.CENTER);

        leftBox.getChildren().addAll(addButton, editButton, deleteButton);
        leftBox.setSpacing(10);

        centerPane.add(idLabel, 0, 0);
        centerPane.add(idTextField, 1, 0);
        centerPane.add(nameLabel, 0, 1);
        centerPane.add(nameTextField, 1, 1);
        centerPane.add(lnameLabel, 0, 2);
        centerPane.add(lnameTextField, 1, 2);
        centerPane.add(adresseLabel, 0, 3);
        centerPane.add(adressTextField, 1, 3);
        centerPane.add(cityLabel, 0, 4);
        centerPane.add(cityTextField, 1, 4);

        centerPane.setPadding(new Insets(10));

        gestionLabel.getStyleClass().add("gestion_label");

        idLabel.getStyleClass().add("labels");
        nameLabel.getStyleClass().add("labels");
        lnameLabel.getStyleClass().add("labels");
        adresseLabel.getStyleClass().add("labels");
        cityLabel.getStyleClass().add("labels");

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

        this.searchTextField.setPromptText("Chercher par nom");
        this.statusLabel.setAlignment(Pos.CENTER);
        this.table = new TableView<>();

        rightBox.getChildren().add(table);
        bottom.setAlignment(Pos.CENTER);
        bottom.getStyleClass().add("button_box");
        this.clients = dao.findAll();
        listOfClients = getUserList();
        root.getStyleClass().add("bg_coloring");

    }

    private ObservableList<Client> getUserList() {
        ObservableList<Client> list = FXCollections.observableArrayList();

        this.clients = dao.findAll();
        for (Client c : clients) {
            list.add(c);
        }
        return list;
    }

    private void updateListItems() {

        listOfClients.removeAll(listOfClients);
        listOfClients.addAll(getUserList());

    }

    private void initTable() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        this.lnameColumn.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        this.adresseColumn.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        this.cityColumn.setCellValueFactory(new PropertyValueFactory<>("Ville"));

        this.table.setItems(listOfClients);
        table.getColumns().addAll(idColumn, nameColumn, lnameColumn, adresseColumn, cityColumn);
    }

    private void clearFields() {
        this.idTextField.setText("");
        this.lnameTextField.setText("");
        this.nameTextField.setText("");
        this.adressTextField.setText("");
        this.cityTextField.setText("");

    }

    @Override
    public void start(Stage primaryStage) {
        this.dao = new ClientDAOIMPL();
        initPane();
        initElement(primaryStage);
        initTable();
        root.setTop(boxTop);
        root.setLeft(leftBox);
        root.setCenter(centerPane);
        root.setRight(rightBox);
        root.setBottom(bottom);

        // Mise à jour de la table après la recherche
        FilteredList<Client> filteredReports = new FilteredList<>(listOfClients);
        searchTextField.textProperty().addListener((observavleValue, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                filteredReports.setPredicate(null);
            } else {
                final String lowerCaseFilter = newValue.toLowerCase();

                filteredReports.setPredicate((Predicate<? super Client>) Client -> {
                    return Client.getPrenom().contains(newValue) || Client.getNom().contains(newValue) || Client.getAdresse().contains(newValue) || Client.getVille().contains(newValue);
                });
            }
        });

        SortedList<Client> sortedClients = new SortedList<>(filteredReports);
        sortedClients.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedClients);
        // Récupération de la ligne courante

        table.setRowFactory(tv -> {
            TableRow<Client> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    Client rowData = row.getItem();
                    idTextField.setText(Long.toString(rowData.getId()));
                    idTextField.setDisable(true);
                    nameTextField.setText(rowData.getNom());
                    lnameTextField.setText(rowData.getPrenom());
                    adressTextField.setText(rowData.getAdresse());
                    cityTextField.setText(rowData.getVille());
                }
            });
            return row;
        });
        addButton.setOnAction(e -> {
            Client p = new Client(Integer.parseInt(idTextField.getText()), nameTextField.getText(), lnameTextField.getText(), adressTextField.getText(), cityTextField.getText());
            dao.create(p);
            clearFields();
            this.statusLabel.setText("Le client est inséré avec succès !");
            this.statusLabel.getStyleClass().add("custom_message");
            updateListItems();

        });

        editButton.setOnAction(e -> {

            Client produtResult = dao.find(Integer.parseInt(idTextField.getText()));
            dao.update(produtResult, nameTextField.getText(), lnameTextField.getText(), adressTextField.getText(), cityTextField.getText());
            updateListItems();
            clearFields();
            this.statusLabel.setText("Le client est bien modifé !");
            this.statusLabel.getStyleClass().add("custom_message");
            idTextField.setDisable(false);
        });

        deleteButton.setOnAction(e -> {
            Client rs = dao.find(Integer.parseInt(idTextField.getText()));
            dao.delete(rs);
            updateListItems();
            clearFields();
            this.statusLabel.setText("Le client est bien été supprimé !");
            this.statusLabel.getStyleClass().add("custom_message");

        });

        primaryStage.setTitle("Gestion des clients");
        
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
