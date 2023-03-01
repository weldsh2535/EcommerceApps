package com.weldsh2535.ecommerceapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.weldsh2535.ecommerceapp.Model.User
import com.weldsh2535.ecommerceapp.R

class UserAdapter(
    var list:List<User>,
    var onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameText: TextView = itemView.findViewById(R.id.text1)
        private val priceText: TextView = itemView.findViewById(R.id.text2)
        private val descriptionText: TextView = itemView.findViewById(R.id.textView)
        private val createDateText: TextView = itemView.findViewById(R.id.textView2)
        private val updateDateText: TextView = itemView.findViewById(R.id.textView2)

        fun bindItem(user: User) {
            itemView.apply {
                nameText.text = user.firstName
                priceText.text = "${user.lastName} Bath"
                descriptionText.text = user.email
                createDateText.text = user.registeredAt!!.toString()

                if (user.registeredAt != null) {
                    updateDateText.text = user.registeredAt!!.toString()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.activity_cart,parent,false)
        return UserViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = list[position]
        holder.bindItem(item)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(item,position)
        }
        holder.itemView.findViewById<Button>(R.id.btnDelete).setOnClickListener {
            onItemClickListener.onDelete(item, position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onClick(item:User,position: Int)
        fun onDelete(item: User,position: Int)
    }
}


