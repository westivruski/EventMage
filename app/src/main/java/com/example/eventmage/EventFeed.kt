package com.example.eventmage

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class EventFeed : AppCompatActivity() {
    internal var users: Users? = null  // declare user object outside onCreate Method

    private  var mDatabaseReference: DatabaseReference? = null
    // private var mDatabase: FirebaseDatabase? = null
    private lateinit var auth: FirebaseAuth

    private lateinit var receivedValueId: TextView

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_feed)
      //  linearLayoutManager = LinearLayoutManager(this)
       // recyclerView.layoutManager = linearLayoutManager
       // receivedValueId = findViewById(R.id.list)

        val textView1: RecyclerView = findViewById(R.id.list)
        textView1.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

// Get a reference to our posts

        val uid = FirebaseAuth.getInstance().currentUser!!.uid

        val rootRef = FirebaseDatabase.getInstance().reference
        //val uidRef = rootRef.child("Users")

        val queryRef: Query = rootRef.child("Users").limitToLast(100)

        queryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val products = ArrayList<Users>()
                for (productSnapshot in dataSnapshot.children) {
                   // val produc = productSnapshot.getValue(Users::class.java)
                    for (productSnapshot1 in productSnapshot.child("Events").children) {
                    val product = productSnapshot1.getValue(Users::class.java)
                    if (product != null) {
                        products.add(product)
                    }
                    val adapter = CustomAdapter(products)
                    textView1.adapter = adapter
                }
            }
               //* val td: List<String>? = dataSnapshot.child("email").value as ArrayList<String>?

                // val name = dataSnapshot.child("email").getValue(String::class.java)
               // textView1.text = td.toString()
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }

        })
       // uidRef.addValueEventListener(valueEventListener)


    }
}


