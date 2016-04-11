package com.example.ajaywisawe.httpdemo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Ajay Wisawe on 2/14/2016.
 */
public class RequestParams {
    String method, baseURL;
    HashMap<String, String> params = new HashMap<String,String>();

    public RequestParams(String method, String baseURL) {
        this.method = method;
        this.baseURL = baseURL;

    }

    public void addParam(String key,String value){
        params.put(key,value);
    }

    public String getEncodedParams(){
        //loop over the key/value pairs of params
        //append to the stringbuilder key = value
                //figure out how to addd the &
        StringBuilder sb = new StringBuilder();
        for(String key : params.keySet()){
            try {
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                if(sb.length() > 0){
                    sb.append("&");
                }
                sb.append(key+"="+value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public String getEncodedURL(){
        return this.baseURL + "?" + getEncodedParams();

    }

    public HttpURLConnection startConnection() throws IOException{
        if(method.equals("GET")){
            URL url = new URL(getEncodedURL());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return con;
        }else{ // POST
            URL url = new URL(this.baseURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(getEncodedParams());
            writer.flush();
            return con;
        }

    }

}
