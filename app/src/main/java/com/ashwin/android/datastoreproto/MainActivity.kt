package com.ashwin.android.datastoreproto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import java.util.*

private const val CXT = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    private val userNameList = listOf("abcd", "efgh", "ijkl", "mnop", "pqrs", "tuvw", "wxyz")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.user.observe(this, {
            println("${Constant.TAG}: $CXT user.onChange: User{id=${it.id}, name=${it.name}, age=${it.age}}")
        })

        val changeButton = findViewById<Button>(R.id.change_button)
        changeButton.setOnClickListener {
            // Update user name : will keep rest of the fields same or default.
            val name = userNameList[Random().nextInt(userNameList.size)]
            mainViewModel.updateUserName(name)

            // Update user
            val userId = Random().nextLong()
            val userName = userNameList[Random().nextInt(userNameList.size)]
            val userAge = Random().nextInt(100)
            mainViewModel.updateUser(userId, userName, userAge)
        }
    }
}
