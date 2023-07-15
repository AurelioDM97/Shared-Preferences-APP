package com.example.sharedpreferencesapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.sharedpreferencesapp.databinding.ActivityMainBinding
import io.github.muddz.styleabletoast.StyleableToast

const val SHARED_PREF = "userName"
const val SAVE_USER = "userName"
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = binding.button

        loadId()

        button.setOnClickListener {
            val toast = Toast.makeText(this, R.string.hai_cambiato_id_correttamente, Toast.LENGTH_SHORT)
            val toastLayout = layoutInflater.inflate(R.layout.custom_toast, null)
            toast.view = toastLayout
            toast.setGravity(Gravity.BOTTOM, 0, 25)

            saveId()
            toast.show()
        }
    }
    private fun loadId() {
        val textId = binding.id
        val sharedPrefs = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
        val saveId = sharedPrefs.getString(SAVE_USER, null)
        textId.text = saveId
    }
    private fun saveId() {
        val editTextId = binding.editText
        val textId = binding.id
        val inputId = editTextId.text.toString()
        textId.text = inputId

        val sharedPrefs = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

        sharedPrefs.edit()
            .putString(SAVE_USER, inputId)
            .apply()
    }
}