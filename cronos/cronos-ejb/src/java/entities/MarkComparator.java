/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Comparator;

/**
 *
 * @author Анюта
 */
public class MarkComparator implements Comparator<Mark>{

    @Override
    public int compare(Mark o1, Mark o2) {
        return o1.getMarkTime().compareTo(o2.getMarkTime());
    }
    
}
