package com.nguyenoanh.downloadfile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nguyenoanh.downloadfile.ItemUserAdapter;
import com.nguyenoanh.downloadfile.Model.ItemUser;
import com.nguyenoanh.downloadfile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayList extends AppCompatActivity {

    private final String JSON_URL ="https://jsonplaceholder.typicode.com/users";

    String dataJson;
    JSONObject jsonObject;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;

    ItemUserAdapter adapter;
    RecyclerView recyclerView;
    List<ItemUser> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.layout_display_list);

        dataJson = getIntent ().getExtras ().getString ("json_data");

        recyclerView = (RecyclerView) findViewById (R.id.recycler_view);
        list = new ArrayList<> ();

        dataJson = getIntent ().getExtras ().getString ("json_data");

        request = new JsonArrayRequest (JSON_URL, new Response.Listener<JSONArray> () {

            @Override
            public void onResponse(JSONArray response) {
                jsonObject = null;

                for (int i = 0; i < response.length (); i++) {
                    try {
                        jsonObject = response.getJSONObject (i);
                        ItemUser item = new ItemUser ();

                        item.setId (jsonObject.getString ("id"));
                        item.setName (jsonObject.getString ("name"));
                        item.setEmail (jsonObject.getString ("email"));
                        item.setAddress (null);
//                        item.setAddress (jsonObject.getString ("address"));
                        item.setPhone (jsonObject.getString ("phone"));
                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }
                }

                setupRecyclerView (list);
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(DisplayList.this);
        requestQueue.add(request) ;
    }


        private void setupRecyclerView( List<ItemUser> list){
            adapter = new ItemUserAdapter (this,list) ;
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager (this));
            recyclerView.setAdapter (adapter);
        }

}
