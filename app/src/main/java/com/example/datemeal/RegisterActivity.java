package com.example.dateMeal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnRegister;
    Member member = new Member();

    EditText etName = (EditText) findViewById(R.id.etName);
    EditText etAge = (EditText) findViewById(R.id.etAge);
    EditText etPassword = (EditText) findViewById(R.id.etPassword);
    EditText etEmail = (EditText) findViewById(R.id.etEmail);
    EditText etPhoneNum = (EditText) findViewById(R.id.etPhoneNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = (Button) findViewById(R.id.btnRegister);
    }

    public  void registerMember(View view){


            member.setName(etName.getText().toString());
            member.setAge(Integer.parseInt(etAge.getText().toString()));
            member.setPassword(etPassword.getText().toString());
            member.setEmail(etEmail.getText().toString());
            member.setPhone(Integer.parseInt(etPhoneNum.getText().toString()));

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSexGroup);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        // find the radio button by returned id
        radioSexButton = (RadioButton) findViewById(selectedId);
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rbMale:
                if (checked)
                   member.setGender("Male");
                    break;
            case R.id.rbFemale:
                if (checked)
                    member.setGender("Female");
                    break;
        }



        try {
            String url = getApplicationContext().getString(R.string.register_member_url);
            makeServiceCall(this, url , member);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
    public void makeServiceCall(Context context, String url, final Member member){
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        //Send data
        try {
            StringRequest postRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(), "Record saved. " + response, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error. " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("name",member.getName());
                    params.put("gender", member.getGender());
                    params.put("age", member.getAge()+ "");
                    params.put("phone", member.getPhone()+ "");
                    params.put("email", member.getEmail());
                    params.put("password", member.getPassword());
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
