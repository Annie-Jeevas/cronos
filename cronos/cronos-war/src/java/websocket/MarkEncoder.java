/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.ShootMark;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author Анюта
 */
class MarkEncoder implements Encoder.Text<ShootMark>{

    @Override
    public String encode(ShootMark arg0) throws EncodeException {
        return arg0.toJson();
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }

    
}
