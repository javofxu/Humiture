package com.example.humiture.ui.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.base.BaseFragment;
import com.example.humiture.R;
import com.example.humiture.R2;
import com.example.humiture.app.App;
import com.example.humiture.data.KuFangSetData;
import com.example.humiture.data.NumberData;
import com.example.humiture.data.Warehouse;
import com.example.humiture.greenDao.DaoSession;
import com.example.humiture.greenDao.KuFangSetDataDao;
import com.example.humiture.mvp.contract.MineKuFangContract;
import com.example.humiture.mvp.presenter.MineKuFangPresenter;
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
public class MineKuFangFragment extends BaseFragment<MineKuFangPresenter> implements MineKuFangContract.View {

    private static final String TAG = "MineKuFangFragment";

    @BindView(R2.id.up_wendu)
    TextView up_wendu;
    @BindView(R2.id.down_wendu)
    TextView down_wendu;
    @BindView(R2.id.up_shidu)
    TextView up_shidu;
    @BindView(R2.id.down_shidu)
    TextView down_shidu;
    @BindView(R2.id.up_pm)
    TextView up_pm;
    @BindView(R2.id.down_pm)
    TextView down_pm;
    @BindView(R2.id.up_tvoc)
    TextView up_tvoc;
    @BindView(R2.id.down_tvoc)
    TextView down_tvoc;
    @BindView(R2.id.up_junluo)
    TextView up_junluo;
    @BindView(R2.id.down_junluo)
    TextView down_junluo;
    @BindView(R2.id.up_jiaquan)
    TextView up_jiaquan;
    @BindView(R2.id.down_jiaquan)
    TextView down_jiaquan;
    @BindView(R2.id.up_eoc2)
    TextView up_eoc2;
    @BindView(R2.id.down_eoc2)
    TextView down_eoc2;
    @BindView(R2.id.up_gas)
    TextView up_gas;
    @BindView(R2.id.down_gas)
    TextView down_gas;

    private String up_wD,down_WD,up_SD,down_SD,up_PM,down_PM,up_TVOC,down_TVOC,up_JL,down_JL,up_JQ,down_JQ,up_ECO2,down_ECO2,up_GAS,down_GAS;

    private OptionsPickerView optionsPickerView = null;

    private ArrayList<NumberData> numberData = new ArrayList<>();

