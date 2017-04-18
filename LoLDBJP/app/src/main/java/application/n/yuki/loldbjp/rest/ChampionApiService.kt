package application.n.yuki.loldbjp.rest

import application.n.yuki.loldbjp.rest.entity.ChampionEntity
import application.n.yuki.loldbjp.rest.entity.ChampionsEntity
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import rx.Observable

/**
 * Created by yuki.n on 2017/04/14.
 */
interface ChampionApiService {
    @GET("platform/v3/champions")
    fun getChampion(@Query("freeToPlay") boolean: Boolean,@Query("api_key") string:String):Observable<ChampionsEntity>
}