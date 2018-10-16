package com.example.merve.issues

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Main2Activity : AppCompatActivity() {
    private lateinit var mAuth:FirebaseAuth
    private  lateinit var tcSigOut:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initialisePaging()
    }
    private fun initialisePaging()
    {
       mAuth = FirebaseAuth.getInstance()
       tcSigOut=findViewById(R.id.signOut)
       initialiseEvents()
    }
    private fun initialiseEvents()
    {
        tcSigOut.setOnClickListener {
            val currentUser = mAuth.currentUser
            mAuth.signOut();
            updateUI(currentUser)
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
        } else {
            val intent = Intent(this@Main2Activity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
