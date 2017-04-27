package application.n.yuki.loldbjp.viewmodel.rest

import android.content.Context
import application.n.yuki.loldbjp.LoLApplication
import application.n.yuki.loldbjp.contract.StaticChampionContract
import application.n.yuki.loldbjp.rest.StaticChampionApiService
import application.n.yuki.loldbjp.utils.StaticChampionBuildQuery
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by yuki.n on 2017/04/14.
 */
class StaticChampionViewModel(context: Context) : StaticChampionContract {
    private lateinit var view: StaticChampionContract.StaticChampionView
    private val service: StaticChampionApiService = (context as LoLApplication).getStaticChampionApiService()

    override fun setView(view: StaticChampionContract.StaticChampionView) {
        this.view = view
    }

    override fun getChampion(id: Int) {
        service.getChampion(id,buildQuery())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onSuccessChampion(it)
                },{
                    view.onError(it)
                })
    }
    override fun getChampions() {
        service.getChampions(buildQueryImageInfoPartypeTags())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onSuccessChampions(it)
                },{
                    view.onError(it)
                })
    }


    private fun buildQuery() = StaticChampionBuildQuery(StaticChampionBuildQuery.Companion.ChampDataBuilder(true)).toQueryMap()
    private fun buildQueryImageInfoPartypeTags() = StaticChampionBuildQuery(StaticChampionBuildQuery.Companion.ChampDataBuilder(true,true,true,true)).toQueryMap()
}