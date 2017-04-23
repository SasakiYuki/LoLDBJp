package application.n.yuki.loldbjp.rest

import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity
import application.n.yuki.loldbjp.rest.entity.StaticChampionsData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import rx.Observable

/**
 * Created by yuki.n on 2017/04/14.
 */
interface StaticChampionApiService {
    @GET("static-data/v3/champions/{id}")
    fun getChampion(@Path("id") id:Int,@QueryMap query: Map<String,String>): Observable<StaticChampionEntity>

    @GET("static-data/v3/champions/")
    fun getChampions(@QueryMap query: Map<String,String>) :Observable<StaticChampionsData>
}