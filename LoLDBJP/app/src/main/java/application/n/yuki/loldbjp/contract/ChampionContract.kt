package application.n.yuki.loldbjp.contract

import application.n.yuki.loldbjp.rest.entity.ChampionEntity
import java.util.*

/**
 * Created by yuki.n on 2017/04/14.
 */
interface ChampionContract {
    interface ChampionView {
        fun onSuccess(entities: ArrayList<Int>)
        fun onError(throwable: Throwable)
    }

    fun setView(view:ChampionView)
    fun getFreeChampions()
}