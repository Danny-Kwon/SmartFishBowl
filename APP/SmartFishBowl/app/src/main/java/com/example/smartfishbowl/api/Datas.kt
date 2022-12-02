package com.example.smartfishbowl.api

import java.time.LocalTime

data class Token(var accessToken: String, var firebaseToken: String)
data class CurrentDevice(var jwtToken: String, var deviceId: Long)
data class Setting(var tmp: String, var hgt: String, var ph: String, var drt: String)
data class FoodSetting(var deviceId: Long, var firstTime: LocalTime, var secondTime: LocalTime, var thirdTime: LocalTime)
data class Getting(var tmp: String, var hgt: String, var ph: String, var drt: String)