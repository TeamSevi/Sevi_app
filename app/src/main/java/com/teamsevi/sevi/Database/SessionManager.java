package com.teamsevi.sevi.Database;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    public static final String KEY_TOTAL ="t" ;

    SharedPreferences usersSession;
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_PHONENO = "phoneno";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public SessionManager(Context _context){
        context = _context;
        usersSession = _context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = usersSession.edit();
    }
    public void grandtotal (String total){
        editor.putString(KEY_TOTAL,total);
        editor.commit();
    }
    public void createLoginSession(String firstname,String lastname,String phoneno,String email,String password){
        editor.putBoolean(IS_LOGIN,true);

        editor.putString(KEY_FIRSTNAME,firstname);
        editor.putString(KEY_LASTNAME,lastname);
        editor.putString(KEY_PHONENO,phoneno);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_PASSWORD,password);

        editor.commit();
    }

    public HashMap<String ,String> getUserDetailFromSession(){
        HashMap userData = new HashMap();

        userData.put(KEY_FIRSTNAME,usersSession.getString(KEY_FIRSTNAME,null));
        userData.put(KEY_LASTNAME,usersSession.getString(KEY_LASTNAME,null));
        userData.put(KEY_PHONENO,usersSession.getString(KEY_PHONENO,null));
        userData.put(KEY_EMAIL,usersSession.getString(KEY_EMAIL,null));
        userData.put(KEY_PASSWORD,usersSession.getString(KEY_PASSWORD,null));

        return userData;
    }

    public boolean checkLogin(){
        if(usersSession.getBoolean(IS_LOGIN,false)){
            return true;
        }
        else {
            return false;
        }
    }
    public void logoutUserFromSession(){
        editor.clear();
        editor.commit();
    }


    public HashMap<String, String> getgrandtotal(String total) {
        editor.putString(KEY_TOTAL,total);
        editor.commit();
        return null;
    }


}
