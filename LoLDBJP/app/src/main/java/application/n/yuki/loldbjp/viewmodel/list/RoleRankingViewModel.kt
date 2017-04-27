package application.n.yuki.loldbjp.viewmodel.list

/**
 * Created by yuki.n on 2017/04/24.
 */
data class RoleRankingViewModel(
        private val ranking:Int,
        private val imageUrl:String,
        private val name:String,
        private val key:String,
        private val mainPercent:Float,
        private val subPercent:Float,
        private val subPlaceholder:String
) {

    fun ranking() = ranking.toString()
    fun imageUrl() = "http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + imageUrl
    fun name() = name
    fun key() = key
    fun mainPercent() = mainPercent.toString()+"%"
    fun subPercent() = subPercent.toString()+"%"
    fun subPlaceholder() = subPlaceholder
}