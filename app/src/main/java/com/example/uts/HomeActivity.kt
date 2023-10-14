package com.example.uts

import android.media.tv.TvContract.Programs.Genres
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.databinding.ActivityMain2Binding


class HomeActivity : AppCompatActivity() {

    private lateinit var MovieRecyclerView: RecyclerView
    private lateinit var MovieList: ArrayList<movies>
    lateinit var imageId:Array<Int>
    lateinit var title:Array<String>
    lateinit var description:Array<String>
    lateinit var Director : Array<String>

    private lateinit var Binding:ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(Binding.root)

        imageId= arrayOf(
            R.drawable.film,
            R.drawable.film1,
            R.drawable.film2,
            R.drawable.film4,
            R.drawable.film5,

        )
//

        title=resources.getStringArray(R.array.judul)
        description=resources.getStringArray(R.array.deskripsi)
        Director=resources.getStringArray(R.array.director)


        MovieRecyclerView=findViewById(R.id.movies_recyclerView)

        MovieList= arrayListOf<movies>()
        getUserData()


        with(Binding){

        }
    }
    private fun getUserData(){
        for (i in imageId.indices){

            val movies = movies(imageId[i],title[i],description[i] ,Director[i])
            MovieList.add(movies)
        }

        val adapter=adapter(MovieList)
        MovieRecyclerView.adapter= adapter
    }


}