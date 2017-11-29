package com.example.barna.shop.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.barna.shop.controller.BaseActivity;
import com.example.barna.shop.controller.ValidEmail;
import com.example.barna.shop.model.Person;
import com.example.barna.shop.R;
import com.example.barna.shop.model.Student;
import com.example.barna.shop.model.User;
import com.example.barna.shop.networkrequest.RegisterAPI;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    public final static String PERSON = "Person";
    public final static String STUDENT = "Student";

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

        register.setOnClickListener(this);
        backToLog.setOnClickListener(this);

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


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backToLog:
                startAsActivity(LoginActivity.class);
                break;
            case R.id.register:
                registerUser();
                break;

        }
    }


    public void registerUser(){
        if (verifyFields()) {

            if (selectedItem.equals(STUDENT)) {

                Student student = new Person.Builder()
                        .setFirstName(firstName.getText().toString())
                        .setLastName(lastName.getText().toString())
                        .setEmail(email.getText().toString())
                        .setPhone(phone.getText().toString())
                        .setPassword(regPassword.getText().toString())
                        .setConfirmPassword(confirmPassword.getText().toString())
                        .setFaculty(faculty.getText().toString())
                        .setYear(Integer.valueOf(year.getText().toString()))
                        .setType(selectedItem)
                        .buildStudent();


                setUser(student);




            }else{
                Person person = new Person.Builder()
                        .setFirstName(firstName.getText().toString())
                        .setLastName(lastName.getText().toString())
                        .setEmail(email.getText().toString())
                        .setPhone(phone.getText().toString())
                        .setPassword(regPassword.getText().toString())
                        .setConfirmPassword(confirmPassword.getText().toString())
                        .setType(selectedItem)
                        .buildPerson();



                setUser(person);


            }
        }

    }

    public boolean verifyFields() {

        if (fieldsEmpty()){
            popUp("You cannot have empty fields");
        }else if (ValidEmail.validEmail(email)) {
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

    public boolean fieldsEmpty(){

        if(selectedItem.equals(STUDENT)){
            if(firstName.getText().toString().isEmpty()
                    || lastName.getText().toString().isEmpty()
                    || email.getText().toString().isEmpty()
                    || phone.getText().toString().isEmpty()
                    || regPassword.getText().toString().isEmpty()
                    || confirmPassword.getText().toString().isEmpty()
                    || faculty.getText().toString().isEmpty()
                    || year.getText().toString().isEmpty()){

                return true;
            }
        }else if (firstName.getText().toString().isEmpty()
                || lastName.getText().toString().isEmpty()
                || email.getText().toString().isEmpty()
                || phone.getText().toString().isEmpty()
                || regPassword.getText().toString().isEmpty()
                || confirmPassword.getText().toString().isEmpty()
                ) {
            return true;
        }
        return false;

    }



    public void setUser(Person user) {

        if (User.canRegister(email.getText().toString())) {
//            User.addUser(user);


            new RegisterAPI(user).execute();

            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

            startAsActivity(LoginActivity.class,  true);
        } else {
            popUp("This is email already exists");
        }
    }
}