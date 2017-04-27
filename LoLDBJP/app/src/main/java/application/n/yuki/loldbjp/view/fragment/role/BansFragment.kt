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
import application.n.yuki.loldbjp.view.activity.BansActivity
import application.n.yuki.loldbjp.view.adapter.RoleRankingAdapter
import application.n.yuki.loldbjp.view.adapter.RoleRankingViewEntity
import application.n.yuki.loldbjp.view.fragment.role.BaseRoleRankingFragment
import application.n.yuki.loldbjp.viewmodel.rest.ChampionBanRateViewModel
import java.util.*

/**
 * Created by yuki.n on 2017/04/24.
 */
class BansFragment : BaseRoleRankingFragment(){
    override fun getMainPercent(item: ChampionGGStatEntity) = item.general.banRate
    override fun getSubPercent(item: ChampionGGStatEntity) = item.general.winPercent

    override fun getSubPercentPlaceholder() = "勝率"

    override fun getSortedArrays(original: ArrayList<ChampionGGStatEntity>): ArrayList<ChampionGGStatEntity> {
        original.sortByDescending { it.general.banRate }
        return original
    }
    companion object {
        fun newInstance(role: String): BansFragment {
            val fragment = BansFragment()
            val bundle = Bundle()
            bundle.putString(ARG_ROLE, role)
            fragment.arguments = bundle
            return fragment
        }

    }
}