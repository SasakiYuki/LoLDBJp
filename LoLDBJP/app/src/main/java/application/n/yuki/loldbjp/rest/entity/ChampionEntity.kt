package application.n.yuki.loldbjp.rest.entity

import java.util.*

/**
 * Created by yuki.n on 2017/04/14.
 */
data class ChampionEntity (
    val id:Int,
    val freeToPlay:Boolean
)

data class ChampionsEntity(
        val champions: ArrayList<ChampionEntity>
)