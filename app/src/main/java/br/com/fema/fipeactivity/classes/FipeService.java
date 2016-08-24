package br.com.fema.fipeactivity.classes;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

public class FipeService {

    public static List<JSONObject> getListJSONFromUrl(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setConnectTimeout(15000);
            http.setRequestMethod("GET");

            StringBuilder result = new StringBuilder();
            BufferedReader rd = null;
            try {
                rd = new BufferedReader(new InputStreamReader(http.getInputStream(), Charset.forName("utf-8")));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
            }finally {
                if (rd != null) {
                    rd.close();
                }
            }
            String jsonStr = result.toString();

            List<JSONObject> list = new LinkedList<>();

            if(jsonStr.trim().startsWith("[")) {
                JSONArray array = new JSONArray(jsonStr);
                for (int i = 0; i < array.length(); i++) {
                    list.add(array.getJSONObject(i));
                }
            }else{
                list.add(new JSONObject(jsonStr));
            }
            return list;
        }catch (Throwable e){
            Log.e("FIPE",e.getMessage(),e);
            return new LinkedList<>();
        }
    }
}