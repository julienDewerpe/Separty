package com.example.separty.Tools;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Requests {

    private Context context;
    private RequestQueue queue;

    public Requests(Context context, RequestQueue queue) {
        this.context = context;
        this.queue = queue;
    }

    public void register(final String user, final String password, final String confpass, final RegisterCallback callback) {
        String url = "http://192.168.1.15/repertoire/register.php";

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Map<String, String> errors = new HashMap<>();
                try {
                    JSONObject json = new JSONObject(response);
                    boolean error = json.getBoolean("error");
                    if (!error) {
                        //inscription ok
                        callback.onSuccess("Inscription réussie");
                    } else {
                        JSONObject message = json.getJSONObject("message");
                        if (message.has("user")) {
                            errors.put("user", message.getString("user"));
                        }
                        callback.inputErrors(errors);
                    }
                } catch (JSONException e) {
                    callback.onError("Une erreur s'est produite.");
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    callback.onError("Connexion impossible");
                } else if (error instanceof VolleyError) {
                    callback.onError("Une erreur s'est produite.");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("user", user);
                map.put("mp", password);
                map.put("mp2", confpass);
                return map;
            }
        };
        queue.add(request);
    }

    public interface RegisterCallback {
        void onSuccess(String message);

        void inputErrors(Map<String, String> errors);

        void onError(String message);
    }

    public void connection(final String user, final String password, final LoginCallback callback) {
        String url = "http://192.168.1.15/repertoire/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject json = null;
                try {
                    json = new JSONObject(response);
                    Boolean error = json.getBoolean("error");

                    if (!error) {
                        String id = json.getString("id");
                        String pseudo = json.getString("user");
                        callback.onSuccess(id, pseudo);
                    } else {
                        callback.onError(json.getString("message"));
                    }
                } catch (JSONException e) {
                    callback.onError("Une erreur s'est produite.");
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof NetworkError) {
                    callback.onError("Connexion impossible");
                } else if (error instanceof VolleyError) {
                    callback.onError("Une erreur s'est produite.");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("user", user);
                map.put("mp", password);
                return map;
            }
        };
        queue.add(request);
    }

    public interface LoginCallback {
        void onSuccess(String id, String pseudo);

        void onError(String message);
    }
}
