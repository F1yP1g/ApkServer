package com.lu.java;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.lu.info.CodeFlag;
/**
 * socket服务端
 * @author LU
 *
 */
public class Server {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("*********服务器已启动*********");
            System.out.println("当前服务器指令码："+CodeFlag.getCOMMEND_CODE());
            while (true) {
                Socket socket = serverSocket.accept();
                SocketThread socketThread = new SocketThread(socket);
                socketThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}