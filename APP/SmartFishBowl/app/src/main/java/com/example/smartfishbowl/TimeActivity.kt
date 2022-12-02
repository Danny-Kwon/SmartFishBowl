package com.example.smartfishbowl

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.smartfishbowl.api.APIS
import com.example.smartfishbowl.api.FoodSetting
import com.example.smartfishbowl.databinding.ActivityTimeBinding
import com.example.smartfishbowl.sharedpreferences.PreferencesUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime

class TimeActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityTimeBinding.inflate(layoutInflater)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.first.setOnClickListener {
            showTimeSetting1()
        }
        binding.second.setOnClickListener {
            showTimeSetting2()
        }
        binding.third.setOnClickListener {
            showTimeSetting3()
        }
    }
    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showTimeSetting1(){
        val prefs = PreferencesUtil(this)
        var set: Int
        var set2: Int
        val build = AlertDialog.Builder(this).create()
        val edialog : LayoutInflater = LayoutInflater.from(this)
        val mView : View = edialog.inflate(R.layout.timepick, null)
        val hour: NumberPicker = mView.findViewById(R.id.numberPicker_hour)
        val min : NumberPicker = mView.findViewById(R.id.numberPicker_min)
        val confirm : Button = mView.findViewById(R.id.btn_settime_ok)
        val cancel : Button = mView.findViewById(R.id.btn_settime_no)
        with(hour){
            minValue = 0
            maxValue = 23
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setOnValueChangedListener{ _, _, i2 ->
                set = i2
                prefs.setInt("FirstHour", set)
            }
        }
        with(min){
            minValue = 0
            maxValue = 59
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setOnValueChangedListener{ _, _, i2 ->
                set2 = i2
                prefs.setInt("FirstMinute", set2)
            }
        }
        confirm.setOnClickListener{
            Toast.makeText(this, "먹이 시간이 ${prefs.getInt("FirstHour", 0)}시 ${prefs.getInt("FirstMinute", 0)}분 으로 설정 되었습니다.", Toast.LENGTH_SHORT).show()
            build.dismiss()
        }
        cancel.setOnClickListener{
            Toast.makeText(this, "먹이 시간 설정하지 않음", Toast.LENGTH_SHORT).show()
            build.dismiss()
        }
        with(build){
            setView(mView)
            create()
            show()
        }
    }
    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showTimeSetting2(){
        val prefs = PreferencesUtil(this)
        var set: Int
        var set2: Int
        val build = AlertDialog.Builder(this).create()
        val edialog : LayoutInflater = LayoutInflater.from(this)
        val mView : View = edialog.inflate(R.layout.timepick, null)
        val hour: NumberPicker = mView.findViewById(R.id.numberPicker_hour)
        val min : NumberPicker = mView.findViewById(R.id.numberPicker_min)
        val confirm : Button = mView.findViewById(R.id.btn_settime_ok)
        val cancel : Button = mView.findViewById(R.id.btn_settime_no)
        with(hour){
            minValue = 0
            maxValue = 23
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setOnValueChangedListener{ _, _, i2 ->
                set = i2
                prefs.setInt("SecondHour", set)
            }
        }
        with(min){
            minValue = 0
            maxValue = 59
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setOnValueChangedListener{ _, _, i2 ->
                set2 = i2
                prefs.setInt("SecondMinute", set2)
            }
        }
        confirm.setOnClickListener{
            Toast.makeText(this, "먹이 시간이 ${prefs.getInt("SecondHour", 0)}시 ${prefs.getInt("SecondMinute", 0)}분 으로 설정 되었습니다.", Toast.LENGTH_SHORT).show()
            build.dismiss()
        }
        cancel.setOnClickListener{
            Toast.makeText(this, "먹이 시간 설정하지 않음", Toast.LENGTH_SHORT).show()
            build.dismiss()
        }
        with(build){
            setView(mView)
            create()
            show()
        }
    }
    @SuppressLint("InflateParams")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showTimeSetting3(){
        val prefs = PreferencesUtil(this)
        var set: Int
        var set2: Int
        val build = AlertDialog.Builder(this).create()
        val edialog : LayoutInflater = LayoutInflater.from(this)
        val mView : View = edialog.inflate(R.layout.timepick, null)
        val hour: NumberPicker = mView.findViewById(R.id.numberPicker_hour)
        val min : NumberPicker = mView.findViewById(R.id.numberPicker_min)
        val confirm : Button = mView.findViewById(R.id.btn_settime_ok)
        val cancel : Button = mView.findViewById(R.id.btn_settime_no)
        with(hour){
            minValue = 0
            maxValue = 23
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setOnValueChangedListener{ _, _, i2 ->
                set = i2
                prefs.setInt("ThirdHour", set)
            }
        }
        with(min){
            minValue = 0
            maxValue = 59
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setOnValueChangedListener{ _, _, i2 ->
                set2 = i2
                prefs.setInt("ThirdMinute", set2)
            }
        }
        confirm.setOnClickListener{
            Toast.makeText(this, "먹이 시간이 ${prefs.getInt("ThirdHour", 0)}시 ${prefs.getInt("ThirdMinute", 0)}분 으로 설정 되었습니다.", Toast.LENGTH_SHORT).show()
            build.dismiss()
        }
        cancel.setOnClickListener{
            Toast.makeText(this, "먹이 시간 설정하지 않음", Toast.LENGTH_SHORT).show()
            build.dismiss()
        }
        with(build){
            setView(mView)
            create()
            show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBackPressed() {
        super.onBackPressed()
        val apis = APIS.create()
        val pref = PreferencesUtil(this)
        val firstTime: LocalTime = LocalTime.of(pref.getInt("FirstHour", 0), pref.getInt("FirstMinute", 0))
        val secondTime: LocalTime = LocalTime.of(pref.getInt("SecondHour", 0), pref.getInt("SecondMinute", 0))
        val thirdTime: LocalTime = LocalTime.of(pref.getInt("SecondHour", 0), pref.getInt("ThirdMinute", 0))
        val foodSetting = FoodSetting(pref.getString("CurrentDevice", "_").toLong(), firstTime, secondTime, thirdTime)
            apis.setTime("Bearer " + pref.getString("JWT", "error"), foodSetting).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("Response", response.body().toString())
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("TimeResponse", t.message.toString())
                }
            })
    }
}