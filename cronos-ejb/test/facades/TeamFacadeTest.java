/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Анюта
 */
public class TeamFacadeTest {
    private TeamFacade tf;
    public TeamFacadeTest() {
    }
    
    @Before
    public void setUp() {
        tf = new TeamFacade();
        
    }

    @Test
    public void testSomeMethod() {
        System.out.println(tf.findAll());
    }
    
}
