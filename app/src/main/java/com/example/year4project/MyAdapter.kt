package com.example.year4project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(private val specialistUserList: ArrayList<speconRecy>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView= LayoutInflater.from(parent.context).inflate(R.layout.specialist_list,
        parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val currentItem= specialistUserList[position]

        holder.specFirstName.text= currentItem.specialistFirstname
        holder.profPract.text= currentItem.specialistProfessionPractised
        holder.specAGe.text= currentItem.specialistAge
        holder.yeOXp.text= currentItem.specialistYearsOfExperience
        holder.hosPract.text=currentItem.specialistHospitalPractisng
    }

    override fun getItemCount(): Int {
       return specialistUserList.size
    }
    class MyViewHolder (itemView : View): RecyclerView.ViewHolder(itemView){
        val specFirstName : TextView= itemView.findViewById(R.id.specfirstName)
        val  profPract: TextView= itemView.findViewById(R.id.ProfessionPractisecec)
        val  hosPract: TextView= itemView.findViewById(R.id.hospitalPractisin)
        val yeOXp: TextView= itemView.findViewById(R.id.YeofEx)
        val specAGe: TextView= itemView.findViewById(R.id.specage)
    }
}