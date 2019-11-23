package org.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.example.database.DatabaseRepo;
import org.example.database.Signup;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public static final Pattern PASSWORD=
            Pattern.compile("^"+
                    "(?=.*[0-9])" +
                    "(?=\\S+$)" +
                    ".{8,}" +
                    "$"
            );

    EditText ed;
    EditText ps;
    EditText re;
    EditText name;
    DatabaseRepo db;
    List<Signup> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button login;
        login = (Button) findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
        Button signup;
        signup = (Button) findViewById(R.id.button);
        name=(EditText) findViewById(R.id.editText);
        ed= (EditText) findViewById(R.id.editText8);
        ps= (EditText) findViewById(R.id.editText10);
        re= (EditText) findViewById(R.id.editText11);
        db= new DatabaseRepo(getApplicationContext());
        final RadioGroup group=(RadioGroup) findViewById(R.id.radiogroup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if(true) {
                    int id=group.getCheckedRadioButtonId();
                    if(id!=-1)
                    {
                        RadioButton rb =(RadioButton) findViewById(id);
                        if(rb.getText().toString().equals("Male"))
                        {
                            db.insertUser(name.getText().toString(),ed.getText().toString(),ps.getText().toString(),'M');
                        }
                        else
                        {
                            db.insertUser(name.getText().toString(),ed.getText().toString(),ps.getText().toString(),'F');
                        }
                    }
                    //Log.e("User : : : ",list.get(0).getName());
                    check();
                }
            }
        });
        Button exit;
        exit = (Button) findViewById(R.id.btn1);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }
    private boolean validateEmail() {
        String emailInput=ed.getText().toString();
        if (emailInput.isEmpty()) {
            ed.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            ed.setError("Please enter a valid email address");
            return false;
        } else {
            ed.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput=ps.getText().toString();
        if (passwordInput.isEmpty()) {
            ps.setError("Field can't be empty");
            return false;
        } else if (passwordInput.length()<8) {
            ps.setError("Password too weak");
            return false;
        } else {
            ps.setError(null);
            return true;
        }
    }
    private boolean validatRetype() {
        String password=ps.getText().toString();
        String retype=re.getText().toString();
        if(!password.equals(retype)){
            re.setError("Passwords do not match");
            return false;
        }
        else{
            re.setError(null);
            return true;
        }
    }
    public boolean confirmInput(View v) {
        if (!validateEmail() | !validatePassword() | !validatRetype()) {
            return false;
        }
        else{
            return true;
        }
    }
    public void check() {
        Intent intent = new Intent(MainActivity.this, Login.class);
        startActivity(intent);
    }
    public void check1() {
        Intent intent = new Intent(MainActivity.this, Tab.class);
        startActivity(intent);
    }

}
