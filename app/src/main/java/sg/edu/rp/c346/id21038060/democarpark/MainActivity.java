package sg.edu.rp.c346.id21038060.democarpark;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    TextView tvTitle;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCarpark = findViewById(R.id.lvCarpark);
        tvTitle = findViewById(R.id.tvTitle);

        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Carpark> alCarpark = new ArrayList<>();
        client.get("https://api.data.gov.sg/v1//transport/carpark-availability", new JsonHttpResponseHandler() {
            String carparkNo;
            String lotsAvailable;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrCarparkData = firstObj.getJSONArray("carpark_data");
                    Log.d("Debug", "No of carparks: "+ jsonArrCarparkData.length());
                    tvTitle.setText("Total Carparks Loaded: "+jsonArrCarparkData.length());
                    for(int i = 0; i < jsonArrCarparkData.length(); i++) { //
                        JSONObject firstObjData = jsonArrCarparkData.getJSONObject(i);
                        carparkNo = firstObjData.getString("carpark_number");
                        Log.d("Debug", carparkNo);

                        JSONArray jsonArrCarparkInfo = firstObjData.getJSONArray("carpark_info");
                        JSONObject firstObjInfo = jsonArrCarparkInfo.getJSONObject(0);
                        lotsAvailable = firstObjInfo.getString("lots_available");
                        Log.d("Debug", lotsAvailable);
                        Carpark carpark = new Carpark(carparkNo, lotsAvailable);
                        alCarpark.add(carpark);
                    }
                }
                catch(JSONException e){

                }
                ArrayAdapter aa = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alCarpark);
                lvCarpark.setAdapter(aa);
            }//end onSuccess
        });
    }//end onResume
}