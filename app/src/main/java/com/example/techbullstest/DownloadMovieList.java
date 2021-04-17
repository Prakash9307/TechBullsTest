package com.example.techbullstest;

import android.app.Activity;
import android.app.ProgressDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DownloadMovieList
{
    Activity activity;
    DownloadComplete listener;
    public DownloadMovieList(Activity activity, DownloadComplete listener)
    {

        this.activity = activity;
        this.listener = listener;
    }

    public void downloadPaymentOptions(String name)
    {
        final ArrayList<MovieBean> al_movies = new ArrayList<>();
        al_movies.clear();

        final ProgressDialog pd = new ProgressDialog(activity);
        pd.setMessage("Downloading movie details...");
        pd.show();
        pd.setCancelable(false);

        String url = "https://www.omdbapi.com/?s="+ name+"&apikey=3a145f7b";


        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest strReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        try
                        {
                            JSONObject root = new JSONObject(response);

                            JSONArray array = root.getJSONArray("Search");

                            for(int i=0; i<array.length(); i++)
                            {
                                JSONObject obj = array.getJSONObject(i);
                                MovieBean movieBean = new MovieBean();
                                movieBean.setTitle(obj.getString("Title"));
                                movieBean.setYear(obj.getString("Year"));
                                movieBean.setImdbID(obj.getString("imdbID"));
                                movieBean.setType(obj.getString("Type"));
                                movieBean.setPoster(obj.getString("Poster"));

                                al_movies.add(movieBean);
                            }

                            if(al_movies.size()>0)
                            {
                                listener.downloadSuccess(al_movies);
                            }

                            pd.dismiss();

                        }catch (Exception e)
                        {
                            listener.downloadFailed();
                            pd.dismiss();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        listener.downloadFailed();
                        pd.dismiss();
                    }
                }
        );

        requestQueue.add(strReq);
    }
}
