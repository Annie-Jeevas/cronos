/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.RaceResult;
import facades.RaceFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Анюта
 */
@ServerEndpoint("/ResultBroadcast")
public class ResultBroadcast {
    @EJB
    private RaceFacade raceFac;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>()); 
    private List<RaceResult> resultList;
    private Boolean raceIsOn; //флаг того, что какая-то гонка начата, надо сбросить по завершении
    //нужен флаг на результате, начальный/конечный/ни то ни се
    //выбирать результаты пока будем из файла JSON
    //надо написать метод для выборки результатов из файла
    //и файл :D
    
    //потом надо разбирать сообщение от клиента? тут вроде только старт будет со стороны клиента
    //паузу и остановку выкидываем, скорость подберем в файле
    //алгоритм такой - ловим месседж от клиента, вытягиваем из файла результат, транслируем месседжи с указанием того, начальный он или конечный
    //при отправке конченого сбрасываем флаг
    //при подключении нового пира отправляем сразу всю историю - нужен флаг
    //на клиенте ловим месседжи, по типу результата вычисляем что рисовать - мишень или блок отметки
    //нужен нулевой выстрел без типа попадания - отметка прихода на стрельбу для отрисовки пустых мишененей
    //для мишени определяем попал или нет - показываем красненький или беленький кружочек вместо черного
    
   
    
    @OnMessage
    public void broadcastResult(String message) {
        if (message == "start"){
            if (raceIsOn){
                //отправить ошибку
                return;
            }
            raceIsOn = true;
            for (Session peer : peers) {
                try {
                    for (int i = 0; i < 3; i++) {
                        System.out.println("broadcastFigure: " + figure);
                        Thread.sleep(500); //здесь будет result.next.time
                        peer.getBasicRemote().sendObject(figure);
                    }
                } catch (IOException | EncodeException | InterruptedException ex) {
                    Logger.getLogger(MyWhiteboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
                    
        }
    }
    
    public List<RaceResult> findAllResults(){
        List<RaceResult> results = new ArrayList<RaceResult>();
        //может быть, возьму из файла
        return results;
    }
    
    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
        resultList = findAllResults();
        raceIsOn = false;
        
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);

    }

}
