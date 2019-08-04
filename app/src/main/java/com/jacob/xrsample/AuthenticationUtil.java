package com.jacob.xrsample;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationUtil {
    private static final String ACTORS_INFO_PATH = "./test.json";
    private static AuthenticationUtil instance;
    public synchronized static AuthenticationUtil getAuthenticationUtil() {
        if(instance == null) {
            instance = new AuthenticationUtil();
        }
        return instance;
    }

    Map<String, Object> availableActorsInfo = null;

    private AuthenticationUtil() {

    }

    public boolean isReady() {
        return availableActorsInfo != null && availableActorsInfo.size() > 0;
    }

    public boolean loadSavedAvailableActorsInfo() {
        File actorsInfoFile = new File(ACTORS_INFO_PATH);
        if(actorsInfoFile.exists() == false) {
            return false;
        }

        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(ACTORS_INFO_PATH));
            Object[] whats = gson.fromJson(reader, Object[].class);
            availableActorsInfo = new HashMap<String, Object>();
            for (Object a: whats) {
                availableActorsInfo.put("key", a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

//    public boolean connect(String actorID) {
//        if(availableActorsInfo.get(actorID) == null) {
//            return false;
//        }
//
//        //connect(callback)
//    }
}
