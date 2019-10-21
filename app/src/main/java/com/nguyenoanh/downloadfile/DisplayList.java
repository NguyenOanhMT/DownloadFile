package com.nguyenoanh.downloadfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayList extends AppCompatActivity {

    String dataJson;
    JSONObject jsonObject;
    JSONArray jsonArray;
    Gson gson;

    ItemUserAdapter adapter;
    ListView listView;
//    Gson gson = new Gson(); // Or use new GsonBuilder().create();
//    MyType obj1 = new MyType();
//    String json = gson.toJson(obj1); // serializes obj1 to Json
//    MyType obj2 = gson.fromJson(json, MyType.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.layout_display_list);

        listView = (ListView) findViewById (R.id.list_view);
        adapter = new ItemUserAdapter (this,R.layout.item_list );

        dataJson = getIntent ().getExtras ().getString ("json_data");
        try {
            jsonObject = new JSONObject (dataJson);
            jsonArray = new JSONArray ();
            int count = 0;

            int id;
            String name,email, address, phone;
            while (count <jsonObject.length ()){
                JSONObject jo = jsonArray.getJSONObject (count);
                    id = jo.getInt ("id");
                    name = jo.getString ("name");
                    email = jo.getString ("email");
                    address = jo.getString ("address");
                    phone = jo.getString ("phone");

                    ItemUser itemUser = new ItemUser (id,name,email,address,phone);
                    adapter.add (itemUser);
                    count++;
            }
        } catch (JSONException e) {
            e.printStackTrace ();
        }

        listView.setAdapter (adapter);
    }
}
