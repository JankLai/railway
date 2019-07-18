package com.bigdata.railway.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 作废，百度地图API并发量超过限制
 */
@Service
public class LocationService {

    public Map<String, List<String>> getLatAndLngByAddresses(List<String> addresses){
        Map<String, List<String>> locations = new HashMap<>();
        for(String addr : addresses){
            List<String> latAndLng = new ArrayList<>();
            String address = "";
            String lat = "";
            String lng = "";
            try {
                address = java.net.URLEncoder.encode(addr,"UTF-8");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            String url = "http://api.map.baidu.com/place/v2/?address="+address+"&output=json&ak=vep5GjMYc8aTIViK0MzvLKCuKaumDfHn";//("http://api.map.baidu.com/geocoder/v2/?" +"ak=7lhI6lW0NhPr0we2Yo3vitwBCQ70jugk&output=json&address=%s",address);
            URL myURL = null;
            URLConnection httpsConn = null; //进行转码
            try {
                myURL = new URL(url);
            } catch (MalformedURLException e) {

            } try {
                httpsConn = (URLConnection) myURL.openConnection();
                if (httpsConn != null) {
                    InputStreamReader insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                    BufferedReader br = new BufferedReader(insr);
                    String data = null;
                    if ((data = br.readLine()) != null) {
                        System.out.println(data);
                        if(data.charAt(10) == '0'){
                            lat = data.substring(data.indexOf("\"lat\":") + ("\"lat\":").length(), data.indexOf("},\"precise\""));
                            lng = data.substring(data.indexOf("\"lng\":") + ("\"lng\":").length(), data.indexOf(",\"lat\""));
                        }
                        else {
                            lat = "0.0000000";
                            lng = "0.0000000";
                        }

                    } insr.close();
                }
            } catch (IOException e) {

            }
            latAndLng.add(lat.substring(0,8));
            latAndLng.add(lng.substring(0,8));
            locations.put(addr, latAndLng);
        }
        return locations;
    }

    public static void main(String[] args){
        List<String> cities = new ArrayList<>();
        cities.add("漳州");
        cities.add("渡口");
        LocationService locationService = new LocationService();
        System.out.println(locationService.getLatAndLngByAddresses(cities).get("漳州"));
        System.out.println(locationService.getLatAndLngByAddresses(cities).get("渡口"));

    }
}
