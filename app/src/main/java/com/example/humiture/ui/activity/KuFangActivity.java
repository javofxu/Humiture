package com.example.humiture.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.base.BaseActivity;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.app.App;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.greenDao.DaoSession;
import com.example.humiture.greenDao.KuFangSetDataDao;
import com.example.humiture.mvp.contract.MineInfoContract;
import com.example.humiture.mvp.contract.MineKuFangContract;
import com.example.humiture.mvp.presenter.MineKuFangPresenter;
import com.example.humiture.ui.view.adapter.MyFragmentPagerAdapter;
import com.example.humiture.utils.helper.GreenDaoHelp;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *Time:2019/5/23
 *Author:冰冰凉
 *dec:库房环境设置
 */
public class KuFangActivity extends BaseActivity<MineKuFangPresenter> implements MineKuFangContract.View {

    @BindView(R2.id.viewPager)
    ViewPager viewPager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private TabLayout mTabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private List<String> list;
    private String name = null;
    private Integer id = null;
    private DaoSession daoSession;
    private KuFangSetData kuFangSetData;
    private List<KuFangSetData> kuFangSetDataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_kufang;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new MineKuFangPresenter(this);
    }

    @Override
    protected void initView() {
        super.initView();
        initViews();
        GreenDaoHelp.getInstance(this).initGreenDao(this);
    }

    @Override
    public void initData() {
        super.initData();
        mPresent.getWareHouse();
    }

    private void initViews() {
        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(viewPager);
//        mPresent.reflex(mTabLayout);
        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
    }

    @OnClick({R.id.mine_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_back:
                finish();
                break;
        }
    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void getWareHouse(List<Warehouse.Data> data) {
        list = new ArrayList<>();
        daoSession = GreenDaoHelp.getInstance(this).getDaoSession();
        for (int i = 0; i < data.size(); i++) {
            name = data.get(i).getName();
            id = data.get(i).getStoreId();
            kuFangSetDataList = isExit(name);
            Log.i(TAG, "getWareHouse: " + kuFangSetDataList.size());
            if(kuFangSetDataList.size() <= 0){
                kuFangSetData = new KuFangSetData();
                kuFangSetData.setStoreId(id);
                kuFangSetData.setName(name);
                daoSession.insert(kuFangSetData);
            }
            Log.i(TAG, "getWareHouse: " + name + "---" + id);
            list.add(name);
        }
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),list,"b");
        viewPager.setAdapter(myFragmentPagerAdapter);
    }

    /**
     * 判断数据库是否有数据
     * @param name
     * @return
     */
    private List<KuFangSetData> isExit(String name){
        daoSession = GreenDaoHelp.getInstance(this).getDaoSession();
        QueryBuilder<KuFangSetData> qb = daoSession.queryBuilder(KuFangSetData.class);
        QueryBuilder<KuFangSetData> kuFangSetDataQueryBuilder = qb.where(KuFangSetDataDao.Properties.Name.eq(name)).orderAsc(KuFangSetDataDao.Properties.Id);
        List<KuFangSetData> kuFangSetDataList = kuFangSetDataQueryBuilder.list();       //查出当前对应的数据
        return kuFangSetDataList;
    }

    @Override
    public void onSuccess() {

    }
}
