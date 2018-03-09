package com.meltwater.fairhairai.base;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by thinhnguyen on 1/25/18.
 */

public abstract class BaseActivity extends AppCompatActivity {

   // protected CustomProgressDialog mProgressDialog;

    public boolean isAlive() {
        return !isFinishing() && !isDestroyed();
    }

    public boolean isDead() {
        return !isAlive();
    }

    public void showProgressDialog(int msgId) {
        showProgressDialog(getString(msgId));
    }

    public void showProgressDialog(String msg) {

        if (isDead()) return;

//        if (mProgressDialog == null) {
//            mProgressDialog = new CustomProgressDialog(BaseActivity.this);
//            mProgressDialog.setCanceledOnTouchOutside(false);
//            mProgressDialog.setCancelable(true);
//        }
//
//        mProgressDialog.setMessage(msg);
//        mProgressDialog.show();

    }

    public void dismissProgressDialog() {
        if (isDead()) return;
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
    }
}