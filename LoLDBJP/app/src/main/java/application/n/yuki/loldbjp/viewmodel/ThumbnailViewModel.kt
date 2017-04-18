package application.n.yuki.loldbjp.viewmodel

/**
 * Created by yuki.n on 2017/04/16.
 */
data class ThumbnailViewModel(
        private val name: String,
        private val imageUrl: String
) {

    fun name() = name
    fun imageUrl() = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + imageUrl
}