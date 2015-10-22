/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import support.Attore;
import support.Cinema;

/**
 * FXML Controller class
 *
 * @author Mark
 */
public class AttoreModificaController implements Initializable {

    @FXML
    private Button btConferma, btAnnulla;
    
    @FXML
    private TextField tfNome, tfCognome, tfIdAttore, tfCitta, tfNazione;
    
    @FXML
    private DatePicker dpDataNascita;
    
    @FXML
    private TextArea taBiografia;
    
    private boolean modifica;
    private FilmManagerController main;
    private Attore attore;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    void initData(Attore attore, boolean modifica, FilmManagerController main) {

        this.modifica = modifica;
        this.attore = attore;
        this.main = main;
        if (modifica) {

            tfIdAttore.setText(attore.getId() + "");
            tfNome.setText(attore.getNome());
            tfCognome.setText(attore.getCognome());
            tfNazione.setText(attore.getNazione());
            tfCitta.setText(attore.getCitta());

            if (attore.getData_nascita() != null) {
                dpDataNascita.setValue(LocalDate.parse(attore.getData_nascita()));
            }

            taBiografia.setText(attore.getBiografia());
        }
    }
    
    public void onClickConferma(ActionEvent event) {

        if (!modifica) {
            attore = new Attore();
        }

        attore.setNome(tfNome.getText().trim());
        attore.setCognome(tfCognome.getText().trim());
        
        if (tfNazione.getText() != null && !tfNazione.getText().trim().equals("")) {
            attore.setNazione(tfNazione.getText());
        }
        if (tfCitta.getText() != null && !tfCitta.getText().trim().equals("")) {
            attore.setCitta(tfCitta.getText());
        }

        if ( dpDataNascita.getValue() != null && !dpDataNascita.getValue().toString().equals("")) {
            attore.setData_nascita(dpDataNascita.getValue().toString());
        }

        if (taBiografia.getText() != null && !taBiografia.getText().trim().equals("")) {
            attore.setBiografia(taBiografia.getText());
        }

        if (modifica) {

            Cinema.updateInfo(attore);
        } // altrimenti inserimento
        else {

            Cinema.insertInfo(attore);
            main.insertInfo(attore);
        }

        Stage stage = (Stage) btConferma.getScene().getWindow();
        stage.close();
    }

    public void onClickAnnulla(ActionEvent event) {
        Stage stage = (Stage) btAnnulla.getScene().getWindow();
        stage.close();
    }
    
}
