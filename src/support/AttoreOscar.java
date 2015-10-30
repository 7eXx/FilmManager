/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import support.Cinema.State;


public class AttoreOscar extends Attore {
    
    public static final String OSCAR = "oscar";
    
    
    private final BooleanProperty oscar = new SimpleBooleanProperty();
    
    private State state = State.NONE;

    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public State getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param state new value of state
     */
    public void setState(State state) {
        this.state = state;
    }


    public boolean isOscar() {
        return oscar.get();
    }

    public void setOscar(boolean value) {
        oscar.set(value);
    }

    public BooleanProperty oscarProperty() {
        return oscar;
    }
}
