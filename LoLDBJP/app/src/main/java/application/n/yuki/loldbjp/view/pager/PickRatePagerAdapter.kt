package application.n.yuki.loldbjp.view.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import application.n.yuki.loldbjp.type.RoleType
import application.n.yuki.loldbjp.view.fragment.role.PickRateFragment

/**
 * Created by yuki.n on 2017/04/26.
 */
class PickRatePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return PickRateFragment.newInstance(RoleType.values()[position].role)
    }

    override fun getCount() = RoleType.values().size

    override fun getPageTitle(position: Int): CharSequence {
        return RoleType.values()[position].role
    }
}
