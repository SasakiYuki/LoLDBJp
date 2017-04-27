package application.n.yuki.loldbjp.view.fragment.role

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import application.n.yuki.loldbjp.contract.ChampionBanRateContract
import application.n.yuki.loldbjp.marker.CoordinatorMarker
import application.n.yuki.loldbjp.marker.FixedToolbarMarker
import application.n.yuki.loldbjp.rest.entity.ChampionGGStatData
import application.n.yuki.loldbjp.rest.entity.ChampionGGStatEntity
import application.n.yuki.loldbjp.rest.entity.StaticChampionsData
import application.n.yuki.loldbjp.view.activity.RoleRankingActivity
import application.n.yuki.loldbjp.view.adapter.RoleRankingAdapter
import application.n.yuki.loldbjp.view.adapter.RoleRankingViewEntity
import application.n.yuki.loldbjp.view.fragment.BaseRecyclerFragment
import application.n.yuki.loldbjp.viewmodel.rest.ChampionBanRateViewModel
import java.util.*

/**
 * Created by yuki.n on 2017/04/26.
 */
abstract class BaseRoleRankingFragment : BaseRecyclerFragment(), ChampionBanRateContract.BanRateView, FixedToolbarMarker, CoordinatorMarker {
    override fun getRecyclerManager() = LinearLayoutManager.VERTICAL

    private lateinit var adapter: RoleRankingAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    override fun onResume() {
        super.onResume()
        adapter.clearList()
        if (getRoleActivity().checkRoleBansData(getRoleString())) {
            setListItems(getRoleActivity().getListItems(getRoleString()))
        } else {
            setupViewModel()
        }
    }

    private fun setupViewModel() {
        val viewModel = ChampionBanRateViewModel(activity.applicationContext)
        viewModel.setView(this)
        viewModel.getBanRate(getRoleString())
    }

    private fun setUpViews() {
        adapter = RoleRankingAdapter(context)
        setupRecyclerAdapter(adapter)
    }

    private fun setListItems(entities: ArrayList<ChampionGGStatEntity>) {
        val list = getSortedArrays(entities)
        val championData = getRoleActivity().getStaticChampionData()
        championData?.let {
            var count = 1
            for (item in list) {
                generateRoleRankingViewEntity(count, item, championData)
                count++
            }
        }
    }

    private fun generateRoleRankingViewEntity(count: Int, ggStatEntity: ChampionGGStatEntity, staticChampionsData: StaticChampionsData) {
        for (item in staticChampionsData.data.entries) {
            if (item.value.key.equals(ggStatEntity.key)) {
                val entity = RoleRankingViewEntity(count,
                        item.value.image.full,
                        item.value.name,
                        item.value.key,
                        getMainPercent(ggStatEntity),
                        getSubPercent(ggStatEntity),
                        getSubPercentPlaceholder() + ":")
                adapter.addEntity(entity)
            }
        }
    }

    override fun onSuccess(entities: ChampionGGStatData) {
        getRoleActivity().addListItems(getRoleString(), entities.data)
        setListItems(entities.data)
    }

    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    abstract fun getMainPercent(item:ChampionGGStatEntity):Float
    abstract fun getSubPercent(item:ChampionGGStatEntity):Float
    abstract fun getSubPercentPlaceholder(): String
    abstract fun getSortedArrays(original: ArrayList<ChampionGGStatEntity>): ArrayList<ChampionGGStatEntity>
    private fun getRoleString() = arguments.getString(ARG_ROLE)
    private fun getRoleActivity() = context as RoleRankingActivity

    companion object {
        val ARG_ROLE = "arg_role"
    }
}