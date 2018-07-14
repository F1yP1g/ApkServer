package com.lu.info;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * 手机信息实体类
 * @author LU
 *
 */
public class PhoneInfo implements Serializable{
	private static final long serialVersionUID = 1L;
    private String phoneNumber;
    private String phoneFactory; //手机厂商
    private String phoneModel;  //手机型号
    private String phoneVersion; //版本号
    private String phoneIMEI;  //IMEI
    private String phoneSimOperator; //运营商
    private List<String> phoneContacter = new ArrayList(); //联系人列表
    private List<String> phoneAppInstalled = new ArrayList(); //手机已安装app列表
    
    private List<String> phoneCallLog = new ArrayList();
    
    private double longitude, latitude;
    
    public double getLongitude(){return longitude;}
    public void setLongitude(double longitude){this.longitude = longitude;}
    public double getLatitude(){return latitude;}
    public void setLatitude(double latitude){this.latitude = latitude;}
    public List<String> getPhoneCallLog(){return this.phoneCallLog;}
    public void setPhoneCallLog(List<String> phoneCallLog){this.phoneCallLog = phoneCallLog;}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneFactory() {
		return phoneFactory;
	}
	public void setPhoneFactory(String phoneFactory) {
		this.phoneFactory = phoneFactory;
	}
	public String getPhoneModel() {
		return phoneModel;
	}
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	public String getPhoneVersion() {
		return phoneVersion;
	}
	public void setPhoneVersion(String phoneVersion) {
		this.phoneVersion = phoneVersion;
	}
	public String getPhoneIMEI() {
		return phoneIMEI;
	}
	public void setPhoneIMEI(String phoneIMEI) {
		this.phoneIMEI = phoneIMEI;
	}
	public String getPhoneSimOperator() {
		return phoneSimOperator;
	}
	public void setPhoneSimOperator(String phoneSimOperator) {
		this.phoneSimOperator = phoneSimOperator;
	}
	public List<String> getPhoneContacter() {
		return phoneContacter;
	}
	public void setPhoneContacter(List<String> phoneContacter) {
		this.phoneContacter = phoneContacter;
	}
	public List<String> getPhoneAppInstalled() {
		return phoneAppInstalled;
	}
	public void setPhoneAppInstalled(List<String> phoneAppInstalled) {
		this.phoneAppInstalled = phoneAppInstalled;
	}

}