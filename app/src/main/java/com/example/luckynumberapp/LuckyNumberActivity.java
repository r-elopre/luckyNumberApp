package com.example.luckynumberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LuckyNumberActivity extends AppCompatActivity {

    TextView textView2, textView3;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_number);

        textView2 = findViewById(R.id.WelcomeTxt);
        textView3 = findViewById(R.id.luckyNumberTxt);
        button2 = findViewById(R.id.share_btn);

        //getting the name
        Intent i = getIntent();
        String username = i.getStringExtra("name");
        Toast.makeText(this, "welcome " + username, Toast.LENGTH_LONG).show();

        //generate random number
        int generatedRandomNumber = generateNumber();
        textView3.setText(""+generatedRandomNumber);

        //share data
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(username, generatedRandomNumber);

            }
        });



    }
    public int generateNumber(){
        Random random = new Random();
       int numberGenerated = random.nextInt(1000);
       return numberGenerated;

    }

    //share data
    public void shareData(String username, int generateRandomNumber){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("plain/text");

        String number = String.valueOf(generateRandomNumber);

        i.putExtra(Intent.EXTRA_SUBJECT, username + " got lucky today!");
        i.putExtra(Intent.EXTRA_TEXT,"his lucky number is: "+ number);

        startActivity(Intent.createChooser(i,"select a platform"));
    }




}