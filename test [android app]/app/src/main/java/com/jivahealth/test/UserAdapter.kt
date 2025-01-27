// UserAdapter.java
package com.jivahealth.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private var userList: List<User>?) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    fun updateUsers(users: List<User>?) {
        userList = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList!![position]
        holder.idTextView.text = user.id.toString()
        holder.nameTextView.text = user.name
        holder.emailTextView.text = user.email
    }

    override fun getItemCount(): Int {
        return if (userList != null) userList!!.size else 0
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var idTextView: TextView
        var nameTextView: TextView
        var emailTextView: TextView

        init {
            idTextView = itemView.findViewById(R.id.userIdTextView)
            nameTextView = itemView.findViewById<TextView>(R.id.userNameTextView)
            emailTextView = itemView.findViewById<TextView>(R.id.userEmailTextView)
        }
    }
}
