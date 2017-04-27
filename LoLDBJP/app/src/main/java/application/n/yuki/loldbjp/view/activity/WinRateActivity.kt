package application.n.yuki.loldbjp.view.activity

import android.support.v4.app.FragmentPagerAdapter
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.view.pager.WinRatePagerAdapter

class WinRateActivity : RoleRankingActivity() {
    override fun getPagerAdapter(): FragmentPagerAdapter {
        return WinRatePagerAdapter(supportFragmentManager)
    }

    override fun onResume() {
        super.onResume()
        setNavigationTitle(getString(R.string.fragment_top_number_win) + getString(R.string.fragment_top_number_search_initial))
    }
}
