package com.example.dateMeal;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Acer on 27-Dec-16.
 */

public class RegisterRequest extends StringRequest {
    private  static  final String REGISTER_REQUEST_URL = "http://datemeal.esy.es/register_member.php";
    private Map<String,String> params;

    public  RegisterRequest(String name, int age, String gender, int phone, String email, String password, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("age", age + "");
        params.put("name", name);
        params.put("name", name);
        params.put("name", name);
        params.put("name", name);
    }
}
