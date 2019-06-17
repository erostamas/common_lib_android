package com.erostamas.common;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

import redis.clients.jedis.Jedis;

public class RedisClient extends AsyncTask<Void, Integer, Map<String, String>> {

    private String _mapName;
    private String _redisIpAddress;
    private TextView _resultTextView;

    public RedisClient(String redisIpAddress, String mapName, TextView resultTextView) {
        _redisIpAddress = redisIpAddress;
        _mapName = mapName;
        _resultTextView = resultTextView;
    }

    @Override
    protected Map<String, String> doInBackground(Void... params) {
        Map<String, String> ret = null;
        try {
            Jedis jedis = new Jedis(_redisIpAddress, 6379);
            jedis.connect();

            if (jedis.exists(_mapName)) {
                ret = jedis.hgetAll(_mapName);
                for (int i = 0; i < ret.size(); i++) {
                    Log.i("lulu", "getMap" + ret);
                }
            }

        } catch (Exception e) {
            Log.e("brewer", "Exception during accessing redis: " + e.getMessage());
        }
        return ret;
    }

    @Override
    protected void onPostExecute(Map<String, String> values) {
        _resultTextView.setText(values.toString());
    }
}
