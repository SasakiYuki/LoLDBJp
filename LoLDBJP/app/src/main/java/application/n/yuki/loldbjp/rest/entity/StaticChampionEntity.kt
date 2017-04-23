package application.n.yuki.loldbjp.rest.entity

import java.util.*

/**
 * Created by yuki.n on 2017/04/14.
 */
data class StaticChampionEntity (
    val image:Image,
    val info:Info,
    val tags: ArrayList<String>,
    val title:String,
    val id:Int,
    val key:String,
    val name:String,
    val partype:String
)

data class StaticChampionsData (
        val data:Map<String,StaticChampionEntity>
)
data class Image (
        val full:String
)
data class Info (
        val difficulty:Int,
        val attack:Int,
        val defense:Int,
        val magic:Int
)
