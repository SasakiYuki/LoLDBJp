package application.n.yuki.loldbjp.view.base

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import application.n.yuki.loldbjp.R


abstract class BasePagerActivity : BaseActivity() {
    private var drawerLayout: DrawerLayout? = null
    private var toggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDrawer()
    }

    override fun getContentLayoutResId(): Int {
        return R.layout.activity_pager
    }


    private fun setupDrawer() {
        drawerLayout = findViewById(R.id.select_contents_drawer) as DrawerLayout
        val navigationView = findViewById(R.id.main_drawer_navigation) as NavigationView
        navigationView.setNavigationItemSelectedListener(selectedListener)

        toggle = ActionBarDrawerToggle(
                this, drawerLayout, R.string.app_name, R.string.app_name)
        drawerLayout!!.addDrawerListener(toggle!!)

        val recyclerView = findViewById(R.id.drawer_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    private val selectedListener = NavigationView.OnNavigationItemSelectedListener {
        drawerLayout!!.closeDrawers()
        true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle!!.syncState()
    }

    override fun onDestroy() {
        super.onDestroy()
        drawerLayout!!.removeDrawerListener(toggle!!)
    }

    protected fun setupViewPager(pagerAdapter: FragmentPagerAdapter) {
        val viewPager = findViewById(R.id.main_viewpager) as ViewPager
        viewPager.adapter = pagerAdapter
        val tabLayout = findViewById(R.id.main_tab) as TabLayout
        tabLayout.setupWithViewPager(viewPager)
    }
}
