package com.example.year4project

import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class specialistLogin : AppCompatActivity() {
    private val TAG= "LoginActivity"
    //global variable
    private var sEmail:String?= null
    private var sPassword:String?= null

    //UI elements
    private var sForgotPassword: TextView? = null
    private var sEmai: EditText?= null
    private var sPasswor: EditText?=null
    private var sLoginButton: Button?=null

    //FireBase reference
    private var mAuth: FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialist_login)

        val goToSpecialistSignUp= findViewById<TextView>(R.id.SpecsignUp)
        goToSpecialistSignUp.setOnClickListener {
            val intent = Intent(this, specialistSignUp::class.java)
            startActivity(intent)
        }


        initialize()
    }

    private fun initialize() {
        sForgotPassword=findViewById(R.id.specialistForgPasd) as TextView
        sEmai= findViewById(R.id.spuserEmail) as EditText
        sPasswor= findViewById(R.id.spuserPassword) as EditText

        sLoginButton= findViewById(R.id.actionSignInSpecialist) as Button

        mAuth= FirebaseAuth.getInstance()
        sForgotPassword!!.setOnClickListener { startActivity(Intent(this@specialistLogin,forgotPassword::class.java))
        }
        sLoginButton!!.setOnClickListener { loginSpecialist() }
    }

    private fun loginSpecialist() {
        sEmail= sEmai?.text.toString()
        sPassword= sPasswor?.text.toString()

        if (!TextUtils.isEmpty(sEmail)&&!TextUtils.isEmpty(sPassword)){
            Log.d(TAG,"Logging in user" )
            mAuth!!.signInWithEmailAndPassword(sEmail!!, sPassword!!)
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful){
                        Log.d(TAG, "sign in with email success")
                        updateUI()
                    }else{
                        Log.e(TAG, "sign in with email failure")
                        Toast.makeText(this@specialistLogin,"Authenticaion failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {

        val intent = Intent(this@specialistLogin,specialistViewsChats::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}