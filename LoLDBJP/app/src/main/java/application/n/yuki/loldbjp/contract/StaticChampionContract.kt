package application.n.yuki.loldbjp.contract

import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity

/**
 * Created by yuki.n on 2017/04/14.
 */
interface StaticChampionContract {
    interface StaticChampionView {
        fun onSuccessChampion(staticChampionEntity: StaticChampionEntity)
        fun onError(throwable: Throwable)
    }
    fun setView(view:StaticChampionView)
    fun getChampion(id:Int)
}