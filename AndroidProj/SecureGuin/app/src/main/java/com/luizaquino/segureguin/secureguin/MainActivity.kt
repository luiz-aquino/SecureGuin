package com.luizaquino.segureguin.secureguin

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.luizaquino.segureguin.secureguin.model.myDocument


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val extras = this.intent.extras
        if (extras !=  null) {
            FirebaseApp.initializeApp(this@MainActivity)

            val db = FirebaseFirestore.getInstance()

            val imgView = this.findViewById<ImageView>(R.id.imgView)
            val id = extras.getString("documentId")
            db.collection("rings").document(id).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val lobj = task.result.toObject(myDocument::class.java)
                    Toast.makeText(this, "Mensagem ID: " + lobj!!.image!!.toBytes().size, Toast.LENGTH_LONG).show()

                    Log.d("info", lobj!!.filename)
                    val bytes = lobj!!.image!!.toBytes()
                    val img = BitmapFactory.decodeByteArray(bytes, 0 , bytes.size)
                    imgView.setImageBitmap(img)
                }
            }
        }
    }
}
