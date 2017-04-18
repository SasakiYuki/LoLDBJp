package application.n.yuki.loldbjp

/**
 * Created by yuki.n on 2017/04/14.
 */
object AppConfig {
    fun getApiEndPoint() = String.format("https://%s",BuildConfig.API_HOST)
}