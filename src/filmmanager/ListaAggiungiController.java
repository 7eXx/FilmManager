/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmmanager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import support.AttoreOscar;
import support.Cinema.State;
import support.Genere;
import support.Produttore;
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
    @FXML
    FilteredList filteredData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        tfRicerca.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                filteredData.setPredicate(new Predicate() {

                    public boolean test(Object person) {
                        // If filter text is empty, display all persons.
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        
                        // Compare first name and last name of every person with filter text.
                        String lowerCaseFilter = newValue.toLowerCase();
                        
                        return person.toString().toLowerCase().contains(lowerCaseFilter); 
                    }
                });
            }
        });
    }

    public void initData(FilmModificaController controller, Class c) {

        this.controller = controller;
        this.c = c;

        if (c.equals(AttoreOscar.class)) {
            listAggiungi.setItems(controller.attoriNonPres);
        } else if (c.equals(RegistaOscar.class)) {
            listAggiungi.setItems(controller.regiaNonPres);
        } else if (c.equals(Genere.class)) {
            listAggiungi.setItems(controller.generiNonPres);
        } else if (c.equals(Produttore.class)) {
            listAggiungi.setItems(controller.prodNonPres);
        }
        
        filteredData = new FilteredList(listAggiungi.getItems(), new Predicate() {

            @Override
            public boolean test(Object p) {
                return true;
            }
        });
        
        listAggiungi.setItems(filteredData);
    }

    @FXML
    public void onClickAggiungi(ActionEvent event) {
        if (!listAggiungi.getSelectionModel().isEmpty()) {
            //AttoreOscar
            if (c.equals(AttoreOscar.class)) {
                int i = listAggiungi.getSelectionModel().getSelectedIndex();
                i = filteredData.getSourceIndex(i);
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
                System.out.println("insert actor: " + controller.attoriMod);

            } else if (c.equals(RegistaOscar.class)) {

                int i = listAggiungi.getSelectionModel().getSelectedIndex();
                i = filteredData.getSourceIndex(i);
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
                System.out.println("insert regia: " + controller.regiaMod);
            } else if (c.equals(Genere.class)) {

                int i = listAggiungi.getSelectionModel().getSelectedIndex();
                i = filteredData.getSourceIndex(i);
                Genere g = controller.generiNonPres.get(i);
                controller.generiNonPres.remove(g);
                controller.generiPres.add(g);
                switch (g.getState()) {
                    case DELETED:
                        g.setState(State.NONE);
                        controller.generiMod.remove(g);
                        break;

                    case NONE:
                        g.setState(State.INSERTED);
                        controller.generiMod.add(g);
                        break;
                }
                System.out.println("insert genere: " + controller.generiMod);

            } else if (c.equals(Produttore.class)) {

                int i = listAggiungi.getSelectionModel().getSelectedIndex();
                i = filteredData.getSourceIndex(i);
                Produttore p = controller.prodNonPres.get(i);
                controller.prodNonPres.remove(p);
                controller.prodPres.add(p);
                switch (p.getState()) {
                    case DELETED:
                        p.setState(State.NONE);
                        controller.prodMod.remove(p);
                        break;

                    case NONE:
                        p.setState(State.INSERTED);
                        controller.prodMod.add(p);
                        break;
                }
                System.out.println("insert prod: " + controller.prodMod);
            }
        }
    }
}
