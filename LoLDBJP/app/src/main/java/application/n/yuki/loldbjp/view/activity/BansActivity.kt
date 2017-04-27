package application.n.yuki.loldbjp.view.activity

import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.view.pager.BansPagerAdapter

class BansActivity : RoleRankingActivity() {
    override fun getPagerAdapter() = BansPagerAdapter(supportFragmentManager)

    override fun onResume() {
        super.onResume()
        setNavigationTitle(getString(R.string.fragment_top_number_ban) + getString(R.string.fragment_top_number_search_initial))
    }
}
