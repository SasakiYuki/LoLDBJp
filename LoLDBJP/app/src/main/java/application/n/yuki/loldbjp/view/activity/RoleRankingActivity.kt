package application.n.yuki.loldbjp.view.activity

import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.contract.StaticChampionContract
import application.n.yuki.loldbjp.rest.entity.ChampionGGStatEntity
import application.n.yuki.loldbjp.rest.entity.StaticChampionEntity
import application.n.yuki.loldbjp.rest.entity.StaticChampionsData
import application.n.yuki.loldbjp.type.RoleType
import application.n.yuki.loldbjp.view.base.BasePagerActivity
import application.n.yuki.loldbjp.viewmodel.rest.StaticChampionViewModel
import java.util.*

abstract class RoleRankingActivity : BasePagerActivity(), StaticChampionContract.StaticChampionView {
    private var staticChampionData: StaticChampionsData? = null
    private val topBansList: ArrayList<ChampionGGStatEntity> = ArrayList()
    private val midBansList: ArrayList<ChampionGGStatEntity> = ArrayList()
    private val jungleBunsList: ArrayList<ChampionGGStatEntity> = ArrayList()
    private val marksmanBunsList: ArrayList<ChampionGGStatEntity> = ArrayList()
    private val supportBunsList: ArrayList<ChampionGGStatEntity> = ArrayList()

    override fun getContentLayoutResId(): Int {
        return R.layout.activity_pager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: StaticChampionViewModel = StaticChampionViewModel(applicationContext)
        viewModel.setView(this)
        viewModel.getChampions()
    }

    fun checkRoleBansData(role: String) =
            !when (role) {
                RoleType.TOP.role -> topBansList.isEmpty()
                RoleType.MID.role -> midBansList.isEmpty()
                RoleType.JUNGLE.role -> jungleBunsList.isEmpty()
                RoleType.ADC.role -> marksmanBunsList.isEmpty()
                RoleType.SUPPORT.role -> supportBunsList.isEmpty()
                else -> true
            }

    fun addListItems(role: String, items: ArrayList<ChampionGGStatEntity>) {
        when (role) {
            RoleType.TOP.role -> topBansList.addAll(items)
            RoleType.MID.role -> midBansList.addAll(items)
            RoleType.JUNGLE.role -> jungleBunsList.addAll(items)
            RoleType.ADC.role -> marksmanBunsList.addAll(items)
            RoleType.SUPPORT.role -> supportBunsList.addAll(items)
        }
    }

    fun getListItems(role: String): ArrayList<ChampionGGStatEntity> {
        return when (role) {
            RoleType.TOP.role -> topBansList
            RoleType.MID.role -> midBansList
            RoleType.JUNGLE.role -> jungleBunsList
            RoleType.ADC.role -> marksmanBunsList
            RoleType.SUPPORT.role -> supportBunsList
            else -> ArrayList()
        }
    }

    fun getStaticChampionData() = staticChampionData

    abstract fun getPagerAdapter(): FragmentPagerAdapter

    override fun onSuccessChampion(staticChampionEntity: StaticChampionEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessChampions(staticChampionsData: StaticChampionsData) {
        this.staticChampionData = staticChampionsData
        setupViewPager(getPagerAdapter())
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

}
