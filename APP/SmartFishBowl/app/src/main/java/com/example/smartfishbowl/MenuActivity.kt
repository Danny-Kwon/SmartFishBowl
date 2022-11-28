package com.example.smartfishbowl

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.smartfishbowl.databinding.AlertdialogEdittextBinding
import com.example.smartfishbowl.viewmodel.BowlViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout
    lateinit var viewModel: BowlViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.menu)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        drawerLayout = findViewById(R.id.activity_drawer)
        navigationView = findViewById(R.id.main_navigationView)
        navigationView.setNavigationItemSelectedListener(this)
        //bowl.text = api. XXX or prefs.setString
        change_tmp.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
            val edittext = builderItem.editText
            with(builder){
                setTitle("적정 온도 변경")
                setMessage("적정 온도를 입력하세요.")
                setView(builderItem.root)
                setPositiveButton("확인"){ _: DialogInterface, _: Int ->
                    if(edittext.text!=null) {
                        tmp.text = edittext.text
                    }
                }
                setNegativeButton("취소"){ _: DialogInterface, _: Int ->
                    Toast.makeText(context, "적정 온도 변경 취소", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
        change_hgt.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
            val edittext = builderItem.editText
            with(builder){
                setTitle("적정 수위 변경")
                setMessage("적정 수위를 입력하세요.")
                setView(builderItem.root)
                setPositiveButton("확인"){ _: DialogInterface, _: Int ->
                    if(edittext.text!=null) {
                        hgt.text = edittext.text
                    }
                }
                setNegativeButton("취소"){ _: DialogInterface, _: Int ->
                    Toast.makeText(context, "적정 수위 변경 취소", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
        change_ph.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
            val edittext = builderItem.editText
            with(builder){
                setTitle("적정 PH 변경")
                setMessage("적정 PH를 입력하세요.")
                setView(builderItem.root)
                setPositiveButton("확인"){ _: DialogInterface, _: Int ->
                    if(edittext.text!=null) {
                        ph.text = edittext.text
                    }
                }
                setNegativeButton("취소"){ _: DialogInterface, _: Int ->
                    Toast.makeText(context, "적정 PH 변경 취소", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
        change_drt.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
            val edittext = builderItem.editText
            with(builder){
                setTitle("적정 탁도 변경")
                setMessage("적정 탁도를 입력하세요.")
                setView(builderItem.root)
                setPositiveButton("확인"){ _: DialogInterface, _: Int ->
                    if(edittext.text!=null) {
                        drt.text = edittext.text
                    }
                }
                setNegativeButton("취소"){ _: DialogInterface, _: Int ->
                    Toast.makeText(context, "적정 탁도 변경 취소", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
        change_fd.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
            val edittext = builderItem.editText
            with(builder){
                setTitle("먹이 공급 주기 변경")
                setMessage("먹이 공급 주기를 입력하세요.")
                setView(builderItem.root)
                setPositiveButton("확인"){ _: DialogInterface, _: Int ->
                    if(edittext.text!=null) {
                        fd.text = edittext.text
                    }
                }
                setNegativeButton("취소"){ _: DialogInterface, _: Int ->
                    Toast.makeText(context, "먹이 공급 주기 변경 취소", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                activity_drawer.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> {
                val intent = Intent(this, ChangeActivity::class.java)
                startActivity(intent)
            }
            R.id.item2 -> {
                val intent = Intent(this, SearchBluetoothActivity::class.java)
                startActivity(intent)
            }
            R.id.item3 -> {

            }
            R.id.item4 -> {

            }
        }
        return false
    }

    override fun onBackPressed() { //뒤로가기 처리
        if(activity_drawer.isDrawerOpen(GravityCompat.START)){
            activity_drawer.closeDrawers()
        } else{
            super.onBackPressed()
        }
    }
}
