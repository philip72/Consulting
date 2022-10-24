package com.example.year4project

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(
//    private val cellClickListener: CellClickListener,
    private val specialistUserList: ArrayList<speconRecy>
    )
    :RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
//
    private lateinit var mListener: onItemClickListener
//
    interface  onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener= clickListener
    }
//
//    interface CellClickListener {
//        fun onCellClickListener( data : speconRecy)
//    }

    var onSpecialistClick:((speconRecy)->Unit)?= null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView= LayoutInflater.from(parent.context).inflate(R.layout.specialist_list,
        parent, false)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.specialist_list,parent,false)

        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      val currentItem= specialistUserList[position]

        holder.specFirstName.text= currentItem.specialistFirstname
        holder.profPract.text= currentItem.specialistProfessionPractised
        holder.specAGe.text= currentItem.specialistAge
        holder.yeOXp.text= currentItem.specialistYearsOfExperience
        holder.rate.text= currentItem.rate
//        holder.itemView.setOnClickListener{
//            onSpecialistClick?.invoke(currentItem)
//        }

//        holder.itemView.setOnClickListener {
//            cellClickListener.onCellClickListener(currentItem)
//        }
    }

    override fun getItemCount(): Int {
       return specialistUserList.size

    }
     class MyViewHolder (itemView : View,
                         clickListener: onItemClickListener):
         RecyclerView.ViewHolder(itemView){
        val specFirstName : TextView= itemView.findViewById(R.id.specfirstName)
        val  profPract: TextView= itemView.findViewById(R.id.ProfessionPractisecec)
        val  rate: TextView= itemView.findViewById(R.id.specRateView)
        val yeOXp: TextView= itemView.findViewById(R.id.YeofEx)
        val specAGe: TextView= itemView.findViewById(R.id.specage)

         init {
             itemView.setOnClickListener{
                 clickListener.onItemClick(adapterPosition)
             }
         }

//        init {
//            itemView.setOnClickListener(this)
//        }
//
//        override fun onClick(v: View?) {
//
//            val position: Int= adapterPosition
//            if (position!=RecyclerView.NO_POSITION) {
//                listener.onSpecClck(position)
//            }
//        }
//    }
//    interface OnSpecilaistClicKListner{
//        fun onSpecClck(position: Int)
//    }

}


}