package com.example.smartfishbowl

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelUuid
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.smartfishbowl.DeviceProfile.Companion.CHARACTER_STATE_UUID
import com.example.smartfishbowl.DeviceProfile.Companion.SERVICE_UUID
import com.example.smartfishbowl.DeviceProfile.Companion.SERVICE_UUID2
import com.example.smartfishbowl.databinding.ActivitySearchBluetoothBinding
import java.util.*

@SuppressLint("MissingPermission")
class SearchBluetoothActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivitySearchBluetoothBinding.inflate(layoutInflater)
    }
    private val bluetoothAdapter: BluetoothAdapter? by lazy(LazyThreadSafetyMode.NONE) {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }
    val REQUEST_ENABLE_BT = 100;
    var bluetoothGatt: BluetoothGatt? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.startBluetooth.setOnClickListener {
            bluetoothOnOff()
        }

        binding.scanBluetooth.setOnClickListener {
            StartBleScan()
        }
    }

    fun bluetoothOnOff(){
        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Log.d("bluetoothAdapter","Device doesn't support Bluetooth")
        }else{
            if (bluetoothAdapter?.isEnabled == false) { // 블루투스 꺼져 있으면 블루투스 활성화
                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
            } else{ // 블루투스 켜져있으면 블루투스 비활성화
                bluetoothAdapter?.disable()
            }
        }
    }

    fun StartBleScan(){
        Log.d("Scan", "Start")
        val scanFilter = ScanFilter.Builder().setServiceUuid(SERVICE_UUID).build()
        val scanFilters:MutableList<ScanFilter> = mutableListOf()
        scanFilters.add(scanFilter)

        val scanSettings = ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build()
        Log.d("Scan", "Starts Now")
        bluetoothAdapter?.bluetoothLeScanner?.startScan(scanFilters, scanSettings, bleScanCallback)
    }

    private val bleScanCallback: ScanCallback by lazy {
        object : ScanCallback(){
            override fun onScanResult(callbackType: Int, result: ScanResult?) {
                Log.d("Scan", "onScanResult")

                val bluetoothDevice = result?.device
                if(bluetoothDevice != null){
                    Log.d("Scan", "Device Name: ${bluetoothDevice.name}, Device Adress: ${bluetoothDevice.address}")

                    connectToDevice(bluetoothDevice)
                }
            }
        }
    }

    private fun connectToDevice(device: BluetoothDevice) {
        bluetoothGatt = device.connectGatt(this, false, bleGattCallback)
    }
    private val bleGattCallback : BluetoothGattCallback by lazy {
        object : BluetoothGattCallback(){
            override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
                if(newState == BluetoothProfile.STATE_CONNECTED){
                    bluetoothGatt?.discoverServices()
                }
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
                super.onServicesDiscovered(gatt, status)
                val service = gatt!!.getService(SERVICE_UUID2)
                val characteristic = service.getCharacteristic(CHARACTER_STATE_UUID)
                characteristic.setValue(binding.wifiSsid.toString() + " : " + binding.wifiPassword.toString())
                characteristic.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
                gatt.writeCharacteristic(characteristic)
            }

            override fun onCharacteristicWrite(
                gatt: BluetoothGatt?,
                characteristic: BluetoothGattCharacteristic?,
                status: Int
            ) {
                super.onCharacteristicWrite(gatt, characteristic, status)
                bluetoothGatt?.close()
                bluetoothGatt = null
                Toast.makeText(this@SearchBluetoothActivity, "Sended Wifi Info Successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class DeviceProfile{
    companion object {
        var SERVICE_UUID = ParcelUuid.fromString("")
        var SERVICE_UUID2 = UUID.fromString("")
        var CHARACTER_STATE_UUID = UUID.fromString("")
    }
}