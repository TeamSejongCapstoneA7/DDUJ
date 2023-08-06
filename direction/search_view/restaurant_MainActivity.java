package com.lyj.direction.search_view;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lyj.direction.R;

import java.util.ArrayList;
import java.util.List;

public class restaurant_MainActivity extends AppCompatActivity implements restaurant_ItemAdapter.onItemListener {

    private static final String TAG ="" ;
    private restaurant_ItemAdapter adapter;
    private List<restaurant_ItemModel> itemList;


    String[][] arr ={  {"앙뚜아네트 용담점", "제주특별자치도 제주시 서해안로 671", "1","0","0"},
            {"돈이랑 본점", "제주특별자치도 서귀포시 남원읍 신례동로 256", "2","0","0"},
            {"도토리키친 본점", "제주특별자치도 제주시 북성로 59 1층", "3","0","0"},
            {"삼미횟집", "제주특별자치도 제주시 도두항서5길 1", "4","0","0"},
            {"하원가흑돼지", "제주특별자치도 서귀포시 이어도로 278", "5","0","0"},
            {"바다를본돼지 제주공항점", "제주특별자치도 제주시 정존15길 42 1층", "6","0","0"},
            {"몬스터살롱", "제주특별자치도 제주시 애월읍 일주서로 6017", "7","0","0"},
            {"닻", "제주특별자치도 제주시 애월읍 하귀2리 2726", "8","0","0"},
            {"칠성뷔페", "제주특별자치도 제주시 관덕로11길 17 비버리힐 4층", "9","0","0"},
            {"뚱딴지", "제주특별자치도 제주시 애월읍 부룡수길 17", "10","0","0"},
            {"도민상회 본점 제주한림협재흑돼지", "제주특별자치도 제주시 한림읍 한림로3길 8", "11","0","0"},
            {"프로젝트064", "제주특별자치도 제주시 구남동4길 31", "12","0","0"},
            {"미쿠니", "제주특별자치도 제주시 서흘길 41 1층", "13","0","0"},
            {"중문맛집 회포장센터 새벽야시장", "제주특별자치도 서귀포시 중문관광로 293", "14","0","0"},
            {"삼다도횟집 본점", "제주특별자치도 제주시 서해안로 572", "15","0","0"},
            {"함쉐프키친", "제주특별자치도 서귀포시 대포중앙로 116", "16","33.2534",	"126.4288"},
            {"제주공항근처맛집 오멍가멍 흑돼지 본점", "제주특별자치도 제주시 서해안로 272 1층", "17","0","0"},
            {"더스푼", "제주특별자치도 제주시 구남동1길 45 1층", "18","0","0"},
            {"글라글라하와이", "제주특별자치도 서귀포시 대정읍 하모항구로 70", "19","0","0"},
            {"흑본오겹 함덕점", "제주특별자치도 제주시 조천읍 신북로 454", "20","0","0"},
            {"컬쳐드", "제주특별자치도 제주시 승천로 81 지하1층 2호", "21","0","0"},
            {"그리다앤쿡", "제주특별자치도 제주시 신성로 111 2층", "22","0","0"},
            {"이런날엔", "제주특별자치도 제주시 구좌읍 해맞이해안로 1000", "23","0","0"},
            {"소렉", "제주특별자치도 제주시 은남2길 41 1층", "24","0","0"},
            {"모모제이", "제주특별자치도 제주시 인다8길 36-1 1층", "25","0","0"},
            {"일품순두부 제주본점", "제주특별자치도 제주시 광평동로 25 1층", "26","0","0"}

    }; //데이터 배열로 받고

