package application.n.yuki.loldbjp.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import application.n.yuki.loldbjp.R;


public abstract class BasePagerActivity extends BaseActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        setupDrawer();
    }

    private void setupDrawer() {

        drawerLayout = (DrawerLayout) findViewById(R.id.select_contents_drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.main_drawer_navigation);
        navigationView.setNavigationItemSelectedListener(selectedListener);

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(toggle);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.drawer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private NavigationView.OnNavigationItemSelectedListener selectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            drawerLayout.closeDrawers();
            return true;
        }
    };

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        drawerLayout.removeDrawerListener(toggle);
    }

    protected void setupViewPager(FragmentPagerAdapter pagerAdapter) {
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab);
        tabLayout.setupWithViewPager(viewPager);
    }
}
