package kr.co.famos.not.control.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    
    public static void main(String[] args) {
        try {
            String ip = "localhost"; //Ŭ���̾�Ʈ�� �ӽ� ���� �ּ�
            int port = 10001; //������ ���� ��Ʈ
            Socket socket = new Socket(ip, port); //Ŭ���̾�Ʈ�� ���� ����

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

            OutputStream out = socket.getOutputStream(); //������ �������κ��� ����� ����
            InputStream in = socket.getInputStream(); //������ �������κ��� �Է��� ����

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out)); //��� ��Ʈ���� ��ȯ
            BufferedReader br = new BufferedReader(new InputStreamReader(in)); //�Է� ��Ʈ���� ��ȯ

            String myMsg = null; //���� �޽���
            String echo = null; //�޴� �޽���

            while ((myMsg = input.readLine()) != null) {
                
                if (myMsg.equals("/q")) {
                    break; //���� ����
                }
                
                pw.println(myMsg); //PrintWriter�� �̿��Ͽ� �������� ����
                pw.flush(); //���� ����
                
                echo = br.readLine(); //������ ���۷� �޽����� �����ϸ� �̸� ����
                System.out.println("echo : " + echo);
            }

            pw.close();
            br.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
