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
import support.RegistaOscar;

/**
 * FXML Controller class
 *
 * @author marco
 */
public class ListaAggiungiController implements Initializable {

    FilmModificaController controller;
    Class c;

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
        this.c = c;

        if (c.equals(AttoreOscar.class)) {
            listAggiungi.setItems(controller.attoriNonPres);
        } else if (c.equals(RegistaOscar.class)) {
            listAggiungi.setItems(controller.regiaNonPres);
        }
    }

    @FXML
    public void onClickAggiungi(ActionEvent event) {
        if (!listAggiungi.getSelectionModel().isEmpty()) {
            //AttoreOscar
            if (c.equals(AttoreOscar.class)) {
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
                        a.setState(State.INSERTED);
                        controller.attoriMod.add(a);
                        break;
                }
                if (a.isOscar()) {
                    controller.addOscar();
                }
                System.out.println("instert lista: " + controller.attoriMod);

            } else if (c.equals(RegistaOscar.class)) {

                int i = listAggiungi.getSelectionModel().getSelectedIndex();
                RegistaOscar r = controller.regiaNonPres.get(i);
                controller.resetBoxRegia();
                
                if (!controller.regiaPres.isEmpty()) {
                    RegistaOscar o = controller.regiaPres.get(0);
                    controller.regiaPres.set(0, r);
                    controller.regiaNonPres.set(i, o);

                } else {
                    controller.regiaNonPres.remove(r);
                    controller.regiaPres.add(r);
                }

                if (controller.regiaMod == null) {
                    controller.regiaMod = r;
                    controller.regiaMod.setState(State.INSERTED);
                    
                } else {
                    
                    switch (controller.regiaMod.getState()) {
                        
                        case NONE:
                        case MODIFIED:
                        case DELETED:
                            controller.regiaMod = r;
                            controller.regiaMod.setState(State.MODIFIED);
                            break;
                        case INSERTED:
                            controller.regiaMod = r;
                            break;
                    }
                }
                System.out.println("insert lista: " + controller.regiaMod);
            }
        }
    }
}
