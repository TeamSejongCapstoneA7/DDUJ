package com.lyj.direction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String API_KEY = "AIzaSyByRMe8nJ5cp0bKSKcpQLM5URiu07U1DvQ"; // Google Maps API 키
    private static final String ORIGIN = "Seattle"; // 출발지
    private static final String DESTINATION = "San+Francisco"; // 목적지

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // API 요청 실행
        new DistanceMatrixTask().execute();
    }

    private class DistanceMatrixTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            // API 요청 URL 생성
            String url = "https://maps.googleapis.com/maps/api/distancematrix/json"
                    + "?origins=" + ORIGIN
                    + "&destinations=" + DESTINATION
                    + "&key=" + API_KEY;

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            if (response != null) {

                try {
                    // JSON 응답 데이터 파싱
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray rowsArray = jsonObject.getJSONArray("rows");
                    JSONObject rowObject = rowsArray.getJSONObject(0);
                    JSONArray elementsArray = rowObject.getJSONArray("elements");
                    JSONObject elementObject = elementsArray.getJSONObject(0);
                    JSONObject durationObject = elementObject.getJSONObject("duration");

                    // 소요 시간(duration) 추출
                    String durationText = durationObject.getString("text");

                    Log.d(TAG, "목적지: " + DESTINATION);
                    Log.d(TAG, "소요 시간: " + durationText);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG,"NOOOOOOO");
                }
            }
            else{
            }
        }
    }
}