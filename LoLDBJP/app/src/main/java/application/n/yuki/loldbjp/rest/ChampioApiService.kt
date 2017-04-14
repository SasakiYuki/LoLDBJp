package application.n.yuki.loldbjp.rest

import retrofit2.http.GET
import retrofit2.http.QueryMap
import rx.Observable

/**
 * Created by yuki.n on 2017/04/14.
 */
interface ChampioApiService {
    @GET("/champion")
    fun getChampion(@QueryMap queryMap: Map<String,String>):Observable
}