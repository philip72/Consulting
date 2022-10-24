package com.example.year4project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class patientRegister : AppCompatActivity() {

    //Ui elements
    private var patientFirstName: EditText?= null
    private var patientLastName: EditText?= null
    private var patientEmail: EditText? = null
    private var patientPhoneNo: EditText?= null
    private var patientPassword: EditText? = null
    private var progressBar: ProgressBar?=null

    private var patientRegistration: Button?= null


//    private lateinit var patientFirstName: EditText
//    private lateinit var patientLastName: EditText
//    private  lateinit var patientEmail: EditText
//    private lateinit var patientPassword: EditText
//    private  lateinit var patientPhone: EditText
//    private lateinit var progressBar: ProgressBar
//
//    private lateinit var patientRegistration :Button

    //Firebase reference
    private var mDatabaseReference: DatabaseReference?= null
    private var mDatabase: FirebaseDatabase?=null
    private var mAuth: FirebaseAuth? = null

    private val TAG ="CreateAccountActivity"

    //global variables
    private var patientFirstNam: String? = null
    private var patientLastNam: String?= null
    private var patientEmai: String?= null
    private var patientPhoneN:String?= null
    private var patientPasswor:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_register)
        val goToPatientSignIn= findViewById<TextView>(R.id.patsignIn)
        goToPatientSignIn.setOnClickListener {
            val intent= Intent(this, patientLogin::class.java)
            startActivity(intent)
        }
        initialize()
