package com.example.pruebas

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import kotlinx.android.synthetic.main.activity_img.*

class ImgActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)


        bt_generar.setOnClickListener{
            GenerateRandomImage()
            iv_foto.visibility = View.VISIBLE
        }


        iv_foto.setOnClickListener {
            dispatchTakePictureIntent()
        }

    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, 1234)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == Activity.RESULT_OK){
            val imageBitmap = data?.extras?.get("data") as Bitmap
            iv_foto.setImageBitmap(imageBitmap)
        }

    }

    private fun GenerateRandomImage() {
        val rnd = (1..10).random()
        if (rnd == 1) {
            iv_random.setImageResource(R.drawable.persona1)
        }
        if (rnd == 2) {
            iv_random.setImageResource(R.drawable.persona2)
        }
        if (rnd == 3) {
            iv_random.setImageResource(R.drawable.persona3)
        }
        if (rnd == 4) {
            iv_random.setImageResource(R.drawable.persona4)
        }
        if (rnd == 5) {
            iv_random.setImageResource(R.drawable.persona5)
        }
        if (rnd == 6) {
            iv_random.setImageResource(R.drawable.persona6)
        }
        if (rnd == 7) {
            iv_random.setImageResource(R.drawable.persona7)
        }
        if (rnd == 8) {
            iv_random.setImageResource(R.drawable.persona8)
        }
        if (rnd == 9) {
            iv_random.setImageResource(R.drawable.persona9)
        }
        if (rnd == 10) {
            iv_random.setImageResource(R.drawable.persona10)
        }
    }
}