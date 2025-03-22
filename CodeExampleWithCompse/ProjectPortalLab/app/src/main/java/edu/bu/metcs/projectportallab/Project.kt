package edu.bu.metcs.projectportallab

data class Project(
    var title: String,
    var description: String,
    var authors: String,
    var link: String,
    var isFavorite: Boolean,
    var keywords: List<String>) {
    companion object {
        val project =
            Project("Weather Forecast",
                "Weather Forcast is an app ...",
                "Liming Zhu",
                "https://github.com/lz9762/METCS683",
                true,
                listOf("#Kotlin", "#Android", "Compose"))
    }
}
