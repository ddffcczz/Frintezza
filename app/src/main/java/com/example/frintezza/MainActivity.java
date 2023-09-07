package com.example.frintezza;
import static android.view.View.*;
import static com.example.frintezza.MD5.getMd5;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ViewGroup loginContainer= null;
    EditText loginText = null;
    EditText passwdText = null;
    Button loginBtn = null;
    SharedPreferences settings;
    SharedPreferences.Editor prefEditor;
    String token=null;
    private static final String PREFS_FILE = "Account";
    private static final String PREF_NAME = "md5token";
    private WebSocketClient webSocketClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginContainer= findViewById(R.id.inputContainer);
        loginText= findViewById(R.id.inputLogin);
        passwdText= findViewById(R.id.inputPassword);
        loginBtn= findViewById(R.id.loginBtn);
        settings = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        token = settings.getString(PREF_NAME,"fail");
        //showMessage(token);
        if(token!="fail"){
            loginContainer.setVisibility(View.INVISIBLE);
        }
        loginBtn.setOnClickListener(this);

    }
    private void showMessage(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.loginBtn){
            if(loginContainer.getVisibility()== VISIBLE ) {
                //showMessage("two"+token);
                String token = loginText.getText().toString() + passwdText.getText().toString();
                String md5Token = getMd5(token);
                prefEditor = settings.edit();
                prefEditor.putString(PREF_NAME, md5Token);
                prefEditor.apply();
                loginContainer.setVisibility(View.INVISIBLE);
                this.token = md5Token;
            }else{
                showMessage("wait..");
            }
            webSocketClient = new WebSocketClient(MainActivity.this,token,"connect");
        }
    }
}