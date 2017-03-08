package com.instamojo.sample.common.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.instamojo.sample.R;
import com.instamojo.sample.common.util.Constants;
import com.instamojo.sample.common.util.Constants.DialogKeys;
import com.instamojo.sample.common.util.FragmentUtil;
import com.instamojo.sample.detail.ui.DetailFragment;
import com.instamojo.sample.landing.tags.ui.TagSearchFragment;
import com.instamojo.sample.landing.ui.LandingFragment;
import com.instamojo.sample.landing.ui.QuestionListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 7/3/17.
 */

public class HomeActivity extends AppCompatActivity implements TagSearchFragment.OnTagSearchListener, BaseFragment.ActionBarIconListener, FragmentManager.OnBackStackChangedListener
        , QuestionListFragment.OnQuestionItemClicked {

    private static final int LAUNCH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        showSplashScreen();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSplashScreen();
            }
        }, LAUNCH_SCREEN_DELAY);
    }

    private void initView() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            onBackPressed();
        }
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackStackChanged() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            showBackButton();
        } else {
            // showMenuButton();
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    private void showBackButton() {
      /*  mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setHomeAsUpIndicator(getDrawerToggleDelegate().getThemeUpIndicator());
        setSupportActionBar(mToolBar);*/
    }

    @Override
    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }

    private void showSplashScreen() {
        SplashDialogFragment fragment = SplashDialogFragment.newInstance();
        if (!fragment.isVisible()) {
            fragment.show(getSupportFragmentManager(), DialogKeys.SPLASH_DIALOG_KEY);
        }
    }

    private void hideSplashScreen() {
        final SplashDialogFragment fragment = (SplashDialogFragment) getSupportFragmentManager()
                .findFragmentByTag(DialogKeys.SPLASH_DIALOG_KEY);
        if (fragment != null) {
            fragment.dismissAllowingStateLoss();
            initView();
            openLandingFragment();
        }
    }

    private void openLandingFragment() {
        FragmentUtil.replaceFragment(this, new TagSearchFragment(), R.id.main_container);
    }

    @Override
    public void onTagSearchClicked(List<String> tags) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = new Bundle();
        Fragment fragment = new LandingFragment();
        bundle.putStringArrayList(Constants.BundleKeys.TAG_LIST_KEY, new ArrayList<>(tags));
        fragment.setArguments(bundle);
        FragmentUtil.replaceAndAddFragment(this, fragment, R.id.main_container);
    }

    @Override
    public void onQuestionClicked(String questionId) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle bundle = new Bundle();
        Fragment fragment = new DetailFragment();
        bundle.putString(Constants.BundleKeys.QUESTION_ID_KEY, questionId);
        fragment.setArguments(bundle);
        FragmentUtil.replaceAndAddFragment(this, fragment, R.id.main_container);
    }
}
