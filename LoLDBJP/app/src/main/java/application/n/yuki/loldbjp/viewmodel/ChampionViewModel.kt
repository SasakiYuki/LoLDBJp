package application.n.yuki.loldbjp.viewmodel

import android.content.Context
import application.n.yuki.loldbjp.BuildConfig
import application.n.yuki.loldbjp.LoLApplication
import application.n.yuki.loldbjp.contract.ChampionContract
import application.n.yuki.loldbjp.rest.ChampionApiService
import application.n.yuki.loldbjp.rest.entity.ChampionEntity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by yuki.n on 2017/04/14.
 */
class ChampionViewModel(context: Context) : ChampionContract {

    private lateinit var view: ChampionContract.ChampionView
    private val apiKey = BuildConfig.API_KEY
    private val service:ChampionApiService = (context as LoLApplication).getChampionApiService()

    override fun setView(view: ChampionContract.ChampionView) {
        this.view = view
    }

    override fun getFreeChampions() {
        service.getChampion(true, apiKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onSuccess(parseIds(it.champions))
                },{
                    view.onError(it)
                })
    }

    private fun parseIds(entities: ArrayList<ChampionEntity>) = (Observable.from(entities).map(ChampionEntity::id).toList().toBlocking().single() as ArrayList<Int>)

}