package Dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Step st1 = new Step("Alexey", "12:23:21", 10, 3);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        String jsonString = gson.toJson(st1);

        Step step2= new Gson().fromJson(jsonString,Step.class);
        System.out.println(step2);

        System.out.println(jsonString);
    }
}
