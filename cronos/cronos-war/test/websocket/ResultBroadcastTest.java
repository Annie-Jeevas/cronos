/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.Mark;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Анюта
 */
public class ResultBroadcastTest {
    
    public ResultBroadcastTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testSomeMethod() throws IOException {
        ResultBroadcast r = new ResultBroadcast();
        r.readFromFile();
        for (Mark m : (List<Mark>)r.getResultList()){
            System.out.println(m.toJson());
        }
    }
    
}
