package com.example.sherpaatourguide.weather;
import android.net.http.SslCertificate;

import org.json.JSONException;
import org.json.JSONObject;

public class weatherData {
    private String mTemperature, mCity, mIcon, mWeatherType;
    private int mCondition;

    public static weatherData fromJson(JSONObject jsonObject) {
        try {
            weatherData weatherD = new weatherData();
            weatherD.mCity = jsonObject.getString("name");
            weatherD.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherD.mWeatherType = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");
            weatherD.mIcon=updateWeatherIcon(weatherD.mCondition);
            double tempResult=jsonObject.getJSONObject("main").getDouble("temp")-273.15;
            int roundedValue=(int)Math.rint(tempResult);
            weatherD.mTemperature=Integer.toString(roundedValue);
            return weatherD;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static String updateWeatherIcon(int condition)
    {
        if(condition>=0 && condition<=300)
        {
            return "thunder1";
        }
        else if(condition>=300 && condition<=500)
        {
            return "lightrain2";
        }
        else if(condition>=500 && condition<=600)
        {
            return "shower";
        }
        else if(condition>=600 && condition<=700)
        {
            return "snow3";
        }
        else if(condition>=701 && condition<=771)
        {
            return "fog";
        }
        else if(condition>=772 && condition<=800)
        {
            return "overcast";
        }
        else if(condition==800)
        {
            return "sunny1";
        }
        else if(condition>=801 && condition<=804)
        {
            return "cloudy";
        }
        else if(condition>=900 && condition<=902)
        {
            return "thunder2";
        }
        if(condition==903)
        {
            return "snow4";
        }
        if(condition==904)
        {
            return "sunny1";
        }
        if(condition>=905 && condition<=1000)
        {
            return "thunder2";
        }
        return "dunno";

    }


    public String getmTemperature() {
        return mTemperature+"°C";
    }

    public String getmCity() {
        return mCity;
    }

    public String getmIcon() {
        return mIcon;
    }

    public String getmWeatherType() {
        return mWeatherType;
    }
}
