package application.n.yuki.loldbjp

import android.app.Application
import android.util.Log
import application.n.yuki.loldbjp.rest.ChampionApiService
import application.n.yuki.loldbjp.rest.ChampionBanRateApiService
import application.n.yuki.loldbjp.rest.StaticChampionApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by yuki.n on 2017/04/14.
 */
class LoLApplication : Application() {
    private val logging = HttpLoggingInterceptor({ Log.d("API_LOG", it) }).setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()
    private val loggingGG = HttpLoggingInterceptor({ Log.d("API_LOG", it)}).setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val clientGG = OkHttpClient.Builder().addInterceptor(loggingGG).build()

    private lateinit var championApiService: ChampionApiService
    private lateinit var staticChampionApiService: StaticChampionApiService
    private lateinit var championBanRateApiService: ChampionBanRateApiService

    override fun onCreate() {
        super.onCreate()
        setUpApiClient()
        setUpApiClientGG()
    }

    private fun setUpApiClient() {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConfig.getApiEndPoint())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

        championApiService = retrofit.create(ChampionApiService::class.java)
        staticChampionApiService = retrofit.create(StaticChampionApiService::class.java)
    }

    private fun setUpApiClientGG() {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(AppConfig.getAPiEndPointGG())
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientGG)
                .build()

        championBanRateApiService = retrofit.create(ChampionBanRateApiService::class.java)
    }

    fun getChampionApiService() = championApiService
    fun getStaticChampionApiService() = staticChampionApiService
    fun getChampionBanRateApiService() = championBanRateApiService
}
