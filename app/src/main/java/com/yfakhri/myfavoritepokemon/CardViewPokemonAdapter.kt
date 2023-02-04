package com.yfakhri.myfavoritepokemon

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.TextRoundCornerProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class CardViewPokemonAdapter(private val listPokemon: ArrayList<Pokemon>) :
    RecyclerView.Adapter<CardViewPokemonAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_item_detail)
        var pbPower: TextRoundCornerProgressBar = itemView.findViewById(R.id.pb_item_power)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardview_pokemon, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val pokemon = listPokemon[position]

        Glide.with(holder.itemView.context).load(pokemon.photo)
            .apply(RequestOptions().override(350, 550)).into(holder.imgPhoto)

        holder.tvName.text = pokemon.name
        holder.tvDetail.text = pokemon.detail
        holder.pbPower.progress = pokemon.power.toFloat()
        holder.pbPower.progressText = pokemon.power.toString()

        holder.itemView.setOnClickListener {
            val detailIntent = Intent(holder.itemView.context, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.EXTRA_NAME, pokemon.name)
            detailIntent.putExtra(DetailActivity.EXTRA_IMAGE, pokemon.photo)
            detailIntent.putExtra(DetailActivity.EXTRA_HEIGHT, pokemon.height)
            detailIntent.putExtra(DetailActivity.EXTRA_ELEMENT, pokemon.element)
            detailIntent.putExtra(DetailActivity.EXTRA_WEIGHT, pokemon.weight)
            detailIntent.putExtra(DetailActivity.EXTRA_CATEGORY, pokemon.category)
            detailIntent.putExtra(DetailActivity.EXTRA_ABILITY, pokemon.ability)
            detailIntent.putExtra(DetailActivity.EXTRA_ATTACK, pokemon.stats.attack)
            detailIntent.putExtra(DetailActivity.EXTRA_DEFENSE, pokemon.stats.defense)
            detailIntent.putExtra(DetailActivity.EXTRA_SPECIAL, pokemon.stats.special)
            detailIntent.putExtra(DetailActivity.EXTRA_DETAIL, pokemon.detail)

            holder.itemView.context.startActivity(detailIntent)
        }

    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }
}