//        patientFirstName=findViewById(R.id.patientFirstName)
//        patientLastName=findViewById(R.id.patientLastName)
//        patientEmail= findViewById(R.id.patientRegEmail)
//        patientPassword= findViewById(R.id.patientPassword)
//        patientPhone=findViewById(R.id.patientPhoneNo)
//        progressBar = findViewById(R.id.progressbar);
//        progressBar.visibility = View.GONE;
//
//        patientRegistration= findViewById(R.id.regPatientContinue)

        mAuth =FirebaseAuth.getInstance()
    }

    private fun initialize() {
        patientFirstName= findViewById<View>(R.id.patientFirstName) as EditText
        patientLastName= findViewById<View>(R.id.patientLastName) as EditText
        patientEmail= findViewById<View>(R.id.patientRegEmail) as EditText
        patientPassword= findViewById<View>(R.id.patientPassword)as EditText
        patientPhoneNo= findViewById<View>(R.id.patientPhoneNo) as EditText

        patientRegistration= findViewById<View>(R.id.regPatientContinue) as Button
        progressBar= ProgressBar(this)

        mDatabase= FirebaseDatabase.getInstance()
        mDatabaseReference=mDatabase!!.reference!!.child("Patients")
        mAuth= FirebaseAuth.getInstance()

        patientRegistration!!.setOnClickListener { createNewAccount() }

    }

    private fun createNewAccount() {
        patientFirstNam= patientFirstName?.text.toString()
        patientLastNam=patientLastName?.text.toString()
        patientEmai=patientEmail?.text.toString()
        patientPhoneN=patientPhoneNo?.text.toString()
        patientPasswor=patientPassword?.text.toString()

        if (!TextUtils.isEmpty(patientFirstNam)&&!TextUtils.isEmpty(patientLastNam)
            && !TextUtils.isEmpty(patientEmai)&& !TextUtils.isEmpty(patientPasswor)
            && !TextUtils.isEmpty(patientPhoneN)){



        }else if(patientEmai.let { Patterns.EMAIL_ADDRESS.matcher(it).matches()  }) {
            Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Enter all details",Toast.LENGTH_SHORT).show()
        }


        if (patientEmai!!.isEmpty()&& patientLastNam!!.isEmpty()&&patientEmai!!.isEmpty()&&
                patientPhoneN!!.isEmpty()&&patientPasswor!!.isEmpty()){
            Toast.makeText(this, "some fields  are empty  fill all details", Toast.LENGTH_SHORT).show()

        }else if (patientEmai?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() }!!){
            Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show()
        }else if (patientPasswor!!.length<8){
            Toast.makeText(this, "Minimum of 8 characters ", Toast.LENGTH_SHORT).show()
        }else if (patientPasswor!!.matches(".*[A-Z].*".toRegex())) {
            Toast.makeText(this, "password should contain upper case ", Toast.LENGTH_SHORT).show()
        }else if (patientPasswor!!.matches(".*[a-z].*".toRegex())) {
            Toast.makeText(this, "must contain lower case  ", Toast.LENGTH_SHORT).show()

        }else if (patientPasswor!!.matches(".*[@#\$%^&+=].*".toRegex())){
            Toast.makeText(this, "must have special characters  ", Toast.LENGTH_SHORT).show()

        }
        mAuth!!
            .createUserWithEmailAndPassword(patientEmai!!,patientPasswor!!)
            .addOnCompleteListener(this){ task->

                if (task.isSuccessful){
                    Log.d(TAG, "createPatientWithEmail:success")
                    val patientId=mAuth!!.currentUser!!.uid

                    val currentuserDb= mDatabaseReference!!.child(patientId)
                    currentuserDb.child("firstname").setValue(patientFirstNam)
                    currentuserDb.child("lastName").setValue(patientLastNam)
                    currentuserDb.child("phone number").setValue(patientPhoneN)

                    updateUserInfoUI()
                }else{
                    Log.w(TAG, "create:failure", task.exception)
                    Toast.makeText(this@patientRegister, "failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateUserInfoUI() {
        val intent= Intent(this@patientRegister,specialistRecyclerviewViewedByPatient::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
    private fun verifyEmail(){
        val mPatient= mAuth!!.currentUser
        mPatient!!.sendEmailVerification()
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    Toast.makeText(this@patientRegister,"Verification email sent to "+mPatient.email,Toast.LENGTH_SHORT).show()

                }else{
                    Log.e(TAG, "send email verification",task.exception)
                    Toast.makeText(this@patientRegister,"failed to connect to send email verify",Toast.LENGTH_SHORT).show()
                }
            }
    }
//            patientFirstName.error= "FirstName or LastName is empty "    }
//    private fun registerPatient(){
//
//        patientRegistration.setOnClickListener {
//        val patientFirstNam: String = patientFirstName.text.toString().trim()
//        val patientLastNam: String = patientLastName.text.toString().trim()
//        val patientEmai: String = patientEmail.text.toString().trim()
//        val patientPhoneNumbe: String = patientPhone.text.toString().trim()
//        val patientPasswor : String = patientPassword.text.toString().trim()
//
//        if (patientFirstNam.isEmpty()&& patientLastNam.isEmpty()){
//            patientFirstName.error= "FirstName or LastName is empty "
//            return@setOnClickListener
//        }else if (patientEmai.isEmpty()){
//            patientEmail.error= "email is empty "
//            return@setOnClickListener
//        }else if (!Patterns.EMAIL_ADDRESS.matcher(patientEmai).matches()){
//            patientEmail.error="invalid email"
//            return@setOnClickListener
//        }else if (patientPasswor.length<8){
//            patientPassword.error="Minimum of 8 characters"
//            return@setOnClickListener
//        }else if (patientPasswor.matches(".*[A-Z].*".toRegex())){
//            patientPassword.error="Must contain 1 Upper case letter"
//            return@setOnClickListener
//        }else if (patientPasswor.matches(".*[a-z].*".toRegex())) {
//            patientPassword.error = "must have 1 lower case letter"
//            return@setOnClickListener
//        }else if (patientPasswor.matches(".*[@#\$%^&+=].*".toRegex())){
//            patientPassword.error= "Must Contain  1 special case Character"
//            return@setOnClickListener
//        }else if (patientPasswor.isEmpty()){
//            patientPassword.error="password is  empty , required "
//            return@setOnClickListener
//        }else if (patientPhoneNumbe.length!=10){
//            patientPhone.error="must be a valid phone number "
//            return@setOnClickListener
//        }else if (patientPhoneNumbe.isEmpty()){
//            patientPhone.error= "phone is  empty , required "
//            return@setOnClickListener
//        }
//
//        mAuth?.createUserWithEmailAndPassword(patientEmai,patientPasswor)
//            .addOnCompleteListener()
//    }
    //}
}