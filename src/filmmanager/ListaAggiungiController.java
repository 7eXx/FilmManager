/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import support.AttoreOscar;
import support.Cinema.State;

/**
 * FXML Controller class
 *
 * @author marco
 */
public class ListaAggiungiController implements Initializable {

    FilmModificaController controller;

    @FXML
    private Button btAggiungi;
    @FXML
    private TextField tfRicerca;
    @FXML
    private ListView listAggiungi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(FilmModificaController controller, Class c) {

        this.controller = controller;

        if (c.equals(AttoreOscar.class)) {
            listAggiungi.setItems(controller.attoriNonPres);
        }
    }

    @FXML
    public void onClickAggiungi(ActionEvent event) {
        if (!listAggiungi.getSelectionModel().isEmpty()) {
            //AttoreOscar
            int i = listAggiungi.getSelectionModel().getSelectedIndex();
            AttoreOscar a = controller.attoriNonPres.get(i);
            controller.attoriNonPres.remove(a);
            controller.attoriPres.add(a);
            switch (a.getState()) {
                case DELETED:
                    a.setState(State.NONE);
                    controller.attoriMod.remove(a);
                    break;
                
                case MOD_DELETED:
                    a.setState(State.MODIFIED);
                    break;

                case NONE:
                    a.setState(State.INSERITED);
                    controller.attoriMod.add(a);
                    break;
            }
            // output test modifiche
            System.out.println("instert lista: " + controller.attoriMod);
        }
    }

}
