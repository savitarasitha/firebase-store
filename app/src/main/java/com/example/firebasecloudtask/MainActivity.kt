package com.example.firebasecloudtask

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import com.example.firebasecloudtask.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import androidx.annotation.NonNull

import com.google.android.gms.tasks.OnFailureListener

import com.google.firebase.firestore.DocumentReference

import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.QueryDocumentSnapshot

import com.google.firebase.firestore.QuerySnapshot

import com.google.android.gms.tasks.OnCompleteListener


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var db : FirebaseFirestore
    lateinit var progressBar : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        db = FirebaseFirestore.getInstance()

        binding.btnView.setOnClickListener {
            startActivity(Intent(this , ViewListActivity::class.java))
        }

        binding.btnSave.setOnClickListener {
            val firstName: String = binding.etFirstName.text.toString()
            val lastName: String = binding.etLastName.text.toString()

            if (firstName.equals("")) {
                Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show()
            } else if (lastName.equals("")) {
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show()
            } else {

                val user: User = User(firstName, lastName)
                progressBar= ProgressDialog(this)
                progressBar.setMessage("Loading...")
                progressBar.show()
                db.collection("users")
                    .add(user)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.id)
                        progressBar.hide()
                        Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show()
                        binding.etFirstName.setText("")
                        binding.etLastName.setText("")
                    }
                    .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
            }
        }

    }



}