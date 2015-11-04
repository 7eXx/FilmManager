/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import filmmanager.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import support.Cinema;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class LoadConnectionController implements Initializable {

    @FXML
    private TextField tfServer;
    @FXML
    private TextField tfPorta;
    @FXML
    private TextField tfDatabase;
    @FXML
    private TextField tfUtente;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private Button btConnetti, btTest;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void onClickConnetti(ActionEvent event) throws IOException {

        String server = tfServer.getText();
        String port = tfPorta.getText();
        String db = tfDatabase.getText();
        String user = tfUtente.getText();
        String pwd = pfPassword.getText();

        boolean ris = Cinema.testConnection(server, port, db, user, pwd);
        if (ris) {
            Cinema.setConnection(server, port, db, user, pwd);
            
            Parent root = FXMLLoader.load(getClass().getResource("FilmManager.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Gestione Film");
            stage.setScene(scene);
            stage.show();

            stage.show();

            Stage s = (Stage) btConnetti.getScene().getWindow();
            s.close();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Errore Connessione");
            alert.setTitle("Connessione Database");
            alert.setContentText("Verificare che i parametri di connessione siano corretti.");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void onClickTest(ActionEvent event) {

        String server = tfServer.getText();
        String port = tfPorta.getText();
        String db = tfDatabase.getText();
        String user = tfUtente.getText();
        String pwd = pfPassword.getText();

        boolean ris = Cinema.testConnection(server, port, db, user, pwd);

        if (ris) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Connessione Database");
            alert.setContentText("Connessione Verificata");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Errore Connessione");
            alert.setTitle("Connessione Database");
            alert.setContentText("Verificare che i parametri di connessione siano corretti.");
            alert.showAndWait();
        }
    }

}
