package com.example.hardik.placesmachintest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class FragmentData extends Fragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Places>mArrayPlaces;
    private AdapterPlaces mAdapterPlaces;
    private RequestQueue mRequestQueue;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_fragment_data,null,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        mLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRequestQueue= Volley.newRequestQueue(getActivity());

        setHasOptionsMenu(true);

        loadData();


        return view;

    }

    public void loadData()
    {
        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=18.509118,73.832644&radius=500&types=cafe&name=cafe&key=AIzaSyAuGEGHwpBK0iPt0Qxp_2s_vsVfbvDfH28";


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                mArrayPlaces = new ArrayList<>();

                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        JSONObject jGeometry=jsonObject.getJSONObject("geometry").getJSONObject("location");
                        String icon = jsonObject.getString("icon");
                        String name = jsonObject.getString("name");
                        String address = jsonObject.getString("vicinity");
                        String rating = jsonObject. getString("rating");
                        double lat=jGeometry.getDouble("lat");
                        double lng=jGeometry.getDouble("lng");

                        Places places = new Places(name, address, rating, icon,lat,lng);
                        mArrayPlaces.add(places);
                        mAdapterPlaces = new AdapterPlaces(getActivity(),mArrayPlaces);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mRecyclerView.setAdapter(mAdapterPlaces);
                mAdapterPlaces.notifyDataSetChanged();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Error while fatching",Toast.LENGTH_LONG).show();
            }
        });
        mRequestQueue.add(jsonObjectRequest);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
            inflater.inflate(R.menu.menu_fragment_data,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.map) {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("Data", mArrayPlaces);
            startActivity(intent);
        }
        return true;
    }
}
