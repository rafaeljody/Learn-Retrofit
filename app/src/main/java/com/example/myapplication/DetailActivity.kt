package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.imageView
import kotlinx.android.synthetic.main.adapter_main.*
import kotlinx.android.synthetic.main.adapter_main.view.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // data taruh di action bar
        supportActionBar!!.title = intent.getStringExtra("intent_name_first")
        tvDetailNama.text = "Nama : " + " " + intent.getStringExtra("intent_name_title") +" "+ intent.getStringExtra("intent_name_first")+
                intent.getStringExtra("intent_name_last")
        tvDetailInfoGender.text = "Gender : " + intent.getStringExtra("intent_gender")
        tvDetailEmail.text = "Email : " + intent.getStringExtra("intent_email")
        tvDetailNomor.text = "Nomor : " + intent.getStringExtra("intent_phone")


        Glide.with( this )
            .load(intent.getStringExtra("intent_picture"))
            .placeholder(R.drawable.img_placeholder) //memberikan placeholder
            .error(R.drawable.img_placeholder) //gambar pengganti jika eror atau corrupt
            .into(imageView)





    }
}