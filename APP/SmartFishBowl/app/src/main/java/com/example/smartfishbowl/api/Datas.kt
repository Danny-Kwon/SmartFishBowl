package com.example.smartfishbowl.api

data class Token(var firebaseToken: String)
data class Jwt(var token: String)
data class BluetoothDatas(var name: String, var address: String)