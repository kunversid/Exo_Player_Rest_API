package com.visionias.exoplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.visionias.exoplayer.Adapters.ListItemAdapter;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;


public class HomeScreen extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListItemAdapter adapter;
    private ArrayList<ListItem> mListItems;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mRecyclerView = findViewById(R.id.recyclerViewList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mListItems = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();

    }

    private void parseJSON() {
        String url = "http://visionias.in/test/api/";
        final JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                String videoTitle = jsonObject.getString("title");
                                String videoUrl = jsonObject.getString("url");
                                String publishDate = jsonObject.getString("publish_date");
                                String thumbnail = jsonObject.getString("thumbnail");

                                mListItems.add(new ListItem(videoTitle, videoUrl, publishDate, thumbnail));

                            }
                            adapter = new ListItemAdapter(mListItems,HomeScreen.this);
                            mRecyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }
}
