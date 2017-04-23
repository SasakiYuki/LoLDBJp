package application.n.yuki.loldbjp.utils

import application.n.yuki.loldbjp.BuildConfig
import java.util.*

/**
 * Created by yuki.n on 2017/04/18.
 */
class StaticChampionBuildQuery (val champData:String? = null) {
    fun toQueryMap():Map<String,String> {
        return object :HashMap<String,String>() {
            init {
                champData?.let {
                    put("champData",champData)
                }
                put("api_key",BuildConfig.API_KEY)
            }
        }
    }
    companion object {
        fun ChampDataBuilder(image:Boolean = false,info:Boolean = false,partype:Boolean = false,tags:Boolean = false):String? {
            val builder = StringBuilder()
            if(image) builder.append("image,")
            if(info) builder.append("info,")
            if(partype) builder.append("partype,")
            if(tags) builder.append("tags,")
            return String(builder)
        }
    }
}