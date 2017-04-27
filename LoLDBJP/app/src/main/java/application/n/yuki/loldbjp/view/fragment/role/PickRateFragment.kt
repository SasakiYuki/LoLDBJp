package application.n.yuki.loldbjp.view.fragment.role

import android.os.Bundle
import application.n.yuki.loldbjp.rest.entity.ChampionGGStatEntity
import java.util.*

/**
 * Created by yuki.n on 2017/04/26.
 */
class PickRateFragment : BaseRoleRankingFragment(){

    override fun getMainPercent(item: ChampionGGStatEntity) = item.general.playPercent
    override fun getSubPercent(item: ChampionGGStatEntity) = item.general.winPercent

    override fun getSubPercentPlaceholder() = "勝率"

    override fun getSortedArrays(original: ArrayList<ChampionGGStatEntity>): ArrayList<ChampionGGStatEntity> {
        original.sortByDescending { it.general.playPercent }
        return original
    }

    companion object {
        fun newInstance(role: String): PickRateFragment{
            val fragment = PickRateFragment()
            val bundle = Bundle()
            bundle.putString(ARG_ROLE, role)
            fragment.arguments = bundle
            return fragment
        }
    }
}