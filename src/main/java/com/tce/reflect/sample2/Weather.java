package com.tce.reflect.sample2;

@Entity
public class Weather {
    @Resources
    Rain rain;//后面通过反射直接注入

    public void weather_rain(){
        rain.rain();
    }
}
