/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Анюта
 */
public class MarkTest {
    private Mark mark;
    public MarkTest() {
    }
    
    @Before
    public void setUp() {
        mark = new LapMark();
        mark.setId(1L);
        mark.setMarkTime(1L);
        RaceResult raceResult = new RaceResult();
        raceResult.setId(1L);
        Race race = new Race();
        race.setId(1L);        
        raceResult.setRace(race);
        Sportsman racer = new Sportsman();
        racer.setId(33L);
        raceResult.setRacer(racer);
        mark.setRaceResult(raceResult);
        mark.setStartOrEnd(Boolean.TRUE);
    }

    @Test
    public void testSomeMethod() {
        System.out.println(mark.toJson());
    }
    
}
