package com.example.smartfishbowl.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIS {
    @POST("login") // 파이어베이스 디바이스 토큰값 전송
    @Headers("accept: application/json",
        "content-type: application/json")
    fun sendToken(
        @Body jsonparams: Token
    ): Call<String>

    @POST("update/deviceid")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun currentDevice(
        @Body jsonparams: CurrentDevice
    ): Call<String>

    @POST("later")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun settingValue(
        @Body jsonparams: Setting
    ): Call<String>

    @POST("later")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun getValues(
        @Body jsonparams: CurrentDevice
    ): Call<Getting>

    //jwtToken, deviceId, Localtime 첫번째 시간, 두번째 시간, 세번째 시간
    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://172.16.236.73:8080/" // 주소

        fun create(): APIS {
            val gson : Gson = GsonBuilder().setLenient().create()
            val ret :APIS= Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIS::class.java)
            Log.d("ret=", ret.toString())
            return ret
        }
    }
}