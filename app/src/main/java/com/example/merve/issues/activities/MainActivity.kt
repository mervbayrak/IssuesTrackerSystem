package com.example.merve.issues.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.merve.issues.R

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private var edtEmail: EditText? = null
    private var edtPassword: EditText? = null
    private var edtPasswordrepeat: EditText? = null
    private var btnSignin: Button? = null
    private var btnCreateaccount: Button? = null
    private var mAuth: FirebaseAuth? = null
    private var strEmail: String? = null
    private var strPassword: String? = null
    private var strPasswordrepeat: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialisePaging()
    }

    private fun initialisePaging() {
        mAuth = FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.email)
        edtPassword = findViewById(R.id.password)
        edtPasswordrepeat = findViewById(R.id.passwordrepeat)
        btnSignin = findViewById(R.id.signInButton)
        btnCreateaccount = findViewById(R.id.createAccountButton)
        initialiseEvents()
    }

    private fun initialiseEvents() {
        signInFunction()
        createAccounFunction()
    }

    private fun signInFunction() {
        btnSignin!!.setOnClickListener(View.OnClickListener {
            validateForm()
            if (!validateForm()) {
                return@OnClickListener
            }
            mAuth!!.signInWithEmailAndPassword(strEmail!!, strPassword!!).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = mAuth!!.currentUser
                    updateUI(user)
                    finish()
                    val intent = Intent(this@MainActivity, Main2Activity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("signIn", "signInWithEmail:failure", task.exception)
                    Toast.makeText(applicationContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
        })
    }

    private fun createAccounFunction() {
        btnCreateaccount!!.setOnClickListener(View.OnClickListener {
            validateForm()
            edtPasswordrepeat!!.visibility = View.VISIBLE
            if (!validateForm()) {
                return@OnClickListener
            }
            mAuth!!.createUserWithEmailAndPassword(strEmail!!, strPassword!!)
                    .addOnCompleteListener(this@MainActivity) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth!!.currentUser
                            updateUI(user)
                            finish()
                            val intent = Intent(this@MainActivity, Main2Activity::class.java)
                            startActivity(intent)
                        } else {
                            Log.w("createUser", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(applicationContext, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }
        })

    }

    private fun validateForm(): Boolean {
        strEmail = edtEmail!!.text.toString().trim { it <= ' ' }
        strPassword = edtPassword!!.text.toString().trim { it <= ' ' }
        strPasswordrepeat = edtPasswordrepeat!!.text.toString().trim { it <= ' ' }
        var valid = true
        if (TextUtils.isEmpty(strEmail)) {
            edtEmail!!.error = "Required."
            valid = false
        } else {
            edtEmail!!.error = null
        }

        if (TextUtils.isEmpty(strPassword)) {
            edtPassword!!.error = "Required."
            valid = false
        } else {
            edtPassword!!.error = null
        }
        if (edtPasswordrepeat!!.visibility == View.VISIBLE) {
            if (TextUtils.isEmpty(strPasswordrepeat)) {
                edtPasswordrepeat!!.error = "Required."
                valid = false
            } else {
                edtPasswordrepeat!!.error = null
            }
        }
        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(applicationContext, "" + "User don't", Toast.LENGTH_LONG).show()
        }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.currentUser
        updateUI(currentUser)
    }
}
