/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.LapMark;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Анюта
 */
class LapEncoder implements Encoder.Text<LapMark> {

    @Override
    public void init(EndpointConfig config) {
       System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    @Override
    public String encode(LapMark object) throws EncodeException {
        return object.toJson();
    }
    
}
