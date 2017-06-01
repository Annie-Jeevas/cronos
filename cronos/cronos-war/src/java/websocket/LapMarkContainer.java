/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.LapMark;
import entities.ShootMark;
import java.util.List;

/**
 *
 * @author Анюта
 */
public class LapMarkContainer {
   private List<LapMark> marks;

        public List<LapMark> getMarks() {
            return marks;
        }

        public void setMarks(List<LapMark> marks) {
            this.marks = marks;
        }

        public LapMarkContainer(List<LapMark> marks) {
            this.marks = marks;
        }
        
        public LapMarkContainer() {
        }     
}
