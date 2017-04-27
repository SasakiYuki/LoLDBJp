package application.n.yuki.loldbjp.viewmodel.rest

import android.content.Context
import application.n.yuki.loldbjp.BuildConfig
import application.n.yuki.loldbjp.LoLApplication
import application.n.yuki.loldbjp.contract.ChampionBanRateContract
import application.n.yuki.loldbjp.rest.ChampionBanRateApiService
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by yuki.n on 2017/04/25.
 */
class ChampionBanRateViewModel(context: Context) : ChampionBanRateContract {
    private lateinit var view: ChampionBanRateContract.BanRateView
    private val apiKey = BuildConfig.CHAMPION_GG_API_KEY
    private val service: ChampionBanRateApiService = (context as LoLApplication).getChampionBanRateApiService()

    override fun setView(view: ChampionBanRateContract.BanRateView) {
        this.view = view
    }

    override fun getBanRate(role: String) {
        service.getBanRate(role,apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onSuccess(it)
                },{
                    view.onError(it)
                })
    }
}