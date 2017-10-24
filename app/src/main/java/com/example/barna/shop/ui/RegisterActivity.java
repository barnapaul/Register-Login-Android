package com.example.barna.shop.ui;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.barna.shop.model.Person;
import com.example.barna.shop.R;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {

    public final static String TAG = "RegisterActivity";

    public final static String PERSON = "Person";

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText phone;
    EditText faculty;
    EditText year;
    EditText regPassword;
    EditText confirmPassword;
    Button register;
    Button backToLog;

    String selectedItem;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        faculty = (EditText) findViewById(R.id.faculty);
        year = (EditText) findViewById(R.id.year);
        regPassword = (EditText) findViewById(R.id.regPassword);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        register = (Button) findViewById(R.id.register);
        backToLog = (Button) findViewById(R.id.backToLog);

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = spinner.getSelectedItem().toString();

                if (selectedItem.equals(PERSON)) {
                    faculty.setVisibility(View.GONE);
                    year.setVisibility(View.GONE);
                } else {
                    faculty.setVisibility(View.VISIBLE);
                    year.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        backToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtolog = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(backtolog);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem.equals("Student"))
                    registerStudent();
                else if (selectedItem.equals("Person")) {
                    registerPerson();
                }
            }
        });
    }

    public void registerPerson() {
        if (firstName.getText().toString().isEmpty()
                || lastName.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()
                || phone.getText().toString().isEmpty()
                || regPassword.getText().toString().isEmpty()
                || confirmPassword.getText().toString().isEmpty()) {
            popUp("You cannot have emthy fields");
        } else if (verifyFields()) {

            Person person = new Person.Builder()
                    .setFirstName(firstName.getText().toString())
                    .setLastName(lastName.getText().toString())
                    .setEmail(email.getText().toString())
                    .setPhone(phone.getText().toString())
                    .setPassword(regPassword.getText().toString())
                    .setType(selectedItem)
                    .buildPerson();

            if (User.canRegister(email.getText().toString())) {
                User.addUser(person);
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

                Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                register.putExtra("USER_TYPE", selectedItem.toString());
                startActivity(register);
            } else {
                popUp("This is email already exists");
            }
        }
    }


    private void registerStudent() {
        if (firstName.getText().toString().isEmpty()
                || lastName.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()
                || phone.getText().toString().isEmpty()
                || faculty.getText().toString().isEmpty()
                || year.getText().toString().isEmpty()
                || regPassword.getText().toString().isEmpty()
                || confirmPassword.getText().toString().isEmpty()) {
            popUp("You cannot have emthy fields");
        } else if (verifyFields()) {

            Student student = new Person.Builder()
                    .setFirstName(firstName.getText().toString())
                    .setLastName(lastName.getText().toString())
                    .setEmail(email.getText().toString())
                    .setPhone(phone.getText().toString())
                    .setPassword(regPassword.getText().toString())
                    .setFaculty(faculty.getText().toString())
                    .setYear(Integer.valueOf(year.getText().toString()))
                    .setType(selectedItem)
                    .buildStudent();


            if (User.canRegister(email.getText().toString())) {
                User.addUser(student);
                Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

                Intent register = new Intent(RegisterActivity.this, LoginActivity.class);
                register.putExtra("USER_TYPE", selectedItem.toString());
                startActivity(register);
            } else {
                popUp("This is email already exists");
            }
        }
    }

    public boolean verifyFields() {
        if (validEmail()) {
            if (phone.length() > 6 && phone.length() < 14) {
                if (regPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    return true;
                } else {
                    popUp("Retype correctly the password");
                }
            } else {
                popUp("Your phone number should have more or less numbers");
            }
        } else {
            popUp("Retype your email correctly");
        }
        return false;
    }

    public boolean validEmail() {
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

        String etEmail = email.getText().toString();

        Matcher matcher = Pattern.compile(validemail).matcher(etEmail);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    public void popUp(String m) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setPositiveButton("OK", null);
        builder.setMessage(m);
        builder.show();
    }

    public void log(String msg) {
        Log.d(TAG, msg);
    }


}


