package application.n.yuki.loldbjp.view.activity

import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.view.pager.PickRatePagerAdapter

class PickRateActivity : RoleRankingActivity() {
    override fun getPagerAdapter() = PickRatePagerAdapter(supportFragmentManager)

    override fun onResume() {
        super.onResume()
        setNavigationTitle(getString(R.string.fragment_top_number_pick) + getString(R.string.fragment_top_number_search_initial))
    }
}
