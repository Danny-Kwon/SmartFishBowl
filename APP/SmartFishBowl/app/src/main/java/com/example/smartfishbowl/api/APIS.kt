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
    @POST("firebaseToken") // 파이어베이스 디바이스 토큰값 전송
    @Headers("accept: application/json",
        "content-type: application/json")
    fun sendToken(
        @Body jsonparams: Token
    ): Call<OkSign>

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val BASE_URL = "http://localhost:8080/" // 주소

        fun create(): APIS {
            val gson : Gson = GsonBuilder().setLenient().create()
            val ret :APIS= Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(APIS::class.java)
            Log.d("ret=", ret.toString())
            return ret
        }
    }
}