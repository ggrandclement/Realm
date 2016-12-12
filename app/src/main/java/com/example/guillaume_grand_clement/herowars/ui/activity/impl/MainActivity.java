package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.TextView;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.data.query.sample.SampleQuery;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AbsActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private RealmResults<SamplePojo> mResult;

    @BindView(R.id.name) TextView mTextViewName;

    public static void start(Context mContext) {
        Intent login = new Intent(mContext, MainActivity.class);
        mContext.startActivity(login);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //addAsyncRealmListener();
        reset();
    }

    @OnClick(R.id.button)
    protected void onCallClick() {
        reset();
    }

    //UpdateUI
    @OnClick(R.id.button1)
    protected void updateUI(){
        if(mResult.size()>0){
            mTextViewName.setText("name :"+ mResult.get(0).getName());
        }
    }

    @OnClick(R.id.button2)
    protected void onCall2Click() {
        final RealmResults<SamplePojo> tmp = mRealm.where(SamplePojo.class).equalTo("name", "toto").findAll();
        if(tmp.size()>0){
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    tmp.get(0).setName("new Toto");
                    Log.d(TAG,tmp.get(0).getName()+ " is the new name");
                }
            });
        }
    }

    private void reset(){
        final SamplePojo object = new SamplePojo();
        object.setName("toto");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mRealm.delete(SamplePojo.class);
                realm.copyToRealm(object);
            }
        });
        mResult = mRealm.where(SamplePojo.class).findAll();
        Log.d(TAG, mResult.toString());
        updateUI();
    }

    private void addAsyncRealmListener(){
        mSubscriptions.add(SampleQuery.getSample(mRealm).filter(new Func1<RealmResults<SamplePojo>, Boolean>() {
            @Override
            public Boolean call(RealmResults<SamplePojo> samplePojos) {
                return samplePojos.size() > 0;
            }
        }).doOnNext(new Action1<RealmResults<SamplePojo>>() {
            @Override
            public void call(RealmResults<SamplePojo> samplePojos) {
                Snackbar.make(MainActivity.this.getContentView(),
                        "Bienvenue " + samplePojos.get(0).getName() + " !",
                        Snackbar.LENGTH_LONG)
                        .show();
            }
        }).subscribe());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
