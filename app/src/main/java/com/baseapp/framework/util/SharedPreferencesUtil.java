package com.baseapp.framework.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {
	private String filenbame = "answerking";

	private SharedPreferences ps;

	private Editor editor;

	public SharedPreferences getPs() {
		return ps;
	}

	public SharedPreferencesUtil(Context context) {
		ps = context.getSharedPreferences(filenbame, 0);
		editor = ps.edit();
	}

	public boolean getFirst() {
		return ps.getBoolean("first7ss", true);
	}

	public void setFirst(boolean fs) {
		editor.putBoolean("first7ss", fs);
		editor.commit();
	}

	public void setVision(String a) {
		editor.putString("visions", a);
		editor.commit();
	}

	public String getVision() {
		return ps.getString("visions", "0");
	}

	public void setUid(String uid) {
		editor.putString("uid", uid);
		editor.commit();
	}

	public String getUid() {
		return ps.getString("uid", "");
	}

	public void setPhone(String phone) {
		editor.putString("phone", phone);
		editor.commit();
	}

	public String getPhone() {
		return ps.getString("phone", "");
	}

	public void setNikeName(String NikeName) {
		editor.putString("NikeName", NikeName);
		editor.commit();
	}

	public String getNikeName() {
		return ps.getString("NikeName", "");
	}

	public void setUserImage(String UserImage) {
		editor.putString("UserImage", UserImage);
		editor.commit();
	}

	public String getSession() {
		return ps.getString("Session", "");
	}

	public void setSession(String Session) {
		editor.putString("Session", Session);
		editor.commit();
	}

	public String getToken() {
		return ps.getString("Token", "");
	}

	public void setToken(String Token) {
		editor.putString("Token", Token);
		editor.commit();
	}

	public String getRongYunToken() {
		return ps.getString("rongyunToken", "");
	}

	public void setRongYunToken(String Token) {
		editor.putString("rongyunToken", Token);
		editor.commit();
	}

	
	public String getUserImage() {
		return ps.getString("UserImage", "");
	}

	public Editor getEditor() {
		return editor;
	}

	public boolean getLogin() {
		return ps.getBoolean("login", false);
	}

	public void setLogin(boolean fs) {
		editor.putBoolean("login", fs);
		editor.commit();
	}

	public void setSCityCode(String cityCode) {
		editor.putString("scitycode", cityCode);
		editor.commit();
	}

	public String getSCityCode() {
		return ps.getString("scitycode", "47");
	}

	public void setSCity(String city) {
		editor.putString("scity", city);
		editor.commit();
	}

	public String getSCity() {
		return ps.getString("scity", "武汉市");
	}

	public void setLCityCode(String cityCode) {
		editor.putString("lcitycode", cityCode);
		editor.commit();
	}

	public String getLCityCode() {
		return ps.getString("lcitycode", "47");
	}

	public void setLCity(String city) {
		editor.putString("Lcity", city);
		editor.commit();
	}

	public String getLCity() {
		return ps.getString("Lcity", "武汉市");
	}

	public String getLatitude() {
		return ps.getString("Latitude", "");
	}

	public void setLatitude(String Y) {
		editor.putString("Latitude", Y);
		editor.commit();
	}

	public String getLongitude() {
		return ps.getString("Longitude", "");
	}

	public void setLongitude(String X) {
		editor.putString("Longitude", X);
		editor.commit();
	}
	
	public void setTheme(int theme){
		editor.putInt("theme", theme);
		editor.commit();
	}
	
	public int getTheme(){
		return ps.getInt("theme", 0);
	}
	
	public String getAccount() {
		return ps.getString("Account", "");
	}

	public void setAccount(String X) {
		editor.putString("Account", X);
		editor.commit();
	}
	
	public String getPwd() {
		return ps.getString("password", "");
	}

	public void setPwd(String X) {
		editor.putString("password", X);
		editor.commit();
	}
	
	public boolean getjizuh() {
		return ps.getBoolean("login_jizhu", false);
	}

	public void setjizhu(boolean fs) {
		editor.putBoolean("login_jizhu", fs);
		editor.commit();
	}
	
	public String getSchoolID() {
		return ps.getString("SchoolID", "0");
	}

	public void setSchoolID(String X) {
		editor.putString("SchoolID", X);
		editor.commit();
	}
	
	public void setSchoolName(String name){
		editor.putString("getSchoolName", name);
		editor.commit();
	}
	public String getSchoolName(){
		return ps.getString("getSchoolName", "");
	}
	
	
}
