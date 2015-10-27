package com.example.elhanan7.lightswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LightSwitchMainActivity extends AppCompatActivity {

    final String USER_AGENT = "Mozilla/5.0";

    private int getCurrentValue() throws Exception {
        URL url = new URL("http://10.0.0.6:8080/get");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            return -1;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine = in.readLine();
        if (inputLine.equals("OFF")) {
            return 0;
        }
        else {
            return 1;
        }
    }

    private void setValue(int state) throws Exception {
        URL url = new URL("http://10.0.0.6:8080/set/" + (state == 0? "off" : "on"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_switch_main);
        // test
    }
}
