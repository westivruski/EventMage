package com.example.eventmage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var btnLogout: Button
    private lateinit var btnRegister: Button
    private lateinit var btnActivityFeed: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this, "Already logged in", Toast.LENGTH_LONG).show()
        }
        setContentView(R.layout.activity_main)

        btnLogout = findViewById(R.id.btnLogout)
        btnRegister = findViewById(R.id.btn_createEvent)
        btnActivityFeed = findViewById(R.id.btn_activityFeed)

        btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnRegister.setOnClickListener{
            val intent = Intent(this, CreateEvent::class.java)
            startActivity(intent)
            finish()
        }
        btnActivityFeed.setOnClickListener{
            val intent = Intent(this, EventFeed::class.java)
            startActivity(intent)
            finish()
        }

    }
}
