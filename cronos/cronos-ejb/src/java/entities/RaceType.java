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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Анюта
 */
@Entity
public class RaceType implements Serializable, IWritableEntity, IJsonParsable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String raceTypeName;
    private int participantsNumber;
    private byte shootingNumber;
    //распределение кубковых очков ждите в следующем релизе ;)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RaceType)) {
            return false;
        }
        RaceType other = (RaceType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RaceType[ id=" + id + " ]";
    }

    @Override
    public String getFriendlyName() {
        return raceTypeName;
    }
    
     @Override
    public String toJson() {
       ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException ex) {
            Logger.getLogger(RaceType.class.getName()).log(Level.SEVERE, null, ex);
            return "{}";
        }
    }

}
