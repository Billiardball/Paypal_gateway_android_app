package com.shop.arram.payment


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.shop.arram.R

class OrderCompletedActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_klarna_payment)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, PaymentKlarnaActivity::class.java))
    }

}