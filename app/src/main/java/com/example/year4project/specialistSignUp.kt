package com.example.year4project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class specialistSignUp : AppCompatActivity() {

    //Ui elements
    private var sFirstName:EditText?=null
    private var sLastName: EditText?=null
    private var sEmail: EditText?= null
    private var sPhNumber: EditText?= null
    private var sPassword: EditText?= null
    private var sProfPract: EditText?= null
    private var sHospID: EditText?=null
    private var sYearOEx: EditText?= null
    private var sAge:EditText?= null
    private var sHospitalPract:EditText?=null

    private var sRegistration: Button?=null

    //Firebase reference
    private var mDatabaseReference: DatabaseReference?= null
    private var mDatabase: FirebaseDatabase?=null
    private var mAuth: FirebaseAuth? = null

    private  val TAG ="createSpecialistAccount"

    //global variables
    private var sFirstNam: String?= null
    private var sLastNam:String?= null
    private var sEmai: String?= null
    private var sPhNumb: String?= null
    private var sPasswor: String?= null
    private var sProfPRac: String?= null
    private var sHosID: String?= null
    private var sYex: String?= null
    private var sAg:String?=null
    private var sHosPr: String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialist_sign_up)

        val goTospecialistLogin= findViewById<TextView>(R.id.specialistregsignIn)
        goTospecialistLogin.setOnClickListener {
            val intent = Intent(this, specialistLogin::class.java)
            startActivity(intent)
        }

        initilaize()

        mAuth=FirebaseAuth.getInstance()
    }

    private fun initilaize() {
        sFirstName= findViewById<View>(R.id.specialistFirstName) as EditText
        sLastName=findViewById<View>(R.id.SpecialistLastName) as EditText
        sEmail=findViewById<View>(R.id.specialistRegEmail) as EditText
        sPhNumber=findViewById<View>(R.id.specialistPhoneNo) as EditText
        sPassword= findViewById<View>(R.id.specialistPassword) as EditText
        sProfPract=findViewById<View>(R.id.ProfessionPractisec) as EditText
        sHospID= findViewById<View>(R.id.HospitalId) as EditText
        sYearOEx=findViewById<View>(R.id.YearsOfExp) as EditText
        sAge=findViewById<View>(R.id.Age) as EditText
        sHospitalPract=findViewById<View>(R.id.hospitalPractising) as EditText

        sRegistration=findViewById<View>(R.id.SpecialistRegister) as Button

        mDatabase= FirebaseDatabase.getInstance()
        mDatabaseReference=mDatabase!!.reference!!.child("Specialist")
        mAuth=FirebaseAuth.getInstance()

        sRegistration!!.setOnClickListener { createNewAccount() }
    }

    private fun createNewAccount() {
        sFirstNam=sFirstName?.text.toString()
        sLastNam=sLastName?.text.toString()
        sEmai=sEmail?.text.toString()
        sPhNumb= sPhNumber?.text.toString()
        sPasswor= sPassword?.text.toString()
        sProfPRac=sProfPract?.text.toString()
        sHosID=sHospID?.text.toString()
        sYex=sYearOEx?.text.toString()
        sAg=sAge?.text.toString()
        sHosPr=sHospitalPract?.text.toString()


        if (!TextUtils.isEmpty(sFirstNam)&&!TextUtils.isEmpty(sLastNam)
            &&!TextUtils.isEmpty(sEmai)&&!TextUtils.isEmpty(sPhNumb)
            &&!TextUtils.isEmpty(sPasswor)&&!TextUtils.isEmpty(sProfPRac)
            &&!TextUtils.isEmpty(sHosID)&&!TextUtils.isEmpty(sYex)
            &&!TextUtils.isEmpty(sAg)&&!TextUtils.isEmpty(sHosPr)){

        }else{
            Toast.makeText(this, "Enter all details", Toast.LENGTH_SHORT).show()

        }
        mAuth!!
            .createUserWithEmailAndPassword(sEmai!!,sPasswor!!)
            .addOnCompleteListener(this){task->
                if (task.isSuccessful){
                    Log.d(TAG, "createSpecialistEmail: success")
                    val  specialistId=mAuth!!.currentUser!!.uid

                    val currentuserDb= mDatabaseReference!!.child(specialistId)
                    currentuserDb.child("specialistFirstname").setValue(sFirstNam)
                    currentuserDb.child("specialistLastName").setValue(sLastNam)
                    currentuserDb.child("specialistPhoneNumber").setValue(sPhNumb)
                    currentuserDb.child("specialistProfessionPractised").setValue(sProfPRac)
                    currentuserDb.child("specialistHospital ID").setValue(sHosID)
                    currentuserDb.child("specialistYearsOfExperience").setValue(sYex)
                    currentuserDb.child("specialistAge").setValue(sAg)
                    currentuserDb.child("specialistHospitalPractisng ").setValue(sHosPr)


                    updateUserInfoUI()
                }else{
                    Log.w(TAG, "create Failure", task.exception)
                    Toast.makeText(this@specialistSignUp, "failed",Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun updateUserInfoUI() {
        val intent =Intent(this@specialistSignUp,specialistViewsChats::class.java )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}