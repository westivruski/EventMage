package com.example.eventmage

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.data.model.User
import com.google.firebase.FirebaseError
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class CreateEvent : AppCompatActivity() {



    //Firebase references
    private lateinit var mDatabaseReference: DatabaseReference
    private var mDatabase: FirebaseDatabase? = null
    private lateinit var auth: FirebaseAuth

    private lateinit var etDescription: EditText
    private lateinit var etEventName: EditText
    private lateinit var btnSubmit: Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)


        etDescription = findViewById(R.id.etEventName)
        etEventName = findViewById(R.id.etDescription)
        btnSubmit = findViewById(R.id.btnSubmit)




        btnSubmit.setOnClickListener {
            var en : String = etEventName.text.toString()
            var ed : String = etDescription.text.toString()
            //val str: String = etEventName.text.toString()

           // mDatabase = FirebaseDatabase.getInstance()
//            mDatabaseReference = mDatabase!!.reference.child("Users")
           // val currentUserDb = mDatabaseReference!!.child(userId)


            auth = FirebaseAuth.getInstance()
            val userId = auth.currentUser!!.uid

            val mDatabase = FirebaseDatabase.getInstance().reference.child("Users")



    val pushedKey: DatabaseReference = mDatabase.child(userId).child("Events").push()
    val data =  Users(en,ed)
    pushedKey.setValue(data)





           // currentUserDb.child("Events").child("EventName").setValue(en)
          //  currentUserDb.child("Events").child("EventDescription").setValue(ed)



     /*       val intent = Intent(this, EventFeed::class.java)
            intent.putExtra("message_key", str)
            startActivity(intent)*/


        }


    }


}
