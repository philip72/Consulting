package com.example.year4project

import android.app.Dialog
import android.content.Intent
import android.media.Rating
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class afterPatientClicksSpecialist : AppCompatActivity() {

    private lateinit var firstSpecName: TextView
    private lateinit var lastSpecName: TextView
    private lateinit var ProfsPec : TextView
    private lateinit var YeofEx: TextView




    private var specialistRating: RatingBar?=null
    private var submitRatingButton: Button?=null

    private var ratingSpec: String?=null

    private var mDatabaseReference: DatabaseReference?= null
    private var mDatabase: FirebaseDatabase?=null
    private var mAuth: FirebaseAuth? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after_patient_clicks_specialist)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title= "Specialist"

        val ratingStars = findViewById<RatingBar>(R.id.rateBar)
        val ratBut= findViewById<Button>(R.id.rateButton)


        mDatabase=FirebaseDatabase.getInstance()
        mAuth=FirebaseAuth.getInstance()

        ratBut?.setOnClickListener{
            val msg = ratingStars.rating.toString()
            Toast.makeText(this@afterPatientClicksSpecialist,
            "rating is : "+msg, Toast.LENGTH_SHORT).show()

            setSpecialistRating()
        }

        val presButton: Button= findViewById(R.id.patientCheckingTheirPrescription)

        presButton.setOnClickListener {
            val bottomSheetDialog= BottomSheetDialog(
                this@afterPatientClicksSpecialist,R.style.BottomSheetDialogTheme
            )

            val bottomSheetView= LayoutInflater.from(applicationContext).inflate(
                R.layout.activity_bottom_sheet,
                findViewById(R.id.btsheet) as LinearLayout?

            )
           bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()

        }
        setValues()

    }

    private fun setSpecialistRating() {

        //val rate = specialistRating?.numStars?.let { Rate(it.toFloat()) }
        val ref = FirebaseDatabase.getInstance().getReference("Specialist")

        val rateId=ref.push().key

//        //val rate= Rate(rateId!!,specialistRating?.numStars!!.toFloat())
//
//        ref.child(rateId).setValue(rate).addOnCompleteListener {
//            Toast.makeText(applicationContext,"Rate is saved",Toast.LENGTH_SHORT).show()
//
//        }


    }


    private  fun setValues(){
        firstSpecName=findViewById(R.id.firstSpecName)
        ProfsPec= findViewById(R.id.ProfsPec)
        YeofEx= findViewById(R.id.YeofEx)
        lastSpecName=findViewById(R.id.lastSpecName)

        firstSpecName.text= intent.getStringExtra("specialistFirstname")
        lastSpecName.text=intent.getStringExtra("specialistLastName")
        ProfsPec.text= intent.getStringExtra("specialistProfessionPractised")
        YeofEx.text= intent.getStringExtra("specialistYearsOfExperience")
    }
}