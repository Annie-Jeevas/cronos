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
public class LapMark extends Mark implements Serializable, IJsonParsable {
    
    @Transient
    private final String type = "Lap";

    public String getType() {
        return type;
    }

    @Override
    public String toJson() {
       ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException ex) {
            Logger.getLogger(LapMark.class.getName()).log(Level.SEVERE, null, ex);
            return "{}";
        }
    }

}
