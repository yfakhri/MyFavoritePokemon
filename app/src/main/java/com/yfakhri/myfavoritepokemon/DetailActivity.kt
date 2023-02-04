package com.yfakhri.myfavoritepokemon

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_HEIGHT = "extra_height"
        const val EXTRA_ELEMENT = "extra_element"
        const val EXTRA_WEIGHT = "extra_weight"
        const val EXTRA_CATEGORY = "extra_category"
        const val EXTRA_ABILITY = "extra_ability"
        const val EXTRA_ATTACK = "extra_attack"
        const val EXTRA_DEFENSE = "extra_defense"
        const val EXTRA_SPECIAL = "extra_special"
        const val EXTRA_DETAIL = "extra_detail"
    }

    private var name: String? = "Pokemon"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        name = intent.getStringExtra(EXTRA_NAME)
        val photo = intent.getIntExtra(EXTRA_IMAGE, 0)
        val height = intent.getStringExtra(EXTRA_HEIGHT)
        val element = intent.getStringExtra(EXTRA_ELEMENT)
        val weight = intent.getStringExtra(EXTRA_WEIGHT)
        val category = intent.getStringExtra(EXTRA_CATEGORY)
        val ability = intent.getStringExtra(EXTRA_ABILITY)
        val attack = intent.getFloatExtra(EXTRA_ATTACK, 0f)
        val defense = intent.getFloatExtra(EXTRA_DEFENSE, 0f)
        val special = intent.getFloatExtra(EXTRA_SPECIAL, 0f)
        val detail = intent.getStringExtra(EXTRA_DETAIL)
        supportActionBar?.title = name

        val imgPhoto: ImageView = findViewById(R.id.img_item_photo)
        val tvName: TextView = findViewById(R.id.tv_item_name)
        val tvHeight: TextView = findViewById(R.id.tv_item_height)
        val tvElement: TextView = findViewById(R.id.tv_item_element)
        val tvWeight: TextView = findViewById(R.id.tv_item_weight)
        val tvCategory: TextView = findViewById(R.id.tv_item_category)
        val tvAbility: TextView = findViewById(R.id.tv_item_ability)
        val radarStats: RadarChart = findViewById(R.id.radar_stats)
        val tvDetail: TextView = findViewById(R.id.tv_item_detail)

        val labels = arrayOf("Attack", "Defense", "Special")
        val dataStats = arrayListOf(
            RadarEntry(attack),
            RadarEntry(defense),
            RadarEntry(special)
        )
        val dataSet = RadarDataSet(dataStats, "Pokemon Stats")
        dataSet.color = Color.RED
        val rData = RadarData(dataSet)

        val xAxis = radarStats.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        val yAxis = radarStats.yAxis
        yAxis.axisMaximum = 8f
        yAxis.axisMinimum = 0f

        radarStats.data = rData
        radarStats.invalidate()

        Glide.with(this).load(photo).apply(RequestOptions().override(350, 550)).into(imgPhoto)
        tvName.text = name
        tvHeight.text = height
        tvElement.text = element
        tvWeight.text = weight
        tvCategory.text = category
        tvAbility.text = ability
        tvDetail.text = detail
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Helo, $name adalah pokemon favoritku")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, "Pokemon Favorit")
                startActivity(shareIntent)
            }
        }
    }
}