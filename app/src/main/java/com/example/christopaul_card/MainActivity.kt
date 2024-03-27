package com.example.christopaul_card

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Déclarer resultLauncher en tant que variable de classe
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isShowingAnswers = findViewById<ImageView>(R.id.toggle123)
        val flashcard_question = findViewById<TextView>(R.id.flashcard_question)
        val flashcard_reponse = findViewById<TextView>(R.id.flashcard_answer)

        // Clic sur la question pour afficher la réponse
        flashcard_question.setOnClickListener {
            flashcard_question.visibility = View.INVISIBLE
            flashcard_reponse.visibility = View.VISIBLE
        }

        // Clic sur la réponse pour afficher la question
        flashcard_reponse.setOnClickListener {
            flashcard_question.visibility = View.VISIBLE
            flashcard_reponse.visibility = View.INVISIBLE
        }

        // Initialiser resultLauncher
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val data: Intent? = result.data
            if (result.resultCode == Activity.RESULT_OK && data != null) {
                val question = data.getStringExtra("question")
                val answer = data.getStringExtra("answer")

                // Mettre à jour les TextView dans MainActivity avec les nouvelles données
                flashcard_question.text = question
                flashcard_reponse.text = answer
            } else {
                Log.i("MainActivity", "Save operation cancelled or no data returned")
            }
        }

        // Lancer MainActivity2 pour obtenir une réponse
        isShowingAnswers.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
        }
    }
}
