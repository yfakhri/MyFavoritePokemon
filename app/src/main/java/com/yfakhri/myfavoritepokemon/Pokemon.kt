package com.yfakhri.myfavoritepokemon

data class Pokemon(
    var name: String = "",
    var detail: String = "",
    var power: Int = 0,
    var photo: Int = 0,
    var stats: PokemonStats = PokemonStats(),
    var height: String = "",
    var element: String = "",
    var weight: String = "",
    var category: String = "",
    var ability: String = ""
)
