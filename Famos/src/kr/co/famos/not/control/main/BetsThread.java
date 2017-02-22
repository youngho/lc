package kr.co.famos.not.control.main;

import java.io.BufferedReader;
import java.net.Socket;

/**
 * <code>BetsThread.java</code>
 * 
 * @company : FAMOS
 * @Description : BETS 소켓 통신
 * @author : 김이주 차장
 * @modify :
 * @since : 2017.02.01
 * @version : 1.0
 */

public class BetsThread extends Thread {
    private Socket sock = null;
    private BufferedReader br = null;

    public BetsThread(Socket sock, BufferedReader br) {
        this.sock = sock;
        this.br = br;
    }

    public void run() {
        String server_info = "";
        String tmpData = "";

        try {

            while ((server_info = br.readLine()) != null) {
                tmpData += server_info + "\n";
                System.out.println("tmpData ===> " + tmpData);
                
                if (server_info.indexOf("</MSG>") > -1) {
                    break;
                }
            }
            
            // 배츠에서 받아온 정보를 분리 하는 작업
            BetsSplit bs = new BetsSplit();
            bs.Parse_Data(tmpData);
            MainDual.thread_stop = true;
        } catch (Exception ex) {

        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (Exception ex) {
            }
            try {
                if (sock != null)
                    sock.close();
            } catch (Exception ex) {
            }
        }
    }
}
