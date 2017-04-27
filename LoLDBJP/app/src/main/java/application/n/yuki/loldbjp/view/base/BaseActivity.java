package application.n.yuki.loldbjp.view.base;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import application.n.yuki.loldbjp.marker.CoordinatorMarker;
import application.n.yuki.loldbjp.marker.FixedToolbarMarker;
import application.n.yuki.loldbjp.utils.FragmentReplaceHandler;
import application.n.yuki.loldbjp.utils.FragmentReplacer;
import application.n.yuki.loldbjp.R;


public abstract class BaseActivity extends AppCompatActivity implements FragmentReplacer,
        FragmentManager.OnBackStackChangedListener {
    protected Toolbar toolbar;
    protected View progressView;
    protected ViewGroup contentOverlay;

    private FragmentReplaceHandler handler;

    private int actionBarPixelSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutResId());

        setUpViews();

        handler = new FragmentReplaceHandler(Looper.getMainLooper());
        handler.setActivity(this);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        // ActionBarのデフォルトの高さを取得
        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        actionBarPixelSize = styledAttributes.getDimensionPixelSize(0, 0);
    }

    protected void setUpViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressView = findViewById(R.id.progress_view);
        contentOverlay = (ViewGroup) findViewById(R.id.content_overlay);
    }

    public void setNavigationTitle(String title) {
        toolbar.setTitle(title);
    }

    public void setNavigationTitle(@StringRes int id) {
        toolbar.setTitle(id);
    }

    protected void setInitialFragment(BaseFragment fragment) {
        replaceFragment(fragment, false);
    }

    @Override
    public void replaceFragment(BaseFragment fragment) {
        replaceFragment(fragment, true);
    }

    @Override
    public void replaceFragment(BaseFragment fragment, boolean animated) {
        if (fragment != null) {
            setUpHeader(fragment);
            setUpOverlay(fragment);
        }

        if (animated) {
            handler.replaceFragment(fragment, FragmentReplaceHandler.ANIMATED);
        } else {
            handler.replaceFragment(fragment, FragmentReplaceHandler.NON_ANIMATED);
        }
    }

    abstract protected int getContentLayoutResId();

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    protected void setUpHeader(BaseFragment fragment) {
        setNavigationTitle(fragment.getTitle(this));
    }

    @Override
    public void onBackStackChanged() {
        final BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null) {
            setUpHeader(fragment);
            setUpOverlay(fragment);
        }

        adjustContentBottomMargin();
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        if (fragment instanceof FixedToolbarMarker) {
            params.setScrollFlags(0);
        } else {
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        adjustContentBottomMargin();
    }

    protected void setUpOverlay(BaseFragment fragment) {
        contentOverlay.removeAllViews();
        if (fragment instanceof OverlayContentProvider) {
            final View view = ((OverlayContentProvider) fragment).getOverlayContent(this);
            if (view != null) {
                contentOverlay.addView(view);
                showOverlay();
            }
        }
    }

    /**
     * CoordinatorLayoutによって自動調整されないコンテンツ領域のマージンを調節する
     */
    public void adjustContentBottomMargin() {
        final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null && fragment.getView() != null) {
            if (!(fragment instanceof CoordinatorMarker)) {
                ((ViewGroup.MarginLayoutParams) fragment.getView().getLayoutParams()).bottomMargin = actionBarPixelSize;
            }
        }
    }

    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressView.setVisibility(View.GONE);
    }

    public void showOverlay() {
        contentOverlay.setVisibility(View.VISIBLE);
    }

    public void hideOverlay() {
        contentOverlay.setVisibility(View.GONE);
    }
}
