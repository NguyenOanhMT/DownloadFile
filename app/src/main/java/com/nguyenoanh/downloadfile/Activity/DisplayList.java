package com.nguyenoanh.downloadfile.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
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

        ArrayList<ItemUser> objects = new Gson().fromJson(dataJson, new TypeToken<ArrayList<ItemUser>> (){}.getType());

        Log.d ("abc", objects.toString ());

        list.clear ();
        list.addAll (objects);
        setupRecyclerView (list);
    }

        private void setupRecyclerView( List<ItemUser> list){
            recyclerView.setLayoutManager(new LinearLayoutManager (this));
            adapter = new ItemUserAdapter (this,list) ;
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter (adapter);
            adapter.notifyDataSetChanged ();
        }

}
