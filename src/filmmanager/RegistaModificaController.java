/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import support.Cinema;
import support.Film;
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
    @FXML
    private ListView<Film> listRegistaFilm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void onClickConferma(ActionEvent event) {

        if (!tfNome.getText().trim().equals("")) {

            if (!modifica) {
                regista = new Regista();
            }

            regista.setNome(tfNome.getText().trim());

            if (tfCognome.getText()!= null && !tfCognome.getText().trim().equals("")) {
                regista.setCognome(tfCognome.getText().trim());
            } else {
                regista.setCognome(null);
            }

            if (tfNazione.getText() != null && !tfNazione.getText().trim().equals("")) {
                regista.setNazione(tfNazione.getText());
            } else {
                regista.setNazione(null);
            }

            if (dpDataNascita.getValue() != null) {
                regista.setData_nascita(dpDataNascita.getValue().toString());
            } else {
                regista.setData_nascita(null);
            }

            if (taBiografia.getText() != null && !taBiografia.getText().trim().equals("")) {
                regista.setBiografia(taBiografia.getText());
            } else {
                regista.setBiografia(null);
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
            if (regista.getCognome() != null) {
                tfCognome.setText(regista.getCognome());
            }
            if (regista.getNazione() != null) {
                tfNazione.setText(regista.getNazione());
            }

            if (regista.getData_nascita() != null) {
                dpDataNascita.setValue(LocalDate.parse(regista.getData_nascita()));
            }
            if (regista.getBiografia() != null) {
                taBiografia.setText(regista.getBiografia());
            }
            
            ObservableList<Film> f = Cinema.getInfo(Film.class, regista, true);
            listRegistaFilm.setItems(f);
        }
    }
}
