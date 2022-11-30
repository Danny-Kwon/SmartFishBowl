package com.example.smartfishbowl.api

import com.google.type.DateTime

data class Token(var accessToken: String, var firebaseToken: String)
data class CurrentDevice(var jwtToken: String, var deviceId: Long)
data class Setting(var jwtToken: String, var tmp: String, var hgt: String, var ph: String, var drt: String)
data class FoodSetting(var jwtToken: String, var deviceId: Long, var firstTime: DateTime, var secondTime: DateTime, var thirdTime: DateTime)
data class Getting(var tmp: String, var hgt: String, var ph: String, var drt: String)