package com.example.year4project

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class patientLogin : AppCompatActivity() {

    private val TAG= "LoginActivity"

    //global variable
    private var patientEmail: String?= null
    private var patientPassowrd: String?= null


    //UI elements
    private var patientForgotPassword: TextView? = null
    private var patientEmai:EditText?= null
    private var patientPasswor:EditText?=null
    private var patientLoginButton: Button?=null
    private var progressBar:ProgressBar?=null


    //FireBase reference
    private var mAuth: FirebaseAuth?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_login)

        val goToPatientSignUp = findViewById<TextView>(R.id.patientsignUp)
        goToPatientSignUp.setOnClickListener {
            val intent =Intent(this,patientRegister::class.java)
            startActivity(intent)
        }
        initialize()

    }

    private fun initialize() {
        patientForgotPassword=findViewById(R.id.forgotPasswd) as TextView
        patientEmai= findViewById(R.id.userEmail) as EditText
        patientPasswor= findViewById(R.id.userPassword) as EditText

        patientLoginButton= findViewById(R.id.actionSignInPatient) as Button

        progressBar= ProgressBar(this)

        mAuth= FirebaseAuth.getInstance()
        patientForgotPassword!!.setOnClickListener { startActivity(Intent(this@patientLogin,forgotPassword::class.java)) }

        patientLoginButton!!.setOnClickListener { loginPatient() }

    }

    private fun loginPatient() {
        patientEmail= patientEmai?.text.toString()
        patientPassowrd= patientPasswor?.text.toString()

        if (!TextUtils.isEmpty(patientEmail)&& !TextUtils.isEmpty(patientPassowrd)){


            Log.d(TAG, "Logging in user")

            mAuth!!.signInWithEmailAndPassword(patientEmail!!,patientPassowrd!!)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Log.d(TAG,"sign in with email success")
                        updateUI()
                    }else{
                        Log.e(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(this@patientLogin,"Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI() {
        val intent = Intent(this@patientLogin, specialistRecyclerviewViewedByPatient:: class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

}