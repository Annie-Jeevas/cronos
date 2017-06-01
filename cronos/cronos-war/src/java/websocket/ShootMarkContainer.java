/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.ShootMark;
import java.util.List;

/**
 *
 * @author Анюта
 */
public class ShootMarkContainer {

    private List<ShootMark> marks;

    public List<ShootMark> getMarks() {
        return marks;
    }

    public void setMarks(List<ShootMark> marks) {
        this.marks = marks;
    }

    public ShootMarkContainer(List<ShootMark> marks) {
        this.marks = marks;
    }

    public ShootMarkContainer() {
    }
}