    /*
    String[][] arr ={ {"벌툰 제주한라대점", "제주특별자치도 제주시 진군남4길 5 2층", "1"},
            {"놀숲 서귀포신시가지점", "제주특별자치도 서귀포시 대청로25번길 4 4층 402호", "2"},
            {"휴애리수국축제", "제주특별자치도 서귀포시 남원읍 신례동로 256", "3"},
            {"종달리수국길", "제주특별자치도 제주시 구좌읍 종달리", "4"},
            {"한라서적타운", "제주특별자치도 제주시 동광로16길 5 ", "5"},
            {"책방소리소문", "제주특별자치도 제주시 한경면 저지동길 8-31 1층", "6"},
            {"제주다원 녹차미로공원", "제주특별자치도 서귀포시 색달동 산 52", "7"},
            {"한림공원 아열대식물원", "제주특별자치도 제주시 한림읍 한림로 300", "8"},
            {"M1971 요트투어", "제주특별자치도 서귀포시 대정읍 최남단해안로 128 M1971 요트클럽하우스 1층", "9"},
            {"제주허브동산 별빛놀이", "제주특별자치도 서귀포시 표선면 돈오름로 170", "10"},
            {"포토시그니처 서귀포이중섭거리점", "제주특별자치도 서귀포시 이중섭로 1", "11"},
            {"논짓물해수족욕카페", "제주특별자치도 서귀포시 논짓물로 13", "12"},
            {"에곤", "제주특별자치도 서귀포시 성산읍 신고로 52-18 1층", "13"},
            {"랩툰", "제주특별자치도 제주시 중앙로 222 일화빌딩 6층", "14"},
            {"성수미술관 제주특별점", "제주특별자치도 제주시 구좌읍 해맞이해안로 1726", "15"},
            {"그림포레스트", "제주특별자치도 서귀포시 중문관광로110번길 32", "16"},
            {"킬링스페이스 제주시청점", "제주특별자치도 제주시 광양8길 26 2층", "17"},
            {"콩툰만화카페", "제주특별자치도 서귀포시 동홍중앙로 49 5층", "18"},
            {"제주반지만들기", "제주특별자치도 제주시 신대로 60 1층", "19"},
            {"반지마을 제주1호점", "제주특별자치도 제주시 월성로4길 17 3층", "20"},
            {"제주로운", "제주특별자치도 서귀포시 남원읍 중산간동로 6596-3", "21"},
            {"모다드렁", "제주특별자치도 제주시 애월읍 유수암평화5길 20", "22"},
    };  //데이터 배열로 받고

     */
    int page =0;
    //List<String> list = Arrays.asList(arr);  //리스트화 해서
    int time_hour=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_activity_main);

        //여기에 서버 연결 함수 쓸 것
        //connection.setRequestProperty("function","select");
        //connection.setRequestProperty("table_name", "tour_list");
        //arr에 받고 처리

        setUpRecyclerView();

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
        adapter = new restaurant_ItemAdapter(itemList);
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

        for(int i =0;i<26;i++){
            String imageName = "f_img" + (i + 1);//파일 이름 생성
            int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName()); // 이미지 리소스 ID 가져오기
            itemList.add(new restaurant_ItemModel(resourceId, arr[i][1], arr[i][0], arr[i][2],arr[i][3],arr[i][4]));
            Log.d(TAG,arr[i][0]);
        }
        /*
        itemList = new ArrayList<>(); //샘플테이터
        itemList.add(new ItemModel(R.drawable.shoping, list.get(0), list.get(1)));
        */
        /*
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



//////////////////////
    /****************************************************
     리사이클러뷰 클릭이벤트 인터페이스 구현
     ***************************************************/
    @Override
    public void onItemClicked(int position) {
        //1.팝업에서 시간이랑 저장여부 묻기
        showNumberInputDialog(position);


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
                intent.putExtra("restaurant_name", arr[position][0]);//array가 잘 넘어가는지모르곘음
                intent.putExtra("restaurant_address", arr[position][1]);
                intent.putExtra("number", arr[position][2]);
                //체류 시간 intent
                intent.putExtra("time_hour", time_hour);

                //경도,위도 intent
                intent.putExtra("x", arr[position][3]);
                intent.putExtra("y", arr[position][4]);

                setResult(104, intent);

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
