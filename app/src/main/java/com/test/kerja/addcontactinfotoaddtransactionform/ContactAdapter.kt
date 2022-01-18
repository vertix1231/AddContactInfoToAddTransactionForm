package com.test.kerja.addcontactinfotoaddtransactionform

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.test.kerja.addcontactinfotoaddtransactionform.databinding.ItemUserBinding


class ContactAdapter(): RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {
    private val listUser: ArrayList<Contact> = ArrayList()

    fun setData(items: ArrayList<Contact>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }
    class MyViewHolder(val binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root) {
        var tvName: TextView = itemView.findViewById(R.id.textView_Nama)
        var tvNomor: TextView = itemView.findViewById(R.id.textView_Nomor)
        val rootlayout: ConstraintLayout = itemView.findViewById(R.id.rlayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = listUser[position]
        holder.tvName.text = user.name
        holder.tvNomor.text = user.number
        holder.rootlayout.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailContactActivity::class.java)
            intent.putExtra(DetailContactActivity.IN_USERNAME, user.name)
            intent.putExtra(DetailContactActivity.IN_NUMBER, user.number)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }


}