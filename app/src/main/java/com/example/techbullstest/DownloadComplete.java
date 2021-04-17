package com.example.techbullstest;

import java.util.ArrayList;

public interface DownloadComplete
{
    void downloadSuccess(ArrayList<MovieBean> arrayList);
    void downloadFailed();
}
