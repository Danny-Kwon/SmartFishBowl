package com.example.smartfishbowl.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface tokenAPI {

    @GET("oauth2/authorization/google/")
    fun getJwtToken(): Call<Jwt>


    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://jungwoo.bowl.p-e.kr:8080/" // 주소

        fun create(): tokenAPI {
            val gson : Gson = GsonBuilder().setLenient().create()
            val ret :tokenAPI= Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(tokenAPI::class.java)
            Log.d("ret=", ret.toString())
            return ret
        }
    }
}