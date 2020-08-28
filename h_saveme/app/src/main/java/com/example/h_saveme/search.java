package com.example.h_saveme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.SearchView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class search extends AppCompatActivity {
    Context context;
    ImageButton FilterBtn;
    RadioGroup radioGroup;
    TextView searchType;
    ImageView searchImage;
    SearchView searchInput;
    int searchTypeValue = 0;
    int rad;
    RecyclerView recyclerView;
    List<Item> items;
    RequestQueue requestQueue;
    RecyclerViewAdapter  adapter;


  Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       // toolbar =(Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
      // getSupportActionBar().setTitle("");
       //toolbar.setNavigationIcon(R.drawable.backkk);
      /*  if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        context = this;
        FilterBtn = findViewById(R.id.btnFilter);
        searchType = findViewById(R.id.searchType);
        searchInput = findViewById(R.id.searchInput);
        searchImage = findViewById(R.id.searchImage);

        FilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectSearchType();
            }
        });
      /*  searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search();
                Log.d("sadas","dafd");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
        searchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                return false;
            }
        });
    }



    void showSelectSearchType() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final View picker_layout = LayoutInflater.from(context).inflate(R.layout.dialoge_search_type, null);
        builder.setView(picker_layout);
        radioGroup = picker_layout.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rad = radioGroup.getCheckedRadioButtonId();
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                checkSelected(rad);
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    void checkSelected(int id) {
        switch (id) {
            case R.id.location:
                searchTypeValue = 1;
                searchType.setText(getText(R.string.based_on_location));
                searchImage.setImageDrawable(getResources().getDrawable(R.drawable.hospit,getTheme()));

                break;
               case R.id.clinic:
                searchTypeValue = 2;
                searchType.setText(getText(R.string.based_on_clink));
                searchImage.setImageDrawable(getResources().getDrawable(R.drawable.location,getTheme()));
                search();

                break;
            case R.id.department:
                searchTypeValue = 3;
                searchType.setText(getText(R.string.based_on_department));
                searchImage.setImageDrawable(getResources().getDrawable(R.drawable.departmantt,getTheme()));
                break;
            case R.id.device:
                searchTypeValue = 4;
                searchType.setText(getText(R.string.based_on_device));
                searchImage.setImageDrawable(getResources().getDrawable(R.drawable.health,getTheme()));
                break;
            case R.id.doctor:
                searchTypeValue = 5;
                searchType.setText(getText(R.string.based_on_doctor));
                searchImage.setImageDrawable(getResources().getDrawable(R.drawable.doctor,getTheme()));
                break;
            case R.id.medical:
                searchTypeValue = 6;
                searchType.setText(getText(R.string.based_on_test));
                searchImage.setImageDrawable(getResources().getDrawable(R.drawable.medical,getTheme()));
                break;

        }
    }

    void search() {
   //   String searchTerm = searchInput.getText().toString();
        switch (searchTypeValue) {
            case 1:
            case 2:Clink();
            case 3:
            case 4:
            case 5:
            case 6:

        }
    }





     void Clink(){

         setContentView(R.layout.activity_search);
        items = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
        parseJSON();

        SearchView searchView = findViewById(R.id.searchInput);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override///////////////////////
//            public boolean onQueryTextSubmit(String query) {
//                adapter.getFilter().filter(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//



    }

   void parseJSON(){

        String URL = "http://192.168.2.5/saveme/public/api/clinks";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject jsonObject = jsonArray.getJSONObject(i);


                                String name = jsonObject.getString("name");
                                Item item = new Item(name);
                                Log.d("search",name + "/n");
                                //  String status = jsonObject.getString("status");
                                //   String address = jsonObject.getString("address");
                                //   Item item = new Item(name,status,address);
                                items.add(item);
                            }

                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}






