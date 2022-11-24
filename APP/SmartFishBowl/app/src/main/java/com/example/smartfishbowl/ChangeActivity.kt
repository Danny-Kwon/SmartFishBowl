package com.example.smartfishbowl

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartfishbowl.adapter.BowlAdapter
import com.example.smartfishbowl.database.BowlData
import com.example.smartfishbowl.databinding.ActivityChangeBinding
import com.example.smartfishbowl.databinding.AlertdialogEdittextBinding
import com.example.smartfishbowl.viewmodel.BowlViewModel

class ChangeActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter: BowlAdapter
    lateinit var viewModel: BowlViewModel
    private val binding by lazy {
        ActivityChangeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.addBowl.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
            val edittext = builderItem.editText
            with(builder){
                setTitle("어항 추가")
                setMessage("어항 ID값을 입력하세요.")
                setView(builderItem.root)
                setPositiveButton("확인"){ _: DialogInterface, _: Int ->
                    if(edittext.text!=null) {
                        viewModel.insertBowl(BowlData(edittext.text.toString(), "1"))
                    }
                }
                setNegativeButton("취소"){ _:DialogInterface, _: Int ->
                    Toast.makeText(context, "기기 추가 취소", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }

        binding.deleteBowl.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val builderItem = AlertdialogEdittextBinding.inflate(layoutInflater)
            val edittext = builderItem.editText
            with(builder){
                setTitle("어항 삭제")
                setMessage("삭제할 어항 번호를 입력하세요.")
                setView(builderItem.root)
                setPositiveButton("확인"){ _:DialogInterface, _: Int ->
                    if(edittext.text!=null){
                        Toast.makeText(context, "${edittext.text}어항 삭제됨", Toast.LENGTH_SHORT).show()
                        viewModel.deleteBowl(BowlData(edittext.text.toString(), "1"))
                    }
                }
                setNegativeButton("취소"){ _:DialogInterface, _:Int ->
                    Toast.makeText(context, "기기 삭제 취소", Toast.LENGTH_SHORT).show()
                }
                show()
            }
        }
        binding.rcv.apply {
            layoutManager = GridLayoutManager(this@ChangeActivity, 2)
            recyclerViewAdapter = BowlAdapter()
            adapter = recyclerViewAdapter
        }
        viewModel = ViewModelProvider(this)[BowlViewModel::class.java]
        viewModel.getAllIdsObservers().observe(this) {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        }
        viewModel.getAllIds()
        recyclerViewAdapter.setOnItemClickListener(object : BowlAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: String, pos: Int) {
                Toast.makeText(this@ChangeActivity, data, Toast.LENGTH_SHORT).show()
            }
        })
    }
}