package Dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//        Step st1 = new Step("Alexey", "12:23:21", 10, 3);
//        GsonBuilder builder = new GsonBuilder();
//        builder.setPrettyPrinting();
//
//        Gson gson = builder.create();
//        String jsonString = gson.toJson(st1);
//        String formatJsonString= jsonString.replace("\n"," ");
//        System.out.println("FormatJsonString : "+ formatJsonString);
//
//        Step step2= new Gson().fromJson(formatJsonString,Step.class);
//        System.out.println(step2);
//
//        System.out.println(jsonString);


        char a='1',b='2',c='3';
        StringBuffer str1= new StringBuffer();
        str1.append(a);
        str1.append(b);
        str1.append(c);
        System.out.println(str1.toString());
    }
}
