package com.example.year4project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class patientSpecAdapter(private val context:Context, private val patientChatList:ArrayList<patientChat>) :
    RecyclerView.Adapter<patientSpecAdapter.ViewHolders>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.patient_chat_list,parent,false)
        return ViewHolders(view)
        }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {

        val patientUser=patientChatList[position]
        holder.patientFirstNa.text=patientUser.patientFirstName
    }

    override fun getItemCount(): Int {
        return patientChatList.size
    }
    class ViewHolders(view: View):RecyclerView.ViewHolder(view){
        val patientFirstNa:TextView= view.findViewById(R.id.patientTextFirstName)
        val textTemp:TextView= view.findViewById(R.id.patientTemp)


    }

}