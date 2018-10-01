package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NetworkState {
    public NetworkState(){}

    public boolean connection(){
        boolean connected = false;
        Runtime runtime = Runtime.getRuntime();
        Process process;
        try{
            process = runtime.exec("ping www.google.com.au");
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuffer sb = new StringBuffer();
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
                if(!sb.equals(null) || !sb.equals("")){
                    break;
                }
            }

            System.out.println(sb.toString());
            inputStream.close();
            inputStreamReader.close();
            bufferedReader.close();

            if (sb!= null && !sb.toString().equals("")){
                if(sb.toString().indexOf("TTL")>0 || sb.toString().indexOf("ttl")>0){
                    connected = true;
                }
            }
        }catch(IOException e){
            //TODO
            e.printStackTrace();
        }
        return connected;
    }

    public static  void main(String[] args){
        NetworkState s = new NetworkState();
        System.out.println(s.connection());
    }
}