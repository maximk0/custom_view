package com.example.customview

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var rainbowCircleView: RainbowCircleView
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rainbowCircleView = findViewById(R.id.rainbowDrumView)
        resetButton = findViewById(R.id.resetButton)

        resetButton.setOnClickListener {
            // Добавьте код для очистки экрана от текста и картинок.
        }

        rainbowCircleView.setOnClickListener {
            rainbowCircleView.spin()
        }
    }
}
