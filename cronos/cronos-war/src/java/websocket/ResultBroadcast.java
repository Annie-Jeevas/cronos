/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import entities.Mark;
import entities.MarkComparator;
import entities.RaceResult;
import entities.ShootMark;
import facades.RaceFacade;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
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
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Анюта
 */
@ServerEndpoint(value = "/ResultBroadcast")
public class ResultBroadcast {

    @EJB
    private RaceFacade raceFac;
    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private List<Mark> resultList = new ArrayList<Mark>();
    private Boolean raceIsOn = false; //флаг того, что какая-то гонка начата, надо сбросить по завершении
    //нужен флаг на результате, начальный/конечный/ни то ни се

    //потом надо разбирать сообщение от клиента? тут вроде только старт будет со стороны клиента
    //паузу и остановку выкидываем, скорость подберем в файле
    //алгоритм такой - ловим месседж от клиента, вытягиваем из файла результат, транслируем месседжи с указанием того, начальный он или конечный
    //при отправке конченого сбрасываем флаг
    //при подключении нового пира отправляем сразу всю историю - нужен флаг
    //на клиенте ловим месседжи, по типу результата вычисляем что рисовать - мишень или блок отметки
    //нужен нулевой выстрел без типа попадания - отметка прихода на стрельбу для отрисовки пустых мишененей
    //для мишени определяем попал или нет - показываем красненький или беленький кружочек вместо черного
    private List<Mark> currentHistory = new ArrayList<Mark>(); //для истории - лист с отправленными метками - заносим туда после отправки

    public List<Mark> getResultList() {
        return resultList;
    }

    public void readFromFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ShootMarkContainer shootMarkContainer = (ShootMarkContainer) mapper.readValue(new FileInputStream("C:\\Users\\Анюта\\Desktop\\Универ\\6 СЕМЕСТР\\РПС\\Курсовик Biathlon\\cronos\\cronos-war\\web\\shooting.json"), ShootMarkContainer.class);
        LapMarkContainer lapMarkContainer = (LapMarkContainer) mapper.readValue(new FileInputStream("C:\\Users\\Анюта\\Desktop\\Универ\\6 СЕМЕСТР\\РПС\\Курсовик Biathlon\\cronos\\cronos-war\\web\\laps.json"), LapMarkContainer.class);
        resultList.addAll(shootMarkContainer.getMarks());
        resultList.addAll(lapMarkContainer.getMarks());
        Collections.sort(resultList, new MarkComparator());
    }

    @OnMessage
    public void broadcastResult(String message, Session session) throws InterruptedException, IOException, EncodeException {
        //if (message == "start") {
       
            readFromFile();
            if (raceIsOn) {
                session.getBasicRemote().sendText("{errorMessage:\"Race is already on\"}");
                return;
            }
            raceIsOn = true;
            Long now = 0L;
            resultList.get(0).setStartOrEnd(Boolean.TRUE);
            resultList.get(resultList.size() - 1).setStartOrEnd(Boolean.FALSE);
            for (Mark m : resultList) { //для каждой отметки
                System.out.println("broadcastFigure: " + m.toString());
                Thread.sleep(m.getMarkTime() - now);//ждем столько, сколько до текущей отметки от предыдущей
                for (Session peer : peers) {  //и для каждого пира                  
                    peer.getBasicRemote().sendText(m.toJson()); //рассылаем отметку
                }
                now = m.getMarkTime(); //меняем предыдущую отметку
                currentHistory.add(m); //записываем в историю
                //где-то тут должна быть запись в БД, подумаю потом
                if ((m.getStartOrEnd() != null) && (!m.getStartOrEnd())) {
                    raceIsOn = false;
                    currentHistory.clear();
                    System.out.println("end of race");
                } //если последняя метка, то сбрасываем флаг и историю
            }
        //}

    }

    @OnOpen
    public void onOpen(Session peer) throws IOException, EncodeException {
        peers.add(peer);
        for (Mark m : currentHistory) {
            for (Session s : peers) {
                s.getBasicRemote().sendText(m.toJson());
            }

        }

    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }

}
