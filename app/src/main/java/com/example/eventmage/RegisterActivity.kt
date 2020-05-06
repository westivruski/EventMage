package com.example.eventmage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
   //Firebase references
    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null



    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

   // private lateinit var btnSignup: Button
    private lateinit var btnRegister: Button

    //private lateinit var tvResetPass: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnRegister = findViewById(R.id.btn_register)
        //tvResetPass = findViewById(R.id.resetPass)


        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        auth = FirebaseAuth.getInstance()

        btnRegister.setOnClickListener {
            var email : String = etEmail.text.toString()
            var password : String = etPassword.text.toString()

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this@RegisterActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
            } else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                    if(task.isSuccessful) {

                        // data to store into the database as child of user

                        //we can change this but we need to add displayName and also add name text field to activity xml and read bookmarked documentation
                        val userId = auth.currentUser!!.uid

                        val currentUserDb = mDatabaseReference!!.child(userId)
                        currentUserDb.child("email").setValue(email)
                        currentUserDb.child("password").setValue(password)

                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
        }

}
