package edu.bu.metcs.projectportallab

data class Project(
    var title: String,
    var description: String,
    var authors: List<String>,
    var link: List<String>,
    var isFavorite: Boolean,
    var keywords: List<String>) {
    companion object {
        val project =
            Project("Weather Forecast",
                "Weather Forcast is an app ...\n\n\n\n",
                listOf("John Dow", "Kelly White"),
                listOf("https://github.com/lz9762/METCS683","\n"),
                false,
                listOf("#Kotlin", "#Android", "#Compose"))
    }
}
