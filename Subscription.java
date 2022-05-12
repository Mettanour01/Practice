package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.database.CardTableData;
import sample.database.DatabaseHandler;
import sample.database.ScheduleTableData;
import sample.database.User;

import java.io.IOException;
import java.sql.SQLException;
/**
 * класс ControllerDesktop для реализации главного окна приложения

 */
public class ControllerDesktop {
    @FXML
    private TableView<CardTableData> subscriptionTable;
    @FXML
    private TableColumn<CardTableData, Integer> subscriptionId;
    @FXML
    private TableColumn<CardTableData, String> clientName;
    @FXML
    private TableColumn<CardTableData, String> clientLastName;
    @FXML
    private TableView<ScheduleTableData> scheduleTable;
    @FXML
    private TableColumn<ScheduleTableData, String> adminData;
    @FXML
    private TableColumn<ScheduleTableData, String> admin;
    @FXML
    private TableColumn<ScheduleTableData, String> adminName;
    @FXML
    private TextField subscriptionIdTextField;
    @FXML
    private Button openButton;
    @FXML
    private Button deleteSubscriptionButton;
    @FXML
    private Button addSubscriptionButton;
    @FXML
    private Button editSubscriptionButton;
    @FXML
    private Button exitButton;
    DatabaseHandler databaseHandler = new DatabaseHandler();

    public ControllerDesktop() {
    }
    /**
     * метод для инициализации всех элементов и разметок
     */
    @FXML
    void initialize() {
        this.cardId.setCellValueFactory(new PropertyValueFactory("id"));
        this.patientName.setCellValueFactory(new PropertyValueFactory("clientName"));
        this.patientLastName.setCellValueFactory(new PropertyValueFactory("clientLastName"));
        this.doctorData.setCellValueFactory(new PropertyValueFactory("adminData"));
        this.doctor.setCellValueFactory(new PropertyValueFactory("admin"));
        this.doctorName.setCellValueFactory(new PropertyValueFactory("adminName"));
        this.updateCardTable();
        this.updateScheduleTable();
        boolean role = User.getRole().equals("admin");
        this.openButton.setVisible(role);
        this.deleteCardButton.setVisible(role);
        this.editCardButton.setVisible(role);
        this.cardIdTextField.setVisible(role);
        this.openButton.setOnAction((actionEvent) -> {
            try {
                User.setEditedCardId(Integer.parseInt(this.cardIdTextField.getText()));
                this.openOtherWindow("/sample/layout/open_card.fxml");
            } catch (Exception var3) {
            }

        });
        this.deleteCardButton.setOnAction((actionEvent) -> {
            this.databaseHandler.deleteCard(Integer.parseInt(this.cardIdTextField.getText()));
            this.updateCardTable();
        });
        this.editCardButton.setOnAction((actionEvent) -> {
            try {
                User.setEditedCardId(Integer.parseInt(this.cardIdTextField.getText()));
                this.openOtherWindow("/sample/layout/edit_card.fxml");
            } catch (Exception var3) {
            }

        });
        this.addCardButton.setOnAction((actionEvent) -> {
            this.openOtherWindow("/sample/layout/add_card.fxml");
        });
        this.exitButton.setOnAction((actionEvent) -> {
            this.openOtherWindow("/sample/layout/authorization.fxml");
        });
    }
    /**
     * метод для изменения абонемента
     */
    private void updateCardTable() {
        try {
            this.cardTable.setItems(this.databaseHandler.returnCards("SELECT * FROM praktika.subscription"));
        } catch (ClassNotFoundException | SQLException var2) {
            var2.printStackTrace();
        }

    }
    /**
     * метод для изменения расписания
     */
    private void updateScheduleTable() {
        try {
            this.scheduleTable.setItems(this.databaseHandler.returnSchedule("SELECT * FROM praktika.schedule"));
        } catch (ClassNotFoundException | SQLException var2) {
            var2.printStackTrace();
        }

    }
    /**
     * метод для кнопки выхода
     */
    private void openOtherWindow(String window) {
        this.exitButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        Parent root = (Parent)loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
