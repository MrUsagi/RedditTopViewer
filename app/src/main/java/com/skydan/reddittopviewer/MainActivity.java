package com.skydan.reddittopviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        RedditClient client = new RedditClient(this);
        try{
            client.getToken();
        }
        catch (JSONException j){
            j.printStackTrace();
        }
        RedditPostAdapter adapter = new RedditPostAdapter(client.getPosts(), this);
        rv.setAdapter(adapter);
    }
}