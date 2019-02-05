package com.example.arsalanabrar.pokemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
   // private RecyclerAdapter mAdapter;
   String title,url,baseexp,height,order,weight,image;
   List<String>getAbilityname=new ArrayList<String>();
    List<String>getypename=new ArrayList<String>();

    List<Data> data=new ArrayList<Data>();
    List<pokemoninfo> pok_info=new ArrayList<pokemoninfo>();

    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycleview1);

        mAdapter = new RecyclerAdapter(data,getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Data userifo = data.get(position);
                pokemoninfo pokemoninfo=pok_info.get(position);
                Intent i =new Intent(MainActivity.this,Info.class);
                i.putExtra("name",userifo.getTitle());
                i.putExtra("image",userifo.getAvatar_url());
                i.putExtra("hieght",pokemoninfo.getHieght());
                i.putExtra("weight",pokemoninfo.getWeight());
                i.putExtra("basexp",pokemoninfo.getBaseexp());
                i.putExtra("order",pokemoninfo.getOrder());


                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        getdatafromserver();
    }
    private void getdatafromserver() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://pokeapi.co/api/v2/pokemon/\n", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response", response.toString());
                String res = response.toString();
                try {
                    JSONObject jsonObject = new JSONObject(res);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i=0;i<jsonArray.length();i++) {
                        JSONObject jobj = (JSONObject) jsonArray.get(i);
                        title = jobj.getString("name");
                        url = jobj.getString("url");
                        getmoreinfo(url,title);
//String a=height;
                  //      Data userdat=new Data(title,url);
                    //    data.add(userdat);

                    }
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        VolleyRequest.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

    }
private void getmoreinfo(final String url, final String title){
    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.d("response", response.toString());
            String res = response.toString();
            try {
                JSONObject jsonObject = new JSONObject(res);
                height=jsonObject.getString("height");
                baseexp=jsonObject.getString("base_experience");
                weight=jsonObject.getString("weight");
                order=jsonObject.getString("order");

                JSONObject img=jsonObject.getJSONObject("sprites");
                image=img.getString("front_default");
                JSONArray jsonArray1=jsonObject.getJSONArray("types");
                JSONArray jsonArray = jsonObject.getJSONArray("abilities");
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject jobj = (JSONObject) jsonArray.get(i);
                    JSONObject jsonObject1 = jobj.getJSONObject("ability");
                    getAbilityname.add(jsonObject1.getString("name"));
                }
                for (int i=0;i<jsonArray1.length();i++){
                    JSONObject jsonObject1=(JSONObject)jsonArray1.get(i);
                    JSONObject jsonObject2=jsonObject1.getJSONObject("type");
                    getypename.add(jsonObject2.getString("name"));
                }
                Data userdat=new Data(title,url,image);
                    data.add(userdat);
                pokemoninfo pokemoninfo=new pokemoninfo(height,order,baseexp,weight);
                pok_info.add(pokemoninfo);
                mAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }
    );
    VolleyRequest.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest1);

}

    }

