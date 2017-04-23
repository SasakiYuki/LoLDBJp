package application.n.yuki.loldbjp.view.activity

import android.os.Bundle
import application.n.yuki.loldbjp.view.base.BasePagerActivity
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.view.pager.MainPagerAdapter

class MainActivity : BasePagerActivity() {
    override fun getContentLayoutResId(): Int {
        return R.layout.activity_pager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewPager(MainPagerAdapter(this,supportFragmentManager))
    }
}
