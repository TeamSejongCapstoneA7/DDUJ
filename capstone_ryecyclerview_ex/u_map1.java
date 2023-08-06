package com.example.capstone_ryecyclerview_ex;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.capstone_ryecyclerview_ex.jeju_setplan2.ItemModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class u_map1 extends AppCompatActivity {

    ArrayList<ItemModel> dataList; //영진코드
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_map1);


        //저장된 데이터 읽기 영진쓰!!!!!!!!!!!
        sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        String json = sharedPreferences.getString("dataList", "defaultValue");
        Log.d("확인",json);
        if (!json.isEmpty()) {
            Gson gson = new Gson();
            dataList= gson.fromJson(json, new TypeToken<ArrayList<ItemModel>>(){}.getType());
            // 데이터 사용
        }

        for (ItemModel item : dataList) {
            Log.d("MainActivity", "Item: " + item.getName() + ", Quantity: " + item.getType());
            int a = item.getType();

            //getType()이 int형인 걸 보니 자료형도 유지가 되는 거 같은
        }

        //저장된 데이터 읽기


        Toast.makeText(u_map1.this, "추천:한라 서적타운/함쉐프키친/M1971 요트투어", Toast.LENGTH_LONG).show();



        //intent하는 부분 입니다.
        //


        Button buttonInsert = (Button)findViewById(R.id.backbutton);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //Intent intent =new Intent(getApplicationContext(), tour_MainActivity.class);//코멘트엑티비티자바랑 연결
                //startActivityForResult(intent, 102);

            }
        }); //동선 재설정하기

        Button top_button = (Button)findViewById(R.id.next_button);
        top_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent =new Intent(getApplicationContext(), lodging_MainActivity.class);//코멘트엑티비티자바랑 연결
                //startActivityForResult(intent, 103);

            }
        }); //동선 확정하기


    }

}