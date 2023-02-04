package com.yfakhri.myfavoritepokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPokemon: RecyclerView
    private var list: ArrayList<Pokemon> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Daftar Pokemon"

        rvPokemon = findViewById(R.id.rv_pokemon)
        rvPokemon.setHasFixedSize(true)

        list.addAll(PokemonData.listData)
        showRecyclerCardView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_about_page -> {
                val aboutIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(aboutIntent)
            }
        }
    }


    private fun showRecyclerCardView() {
        rvPokemon.layoutManager = LinearLayoutManager(this)
        val cardViewPokemonAdapter = CardViewPokemonAdapter(list)
        rvPokemon.adapter = cardViewPokemonAdapter
    }

}