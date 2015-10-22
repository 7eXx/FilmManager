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
import support.Cinema;
import support.Regista;

/**
 * FXML Controller class di Regista
 *
 * @author marco
 */
public class RegistaModificaController implements Initializable {

    Regista regista;
    FilmManagerController main;
    boolean modifica;

    @FXML
    private TextField tfIdRegista, tfNome, tfCognome, tfNazione;
    @FXML
    private TextArea taBiografia;
    @FXML
    private DatePicker dpDataNascita;
    @FXML
    private Button btConferma, btAnnulla;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void onClickConferma(ActionEvent event) {
        if (!modifica) {
            regista = new Regista();
        }

        regista.setNome(tfNome.getText().trim());
        regista.setCognome(tfCognome.getText().trim());

        if (tfNazione.getText() != null && !tfNazione.getText().trim().equals("")) {
            regista.setNazione(tfNazione.getText());
        }

        if (dpDataNascita.getValue() != null && !dpDataNascita.getValue().toString().equals("")) {
            regista.setData_nascita(dpDataNascita.getValue().toString());
        }

        if (taBiografia.getText() != null && !taBiografia.getText().trim().equals("")) {
            regista.setBiografia(taBiografia.getText());
        }

        if (modifica) {

            Cinema.updateInfo(regista);
        } // altrimenti inserimento
        else {

            Cinema.insertInfo(regista);
            main.insertInfo(regista);
        }

        Stage stage = (Stage) btConferma.getScene().getWindow();
        stage.close();
    }

    public void onClickAnnulla(ActionEvent event) {
        Stage stage = (Stage) btAnnulla.getScene().getWindow();
        stage.close();
    }

    void initData(Regista regista, boolean modifica, FilmManagerController main) {

        this.modifica = modifica;
        this.regista = regista;
        this.main = main;
        if (modifica) {

            tfIdRegista.setText(regista.getId() + "");
            tfNome.setText(regista.getNome());
            tfCognome.setText(regista.getCognome());
            tfNazione.setText(regista.getNazione());

            if (regista.getData_nascita() != null) {
                dpDataNascita.setValue(LocalDate.parse(regista.getData_nascita()));
            }

            taBiografia.setText(regista.getBiografia());
        }
    }

}