package com.example.capstone_ryecyclerview_ex.search_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone_ryecyclerview_ex.R;
import com.example.capstone_ryecyclerview_ex.jeju_setplan2.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lodging_MainActivity extends AppCompatActivity implements lodging_ItemAdapter.onItemListener {

    private static final String TAG ="" ;
    private lodging_ItemAdapter adapter;
    private List<lodging_ItemModel> itemList;
    String[][] arr ={ {"파르나스 호텔 제주","서귀포,제주도","1","x","y"},
            {"제주 신라 호텔","서귀포,제주도","2","x","y"},
            {"랜딩관 제주신화월드 호텔앤리조트","서귀포,제주도","3","x","y"},
            };  //데이터 배열로 받고
    int time_hour=1;
    int page; //어떤 페이지에서 오는지 확인 200->calendr


    //List<List<String>> arrayList = new ArrayList<>();

    //List<String> list = Arrays.asList(arr);  //리스트화 해서

    /*
    for (String[] array : doubleArray) {
        ArrayList<String> subArrayList = new ArrayList<>();
        for (String element : array) {
            subArrayList.add(element);
        }
        arrayList.add(subArrayList);
    }
    */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lodging_activity_main);


        //여기에 서버 연결 함수 쓸 것
        //connection.setRequestProperty("function","select");
        //connection.setRequestProperty("table_name", "lodging_list");
        //arr에 받고 처리

        setUpRecyclerView();





        Intent intent = getIntent();
        page=intent.getIntExtra("calender",1);

        Log.d("number","page"+page);

    }

    /****************************************************
     리사이클러뷰, 어댑터 셋팅
     ***************************************************/
    private void setUpRecyclerView() {
        //recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        //adapter
        itemList = new ArrayList<>(); //샘플테이터
        fillData();
        adapter = new lodging_ItemAdapter(itemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL); //밑줄
        recyclerView.addItemDecoration(dividerItemDecoration);

        //데이터셋변경시
        //adapter.dataSetChanged(exampleList);

        //어댑터의 리스너 호출
        adapter.setOnClickListener(this);
    }




    private void fillData() {

        for(int i =0;i<3;i++){
            itemList.add(new lodging_ItemModel( arr[i][1], arr[i][0],arr[i][2],arr[i][3],arr[i][4]));
            Log.d(TAG,arr[i][0]);
        }
        /*
        itemList = new ArrayList<>(); //샘플테이터
        itemList.add(new ItemModel(R.drawable.shoping, list.get(0), list.get(1)));
        */
        /*lodging변경 필요
        itemList.add(new tour_ItemModel(R.drawable.shoping, "One", "Ten"));
        itemList.add(new tour_ItemModel(R.drawable.shoping, "Two", "Eleven"));
        itemList.add(new tour_ItemModel(R.drawable.shoping, "Three", "Twelve"));
        itemList.add(new tour_ItemModel(R.drawable.shoping, "Four", "Thirteen"));

        itemList.add(new tour_ItemModel(R.drawable.shoping, "Five", "Fourteen"));
        itemList.add(new tour_ItemModel(R.drawable.shoping, "Six", "Fifteen"));
        itemList.add(new tour_ItemModel(R.drawable.shoping, "Seven", "Sixteen"));
        itemList.add(new tour_ItemModel(R.drawable.shoping, "Eight", "Seventeen"));
        itemList.add(new tour_ItemModel(R.drawable.shoping, "Nine", "Eighteen"));
        */

    }

    /****************************************************
     onCreateOptionsMenu SearchView  기능구현
     ***************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /****************************************************
     리사이클러뷰 클릭이벤트 인터페이스 구현
     ***************************************************/
    @Override
    public void onItemClicked(int position) {

        if(page ==200){ //calender페이지에서 왔을 땐 시간 선택 없음
            Intent intent = new Intent();//코멘트엑티비티자바랑 연결
            intent.putExtra("name", arr[position][0]);//array가 잘 넘어가는지모르곘음
            intent.putExtra("address", arr[position][1]);
            intent.putExtra("number", arr[position][2]);
            //체류 시간 intent
            //intent.putExtra("time_hour", time_hour);

            setResult(103, intent);

            finish();

            page =1;
        }
        //1.팝업에서 시간이랑 저장여부 묻기
        else {
            showNumberInputDialog(position);

        }




        
    }



    private void showNumberInputDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.RoundedDialog);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.u_dialog_number_input, null);
        final EditText numberEditText = dialogView.findViewById(R.id.numberEditText);

        Button positiveButton = dialogView.findViewById(R.id.positiveButton);
        Button negativeButton = dialogView.findViewById(R.id.negativeButton);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.show();

        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberString = numberEditText.getText().toString();
                time_hour = Integer.parseInt(numberString);    //time_hour에 시간 저장함
                dialog.dismiss();

                //2.저장하기 (falg ==1)->intent데이터를 담고 넘어가기
                //Toast.makeText(this, "" +position, Toast.LENGTH_SHORT).show();

                Log.d("position", "{" + position);
                Intent intent = new Intent();//코멘트엑티비티자바랑 연결
                intent.putExtra("name", arr[position][0]);//array가 잘 넘어가는지모르곘음
                intent.putExtra("address", arr[position][1]);
                intent.putExtra("number", arr[position][2]);
                //체류 시간 intent
                intent.putExtra("time_hour", time_hour); //체류시간

                setResult(103, intent);

                finish();
                //숙소에서 intent를 하면 전에 껄로 안들어감 새로운 setplan에 들어가서
                //처음 setplan에 적용이 안됨 페이지가 두개가 됨 (서버에서 해야할 듯)
                //day별로 데이터 저장하는 법
                //여러 액티비티에서 한 액티비티로 이동할 때 값을 넘겨줄려면 각 데이터를 어케 분리해서 넘겨줘야하는 가




            }
        });

        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


}