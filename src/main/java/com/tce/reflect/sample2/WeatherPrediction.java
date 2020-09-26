package com.tce.reflect.sample2;

public class WeatherPrediction {
    //应该由spring容器完成
    public Object springDo(){
        Weather_reflec weather_reflec = new Weather_reflec();
        Weather weather = new Weather();
        try {
            weather_reflec.getRef(weather);
        }catch (ClassNotFoundException|InstantiationException|IllegalAccessException e){
            e.printStackTrace();
        }
        return weather_reflec.returnList().get(0);
    }

    public static void main(String[] args) {
        WeatherPrediction weatherPrediction = new WeatherPrediction();
        Weather weather=(Weather)weatherPrediction.springDo();
        weather.weather_rain();
    }
}
