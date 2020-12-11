package com.skydan.reddittopviewer;

import android.content.Context;
import android.content.SharedPreferences;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RedditClient {
    private SharedPreferences _pref;
    private String _token;
    private String _posts;
    private Context _context;
    private static AsyncHttpClient _client = new AsyncHttpClient();

    public RedditClient(Context context){
        this._context = context;
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        _client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {

        _client.post(url, params, responseHandler);
    }

    public void getToken() throws JSONException {

        _pref = _context.getSharedPreferences("AppPref",Context.MODE_PRIVATE);
        String uuid = UUID.randomUUID().toString();

        RequestParams requestParams = new RequestParams();
        requestParams.put("grant_type","https://oauth.reddit.com/grants/installed_client");
        requestParams.put("device_id",uuid);

        post("https://www.reddit.com/api/v1/access_token", requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    _token = response.getString("access_token").toString();
                    SharedPreferences.Editor edit = _pref.edit();
                    edit.putString("token",_token);
                    edit.commit();
                }catch (JSONException j)
                {
                    j.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
    public List<RedditPost> getPosts() {
        List<RedditPost> list = new ArrayList<>();
        RequestParams requestParams = new RequestParams();
        requestParams.put("limit", 100);
        _client.addHeader("Authorization", "bearer " + _pref.getString("token", ""));
        _client.addHeader("User-Agent", "RedditTopViewer/0.1 by MrUsagi22");

        _client.get(_context, "https://www.reddit.com/top.json", requestParams, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray children = response.getJSONArray("children");
                    for(int i=0;i<children.length();i++){
                        JSONObject data = children.getJSONObject(i);
                        RedditPost post = new RedditPost();
                        post.setAuthor(data.getString("author"));
                        post.setDateOfPost(data.getLong("created"));
                        post.setThumbnailImageURL(data.getString("thumbnail"));
                        post.setCommentNumber(data.getInt("num_comments"));
                        list.add(post);
                    }
                } catch (JSONException j) {
                    j.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
        return list;
    }
}
