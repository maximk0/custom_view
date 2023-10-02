package com.example.customview

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val  rainbowCircleView: RainbowCircleView by lazy { findViewById(R.id.rainbowDrumView) }
    private val resetButton: Button by lazy { findViewById(R.id.resetButton) }
    private val customText: DrawTextView by lazy { findViewById(R.id.customText) }
    private val tvTitle: TextView by lazy { findViewById(R.id.tvTitle) }
    private val image: ImageView by lazy { findViewById(R.id.image) }
    private lateinit var color: SelectedColor

    private val scope = CoroutineScope(Job() + Dispatchers.Main)
    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetButton.setOnClickListener {
            tvTitle.text = getString(R.string.click_circle)
            image.visibility = View.INVISIBLE
            customText.visibility = View.INVISIBLE
        }

        rainbowCircleView.setOnClickListener {
            rainbowCircleView.spin()
            job = scope.launch {
                tvTitle.text = getString(R.string.selectedColor)
                delay(1100)
                color = rainbowCircleView.getColor()
                tvTitle.text = getString(R.string.title, color.name, color.action)
                when(color.action){
                    Action.TEXT ->{
                        customText.visibility = View.VISIBLE
                        image.visibility = View.INVISIBLE
                        customText.setColor(color)
                        customText.text = color.name
                    }
                    Action.IMAGE -> {
                        image.visibility = View.VISIBLE
                        customText.visibility = View.INVISIBLE
                        Picasso.get().load("https://picsum.photos/200/300").fit().centerCrop().into(image)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
