package application.n.yuki.loldbjp.rest.entity

import java.util.*

/**
 * Created by yuki.n on 2017/04/24.
 */
data class ChampionGGStatEntity (
        val key:String,
        val role:String,
        val name:String,
        val general:General
)

data class General(
        val overallPositionChange:Int,
        val overallPosition:Int,
        val banRate:Float,
        val playPercent:Float,
        val winPercent:Float
)

data class ChampionGGStatData(
        val data:ArrayList<ChampionGGStatEntity>
)



