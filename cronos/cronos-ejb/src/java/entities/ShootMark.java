/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Анюта
 */
@Entity
public class ShootMark extends Mark implements Serializable, IJsonParsable {

    private boolean slip;
    @Transient
    private final String type = "Shoot";
    @Transient
    private int shootingID;
    @Transient
    private int numderInShooting;

    public String getType() {
        return type;
    }
    

    public boolean isSlip() {
        return slip;
    }

    public void setSlip(boolean slip) {
        this.slip = slip;
    }

    public int getShootingID() {
        return shootingID;
    }

    public void setShootingID(int shootingID) {
        this.shootingID = shootingID;
    }

    public int getNumderInShooting() {
        return numderInShooting;
    }

    public void setNumderInShooting(int numderInShooting) {
        this.numderInShooting = numderInShooting;
    }

    
    
    @Override
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException ex) {
            Logger.getLogger(Team.class.getName()).log(Level.SEVERE, null, ex);
            return "{}";
        }
    }
    
    
    
}
