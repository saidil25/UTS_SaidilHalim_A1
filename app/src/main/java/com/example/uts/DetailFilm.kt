package com.example.uts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uts.databinding.DetailFilmBinding

class DetailFilm : AppCompatActivity() {

    private lateinit var binding: DetailFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("Title")
        val desciption = intent.getStringExtra("Description")
        val Director = intent.getStringExtra("Director")
        val image = intent.getIntExtra("Image_Movie",0)
        val genres = intent.getStringArrayListExtra("Genres")


        with(binding){
            titleMovie.text = title
            deskripsiMovie.text = desciption
            Glide.with(this@DetailFilm).load(image).into(imageDetailMovie)
            director.text=Director

            binding.btnPesanSekarang.setOnClickListener {
                val intent = Intent(this@DetailFilm,DetailPemesanan::class.java)
                intent.putExtra("Title",title)
                intent.putExtra("Image",image)
                startActivity(intent)
                finish()
            }

        }

    }



    }

