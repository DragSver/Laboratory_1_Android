package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lab1.databinding.TitleActivityBinding

class TitleActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var binding: TitleActivityBinding

    companion object {
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TitleActivityBinding.inflate(layoutInflater)
        val id = intent?.getStringExtra(ID)
        binding.buttonId.text = id.toString()
        binding.topAppBar.setOnClickListener(this)


        binding.switchColor.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.colorText.setBackgroundColor(resources.getColor(R.color.isSwitch, null))
            }else{
                binding.colorText.setBackgroundColor(resources.getColor(R.color.notSwitch, null))
            }
        }

        setContentView(binding.root)
    }

    override fun onClick(v: View) {
        val intent = Intent(this@TitleActivity, ProductActivity::class.java)
        startActivity(intent)
    }
}