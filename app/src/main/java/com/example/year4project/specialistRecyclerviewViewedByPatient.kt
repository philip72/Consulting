package com.example.year4project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class specialistRecyclerviewViewedByPatient : AppCompatActivity() {

    private lateinit var dbref: DatabaseReference
    private lateinit var specialistRecyclerView: RecyclerView
    private lateinit var specialistUserArraylist: ArrayList<speconRecy>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialist_recyclerview_viewed_by_patient)


        specialistRecyclerView= findViewById(R.id.specialistUser)
        specialistRecyclerView.layoutManager =LinearLayoutManager(this)
        specialistRecyclerView.setHasFixedSize(true)

        specialistUserArraylist= arrayListOf<speconRecy>()

        getSpecialistUserData()
    }

    private fun getSpecialistUserData() {
        dbref= FirebaseDatabase.getInstance().getReference("Specialist")

        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

            if (snapshot.exists()){
                for (snapUserShot in snapshot.children){
                    val specialist= snapUserShot.getValue(speconRecy::class.java)
                    specialistUserArraylist.add(specialist!!)


                }
                specialistRecyclerView.adapter=MyAdapter(specialistUserArraylist)
            }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}