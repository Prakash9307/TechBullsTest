package com.example.techbullstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DownloadComplete {

    RecyclerView recycler_movies;
    EditText edit_name;
    Button btn_search;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        downloadData("batman");
    }

    public void initUI()
    {
        edit_name = findViewById(R.id.edit_name);
        btn_search = findViewById(R.id.btn_search);

        recycler_movies = findViewById(R.id.recycler_movies);

        btn_search.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.btn_search)
        {
            if (edit_name.getText().toString().equals(""))
            {
                edit_name.setError(getString(R.string.please_enter_name));
            }
            else
            {
                downloadData(edit_name.getText().toString());
            }
        }
    }

    public void downloadData(String text)
    {
        DownloadMovieList downloadMovieList = new DownloadMovieList(this,this);
        downloadMovieList.downloadPaymentOptions(text);
    }

    @Override
    public void downloadSuccess(ArrayList<MovieBean> arrayList)
    {
        AdapterMovieList adapterMovieList = new AdapterMovieList(this,arrayList);
        recycler_movies.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recycler_movies.setItemAnimator(new DefaultItemAnimator());
        recycler_movies.setAdapter(adapterMovieList);
    }

    @Override
    public void downloadFailed()
    {

    }
}