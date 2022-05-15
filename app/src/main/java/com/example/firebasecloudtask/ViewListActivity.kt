package com.example.firebasecloudtask

import android.app.ProgressDialog
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.firebasecloudtask.databinding.ActivityMainBinding
import com.example.firebasecloudtask.databinding.ActivityViewListBinding
import com.google.firebase.firestore.FirebaseFirestore

class ViewListActivity : AppCompatActivity() {

    lateinit var binding: ActivityViewListBinding
    lateinit var db: FirebaseFirestore
    lateinit var list :ArrayList<User>
    lateinit var progressBar : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        db = FirebaseFirestore.getInstance()
        list = ArrayList()
        progressBar= ProgressDialog(this)
        progressBar.setMessage("Loading...")
        progressBar.show()
        db.collection("users")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result) {
                        Log.d(ContentValues.TAG, document.id + " => " + document.data)
                        val user : User = document.toObject(User::class.java)
                        list.add(user)
                    }
                    setAdapter()
                } else {
                    Log.w(ContentValues.TAG, "Error getting documents.", task.exception)
                }
            }
    }

    private fun setAdapter() {
        val adapter  = Adapter(list , this)
        binding.rvUsers.adapter = adapter
        progressBar.hide()
    }
}