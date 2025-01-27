package com.jivahealth.test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var getUsersButton: Button
    private lateinit var usersRecyclerView: RecyclerView
    private lateinit var errorMsg: TextView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        submitButton = findViewById(R.id.submitButton)
        getUsersButton = findViewById(R.id.getUsersButton)
        usersRecyclerView = findViewById(R.id.usersRecyclerView)
        errorMsg = findViewById(R.id.errorMsg)

        // Initialize RecyclerView
        userAdapter = UserAdapter(emptyList())
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = userAdapter

        val userApi = RetrofitClient.getInstance().create(UserApi::class.java)

        // POST Request: Add User
        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()

            if (name.isEmpty() || email.isEmpty()) {
                showError("Please fill in all fields")
                return@setOnClickListener
            }

            val user = User(name, email)
            userApi.createUser(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "User added successfully!", Toast.LENGTH_SHORT).show()
                        nameEditText.text.clear()
                        emailEditText.text.clear()
                    } else {
                        showError("Failed to add user!")
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    showError("Error: ${t.message}")
                }
            })
        }

        // GET Request: Retrieve Users
        getUsersButton.setOnClickListener {
            userApi.getAllUsers().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    if (response.isSuccessful) {
                        val users = response.body()
                        if (users != null && users.isNotEmpty()) {
                            userAdapter.updateUsers(users)
                            errorMsg.visibility = TextView.GONE
                        } else {
                            showError("No users found")
                        }
                    } else {
                        showError("Failed to retrieve users!")
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    showError("Error: ${t.message}")
                }
            })
        }
    }

    private fun showError(message: String) {
        errorMsg.text = message
        errorMsg.visibility = TextView.VISIBLE
    }
}
