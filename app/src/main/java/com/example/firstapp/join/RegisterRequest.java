package com.example.firstapp.join;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;


public class RegisterRequest extends StringRequest {

    final static private String URL= "http://phliopox327.dothome.co.kr/Register.php";
    private Map<String,String> map;


    public RegisterRequest(String UserEmail,String UserPwd, String UserName, Response.Listener<String> listener) {
        super(Method.POST, URL, listener,null);

        map = new HashMap<>();
        map.put("UserEmail",UserEmail);
        map.put("UserPwd", UserPwd);
        map.put("UserName", UserName);

    }

    @Nullable
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
