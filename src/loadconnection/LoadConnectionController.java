/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadconnection;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void onClickConnetti(ActionEvent event)
    {
        
    }
    
    @FXML
    public void onClickTest(ActionEvent event)
    {
        
    }
    
}
