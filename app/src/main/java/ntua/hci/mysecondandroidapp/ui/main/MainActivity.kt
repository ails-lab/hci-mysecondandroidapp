package ntua.hci.mysecondandroidapp.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import ntua.hci.mysecondandroidapp.R
import ntua.hci.mysecondandroidapp.ui.list.ListActivity
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        setTitle(R.string.app_name)

        val welcome: TextView = findViewById<TextView>(R.id.txtNames) as TextView
        val welcomeMsg: String = getString(R.string.txtName) + " " + auth.currentUser?.email +"!"
        welcome.text = welcomeMsg

        if (auth.currentUser?.isEmailVerified == false)
            Toast.makeText(baseContext, R.string.verify_email,
                Toast.LENGTH_SHORT).show()

        findViewById<Button>(R.id.btnListview).setOnClickListener {
            val list = Intent(this, ListActivity::class.java)
            startActivity(list)

            this.setResult(Activity.RESULT_OK)
            this.finish()
        }

        findViewById<Button>(R.id.btnTerminate).setOnClickListener {
            auth.signOut()
            this.setResult(Activity.RESULT_OK)
            this.finish()
        }
    }
}