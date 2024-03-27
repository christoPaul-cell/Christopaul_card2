package com.example.christopaul_card

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.christopaul_card.R
class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val questionEditText = findViewById<EditText>(R.id.editTextField1)
        val answerEditText = findViewById<EditText>(R.id.editTextField1)
        val cancelButton = findViewById<ImageView>(R.id.icone_X)
        val saveButton = findViewById<ImageView>(R.id.icone_save)

        // Définir le clic sur le bouton d'annulation (icone_X)
        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        // Définir le clic sur le bouton de sauvegarde (icone_save)
        saveButton.setOnClickListener {
            val question = questionEditText.text.toString()
            val answer = answerEditText.text.toString()

            // Créer un Intent avec les données à renvoyer
            val resultIntent = Intent()
            resultIntent.putExtra("question", question)
            resultIntent.putExtra("answer", answer)

            // Définir le résultat en utilisant setResult et terminer l'activité
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
