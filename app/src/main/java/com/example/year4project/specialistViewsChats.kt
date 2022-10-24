package com.example.year4project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class specialistViewsChats : AppCompatActivity() {

    private lateinit var specialistRecyclerView: RecyclerView
    var patientLit= ArrayList<patientChat>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialist_views_chats)

        specialistRecyclerView= findViewById(R.id.specialistSideRecyclerview)

        specialistRecyclerView.layoutManager= LinearLayoutManager(this,LinearLayout.VERTICAL,false)



    }
    fun getChatsPatient(){
        val  firebase : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val databaseReference:DatabaseReference = FirebaseDatabase.getInstance().getReference("Patients")

        databaseReference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                patientLit.clear()

            for (dataSnapShot:DataSnapshot in snapshot.children){
                val patien= dataSnapShot.getValue(patientChat::class.java)
                if (patien!!.patientId.equals(firebase.uid)){
                    patientLit.add(patien)
                }
            }
                val patientUserAdapter= patientSpecAdapter(this@specialistViewsChats,patientLit)

                specialistRecyclerView.adapter=patientUserAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                (Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show())
            }

        })

    }
}