/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import filmmanager.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    public void onClickConnetti(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("FilmManager.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Gestione Film");
        stage.setScene(scene);
        stage.show();

        stage.show();
        
        Stage s = (Stage) btConnetti.getScene().getWindow();
        s.close();
    }
    
    @FXML
    public void onClickTest(ActionEvent event){
        
        String server = tfServer.getText();
        String port = tfPorta.getText();
        String db = tfDatabase.getText();
        String user = tfUtente.getText();
        String pwd = pfPassword.getText();
        
        
        
    }
    
    
    
}
