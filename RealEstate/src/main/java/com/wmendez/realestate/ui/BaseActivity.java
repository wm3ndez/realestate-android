package com.wmendez.realestate.ui;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.wmendez.realestate.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class BaseActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar_actionbar) Toolbar mActionBarToolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
        getActionBarToolbar();
    }

    protected Toolbar getActionBarToolbar() {
        if (mActionBarToolbar != null) {
            setSupportActionBar(mActionBarToolbar);
        }
        return mActionBarToolbar;
    }
}