    private int id;
    private String name;
    private DaoSession daoSession;
    private KuFangSetData kuFangSetData;
    private List<KuFangSetData> list;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine_kufang;
    }

    @Override
    protected void initPresent() {
        super.initPresent();
        mPresent = new MineKuFangPresenter(mContext);
        GreenDaoHelp.getInstance(getActivity()).initGreenDao(getActivity());
    }

    @Override
    protected void initData() {
        super.initData();
        name = getArguments().getString("name");
        Log.i(TAG, "initData: " + name);
        //进行数据库查询数据
        mPresent.show(list,name,up_wendu,down_wendu, up_shidu,down_shidu,up_pm,down_pm,
                up_tvoc,down_tvoc,up_junluo,down_junluo, up_jiaquan,down_jiaquan,up_eoc2,
                down_eoc2, up_gas,down_gas);
        mPresent.getWenDuData(numberData);
        initOptionPicker();
    }

    @OnClick({R2.id.kufang_ok,R2.id.kufang_wendu,R2.id.kufang_shidu,
            R2.id.kufang_pm,R2.id.kufang_tvoc,R2.id.kufang_bacterial,
            R2.id.kufang_formol,R2.id.kufang_eoc2,R2.id.kufang_gas})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.kufang_ok:
                //执行上传代码
                Log.i(TAG, "onClick: " + name + "---" + up_wD + "---" + down_WD + "---" + up_SD + "---" + down_SD + "---" + up_PM + "---" + up_TVOC);
                if(TextUtils.isEmpty(name)){
                    showToast("库房不能为空");
                }else{
                    mPresent.getKuFangData("1",up_wD,down_WD,up_SD,down_SD,up_PM,up_TVOC);
                }
                break;
                //温度
            case R.id.kufang_wendu:
                id = 1;
                optionsPickerView.show();
                break;
                //湿度
            case R.id.kufang_shidu:
                id = 2;
                optionsPickerView.show();
                break;
                //pm
            case R.id.kufang_pm:
                id = 3;
                optionsPickerView.show();
                break;
                //tvoc
            case R.id.kufang_tvoc:
                id = 4;
                optionsPickerView.show();
                break;
                //bacterial
            case R.id.kufang_bacterial:
                id = 5;
                optionsPickerView.show();
                break;
                //formol
            case R.id.kufang_formol:
                id = 6;
                optionsPickerView.show();
                break;
                //eoc2
            case R.id.kufang_eoc2:
                id = 7;
                optionsPickerView.show();
                break;
                //gas
            case R.id.kufang_gas:
                id = 8;
                optionsPickerView.show();
                break;
        }
    }

    @Override
    public void onFail(String msg) {
        showToast(msg);
    }

    @Override
    public void getWareHouse(List<Warehouse.Data> data) {

    }

    @Override
    public void onSuccess() {
        //将库房设置的数据保存在本地,使用的是GreenDao，如果没有库房，则进行添加数据，如果已经有了，那么就进行更新
        list = GreenDaoHelp.getInstance(getActivity()).getStorId(name);
        daoSession = GreenDaoHelp.getInstance(getActivity()).getDaoSession();
        kuFangSetData = new KuFangSetData();
        Log.i(TAG, "onSuccess: " + list.size());
        if(list.size() <= 0){
            kuFangSetData.setName(name);
            kuFangSetData.setUp_WD(up_wD);
            kuFangSetData.setDown_WD(down_WD);
            kuFangSetData.setUp_SD(up_SD);
            kuFangSetData.setDown_SD(down_SD);
            kuFangSetData.setUp_PM(up_PM);
            kuFangSetData.setDown_PM(down_PM);
            kuFangSetData.setUp_TVOC(up_TVOC);
            kuFangSetData.setDown_TVOC(down_TVOC);
            kuFangSetData.setUp_JL(up_JL);
            kuFangSetData.setDown_JL(down_JL);
            kuFangSetData.setUp_JQ(up_JQ);
            kuFangSetData.setDown_JQ(down_JQ);
            kuFangSetData.setUp_EOC2(up_ECO2);
            kuFangSetData.setDown_EOC2(down_ECO2);
            kuFangSetData.setUp_GAS(up_GAS);
            kuFangSetData.setDown_GAS(down_GAS);
            daoSession.insert(kuFangSetData);
            showToast("保存成功");
        }else{
            for (int i = 0; i < list.size(); i++) {
                Long id = list.get(i).getId();
                int storeId = list.get(i).getStoreId();
                String storname = list.get(i).getName();
                Log.i(TAG, "onSuccess: " + list.get(i).getId() + "---" + list.get(i).getStoreId() + "---" + list.get(i).getName());
                if(name.equals(storname)){
                    kuFangSetData.setId(id);
                    kuFangSetData.setStoreId(storeId);
                    kuFangSetData.setName(name);
                    kuFangSetData.setUp_WD(up_wendu.getText().toString());
                    kuFangSetData.setDown_WD(down_wendu.getText().toString());
                    kuFangSetData.setUp_SD(up_shidu.getText().toString());
                    kuFangSetData.setDown_SD(down_shidu.getText().toString());
                    kuFangSetData.setUp_PM(up_pm.getText().toString());
                    kuFangSetData.setDown_PM(down_pm.getText().toString());
                    kuFangSetData.setUp_TVOC(up_tvoc.getText().toString());
                    kuFangSetData.setDown_TVOC(down_tvoc.getText().toString());
                    kuFangSetData.setUp_JL(up_junluo.getText().toString());
                    kuFangSetData.setDown_JL(down_junluo.getText().toString());
                    kuFangSetData.setUp_JQ(up_jiaquan.getText().toString());
                    kuFangSetData.setDown_JQ(down_jiaquan.getText().toString());
                    kuFangSetData.setUp_EOC2(up_eoc2.getText().toString());
                    kuFangSetData.setDown_EOC2(down_eoc2.getText().toString());
                    kuFangSetData.setUp_GAS(up_gas.getText().toString());
                    kuFangSetData.setDown_GAS(down_gas.getText().toString());
                    daoSession.update(kuFangSetData);
                    showToast("保存成功");
                }
            }
        }
    }

    /**
     * 不联动的选择器
     */
    private void initOptionPicker(){
        optionsPickerView = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String str = "第一列" + numberData.get(options1).getNumber() + "\n第二列" + numberData.get(options2).getNumber();
                //可以加上单位
                if(id == 1){
                    up_wD = numberData.get(options1).getNumber();
                    down_WD = numberData.get(options2).getNumber();
                    up_wendu.setText(up_wD);
                    down_wendu.setText(down_WD);
                }else if(id == 2){
                    up_SD = numberData.get(options1).getNumber();
                    down_SD = numberData.get(options2).getNumber();
                    up_shidu.setText(up_SD);
                    down_shidu.setText(down_SD);
                }else if(id == 3){
                    up_PM = numberData.get(options1).getNumber();
                    down_PM = numberData.get(options2).getNumber();
                    up_pm.setText(up_PM);
                    down_pm.setText(down_PM);
                }else if(id == 4){
                    up_TVOC = numberData.get(options1).getNumber();
                    down_TVOC = numberData.get(options2).getNumber();
                    up_tvoc.setText(up_TVOC);
                    down_tvoc.setText(down_TVOC);
                }else if(id == 5){
                    up_JL = numberData.get(options1).getNumber();
                    down_JL = numberData.get(options2).getNumber();
                    up_junluo.setText(up_JL);
                    down_junluo.setText(down_JL);
                }else if(id == 6){
                    up_JQ = numberData.get(options1).getNumber();
                    down_JQ = numberData.get(options2).getNumber();
                    up_jiaquan.setText(up_JQ);
                    down_jiaquan.setText(down_JQ);
                }else if(id == 7){
                    up_ECO2 = numberData.get(options1).getNumber();
                    down_ECO2 = numberData.get(options2).getNumber();
                    up_eoc2.setText(up_ECO2);
                    down_eoc2.setText(down_ECO2);
                }else if(id == 8){
                    up_GAS = numberData.get(options1).getNumber();
                    down_GAS = numberData.get(options2).getNumber();
                    up_gas.setText(up_GAS);
                    down_gas.setText(down_GAS);
                }else{

                }
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
            @Override
            public void customLayout(View v) {
                final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                final TextView tv_cancel = (TextView) v.findViewById(R.id.tv_cancel);
                tvSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionsPickerView.returnData();
                        optionsPickerView.dismiss();
                    }
                });

                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        optionsPickerView.dismiss();
                    }
                });

            }
        })
                .build();
        optionsPickerView.setNPicker(numberData, numberData, null);
        optionsPickerView.setSelectOptions(23, 13, 0);
    }

}
