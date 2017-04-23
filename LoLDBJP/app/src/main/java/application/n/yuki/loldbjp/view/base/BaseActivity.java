package application.n.yuki.loldbjp.view.base;

import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import application.n.yuki.loldbjp.FragmentReplaceHandler;
import application.n.yuki.loldbjp.FragmentReplacer;
import application.n.yuki.loldbjp.R;


public abstract class BaseActivity extends AppCompatActivity implements FragmentReplacer,
        FragmentManager.OnBackStackChangedListener {
    protected Toolbar toolbar;
    protected View progressView;
    protected ViewGroup contentOverlay;

    private FragmentReplaceHandler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutResId());

        setUpViews();

        handler = new FragmentReplaceHandler(Looper.getMainLooper());
        handler.setActivity(this);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    protected void setUpViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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
