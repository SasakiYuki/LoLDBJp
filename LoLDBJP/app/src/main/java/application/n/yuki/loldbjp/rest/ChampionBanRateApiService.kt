package application.n.yuki.loldbjp.rest

import application.n.yuki.loldbjp.rest.entity.ChampionGGStatData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by yuki.n on 2017/04/25.
 */
interface ChampionBanRateApiService {
    @GET("stats/role/{role}")
    fun getBanRate(@Path("role") role:String,@Query("api_key") apiKey:String,@Query("page") page:Int = 1,@Query("limit") limit:Int = 100):Observable<ChampionGGStatData>
}