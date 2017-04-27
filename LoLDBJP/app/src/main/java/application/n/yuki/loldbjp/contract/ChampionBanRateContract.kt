package application.n.yuki.loldbjp.contract

import application.n.yuki.loldbjp.rest.entity.ChampionGGStatData
import java.util.*

/**
 * Created by yuki.n on 2017/04/25.
 */
interface ChampionBanRateContract {
    interface BanRateView {
        fun onSuccess(entities: ChampionGGStatData)
        fun onError(throwable: Throwable)
    }

    fun setView(view: BanRateView)
    fun getBanRate(role:String)
}