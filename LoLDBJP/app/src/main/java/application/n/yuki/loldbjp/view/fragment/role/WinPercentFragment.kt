package application.n.yuki.loldbjp.view.fragment.role

import android.os.Bundle
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.rest.entity.ChampionGGStatEntity
import java.util.*

/**
 * Created by yuki.n on 2017/04/26.
 */
class WinPercentFragment : BaseRoleRankingFragment() {

    override fun getMainPercent(item: ChampionGGStatEntity) = item.general.winPercent
    override fun getSubPercent(item: ChampionGGStatEntity) = item.general.playPercent

    override fun getSubPercentPlaceholder() = "使用率"

    override fun getSortedArrays(original: ArrayList<ChampionGGStatEntity>): ArrayList<ChampionGGStatEntity> {
        original.sortByDescending { it.general.winPercent }
        return original
    }

    companion object {
        fun newInstance(role: String): WinPercentFragment {
            val fragment = WinPercentFragment()
            val bundle = Bundle()
            bundle.putString(ARG_ROLE, role)
            bundle.putInt(ARG_CONTENTS_NAME_ID, R.string.app_name)
            fragment.arguments = bundle
            return fragment
        }
    }
}