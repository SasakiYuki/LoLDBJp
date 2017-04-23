package application.n.yuki.loldbjp.view.pager

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import application.n.yuki.loldbjp.R
import application.n.yuki.loldbjp.view.fragment.TopFragment

/**
 * Created by yuki.n on 2017/04/18.
 */
class MainPagerAdapter(context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val topTitle:String = context.getString(R.string.pager_top)

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> TopFragment.newInstance()
            else -> TopFragment.newInstance()
        }
    }

    override fun getCount() = 1

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            0 -> topTitle
            else -> topTitle
        }
    }
}