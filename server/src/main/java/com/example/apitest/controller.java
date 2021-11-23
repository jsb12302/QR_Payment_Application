package com.example.apitest;

import com.example.apitest.signup.user.User;
import com.example.apitest.signup.user.UserDTO;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;

@RestController
public class controller {

    @GetMapping("/test")
    public void test() throws UnsupportedEncodingException {

        String txt = "엄광로176";
        String txtEnc = URLEncoder.encode(txt, "UTF-8");

        String url = "https://dapi.kakao.com/v2/local/search/address.json?analyze_type=similar&page=1&size=10&query="+txtEnc;
        String authorizationKey = "KakaoAK a85dda23e2cf591d865b03498582c750";
        URL urlObject = null;
        HttpURLConnection con = null;
        StringBuffer response = new StringBuffer();
        String result="";
        try {
            urlObject = new URL(url);
            con = (HttpURLConnection) urlObject.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", authorizationKey);

            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            BufferedReader iny = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String output;

            while ((output = iny.readLine()) != null) {
                response.append(output);
            }

            iny.close();
            result = response.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jObject = new JSONObject(result);

        JSONArray jArray = jObject.getJSONArray("documents");
        JSONObject jsonObject = jArray.getJSONObject(0);
        JSONObject jsonObject2 = jsonObject.getJSONObject("address");
        String x=jsonObject2.getString("x");
        String y=jsonObject2.getString("y");
        System.out.printf(x);
        System.out.printf(y);

    }

    @GetMapping("/test2")
    @ResponseBody
    public Item test(@RequestParam("name") String name){
        System.out.println(name);
        Item item=new Item("123",name+"hi");
        return item;
    }

//    @GetMapping("/test") //필드 각각 테스트
//    @ResponseBody
//    public void test(@RequestParam("userId") String userId,@RequestParam("userPwd") String userPwd,
//                     @RequestParam("userPwd2") String userPwd2,@RequestParam("userName") String userName
//                    ,@RequestParam("userHP") String userHP) {
//        System.out.println(userId);
//        System.out.println(userHP);
//    }

//    @PostMapping("/test3") //유저 객체 테스트
//    @ResponseBody
//    public User test(@RequestBody UserDTO userDTO) {
//        System.out.println("connect");
//        User user=new User(1,userDTO.getUserId(), userDTO.getUserPwd(),
//                userDTO.getUserPwd2(), userDTO.getUserName(), userDTO.getUserHP());
//        System.out.println("connect2");
//        return user;
//    }

}
