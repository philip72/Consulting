package com.example.year4project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*
import kotlin.collections.ArrayList

class specialistRecyclerviewViewedByPatient : AppCompatActivity()
{

    private lateinit var dbref: DatabaseReference
    private lateinit var specialistRecyclerView: RecyclerView
    private lateinit var specialistUserArraylist: ArrayList<speconRecy>
    private lateinit var tempArrayList: ArrayList<speconRecy>

    lateinit var profPract : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specialist_recyclerview_viewed_by_patient)

        specialistRecyclerView= findViewById(R.id.specialistUser)


        specialistRecyclerView.layoutManager =LinearLayoutManager(this)
        specialistRecyclerView.setHasFixedSize(true)

        tempArrayList= arrayListOf<speconRecy>()
        specialistUserArraylist= arrayListOf<speconRecy>()

       // specialistUserArraylist= ArrayList()
//        specAdapter= MyAdapter(specialistUserArraylist)
//
//        specialistRecyclerView.adapter=specAdapter
//        specAdapter = MyAdapter(MyAdapter.CellClickListener{  name ->
//            Toast.makeText(applicationContext,"${name.}")
//
//        })

//
////
        getSpecialistUserData()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {



        
        menuInflater.inflate(R.menu.menu_item,menu)
        val item= menu?.findItem(R.id.search_bar)

        val searchView=item?.actionView as SearchView

        searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempArrayList.clear()
                val searchText= newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    specialistUserArraylist.forEach {
                        if (it.specialistProfessionPractised.toString().toLowerCase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }
                    specialistRecyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    tempArrayList.clear()
                    tempArrayList.addAll(specialistUserArraylist)
                    specialistRecyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
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
                val specAdapter= MyAdapter(specialistUserArraylist)
                tempArrayList.addAll(specialistUserArraylist)
                specialistRecyclerView.adapter=specAdapter
//                specialistRecyclerView.adapter=MyAdapter(tempArrayList)
                //tempArrayList.addAll(specialistUserArraylist)
               //specialistRecyclerView.adapter=MyAdapter(specialistUserArraylist)
               // specialistRecyclerView.adapter=MyAdapter(tempArrayList)

                specAdapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
                      val intent= Intent(this@specialistRecyclerviewViewedByPatient,afterPatientClicksSpecialist::class.java)

                        intent.putExtra("specialistFirstname", specialistUserArraylist[position].specialistFirstname)
                        intent.putExtra("specialistProfessionPractised", specialistUserArraylist[position].specialistProfessionPractised)
                        intent.putExtra("specialistYearsOfExperience",specialistUserArraylist[position].specialistYearsOfExperience)
                        intent.putExtra("specialistAge", specialistUserArraylist[position].specialistAge)

                        startActivity(intent)
                    }

                })

                specialistRecyclerView.visibility= View.VISIBLE


            }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

//    override fun onCellClickListener(data: speconRecy) {
//        val speAdapter = MyAdapter(specialistUserArraylist,this)
//        Toast.makeText(this,"Cell clicked",)
//    }

}