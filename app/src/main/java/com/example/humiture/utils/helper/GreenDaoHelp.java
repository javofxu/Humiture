package com.example.humiture.utils.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.humiture.data.KuFangSetData;
import com.example.humiture.greenDao.DaoMaster;
import com.example.humiture.greenDao.DaoSession;
import com.example.humiture.greenDao.KuFangSetDataDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 *Time:2019/6/25
 *Author:冰冰凉
 *dec:  GreenDao的帮助类
 */
public class GreenDaoHelp {

    private DaoSession daoSession;
    private static GreenDaoHelp greenDaoHelp;
    private Context context;

    public GreenDaoHelp(Context context) {
        this.context = context;
    }

    public static GreenDaoHelp getInstance(Context context){
        if(greenDaoHelp == null){
            synchronized (GreenDaoHelp.class){
                if(greenDaoHelp == null){
                    greenDaoHelp = new GreenDaoHelp(context);
                }
            }
        }
        return greenDaoHelp;
    }

    /**
     * GreenDao的初始化
     */
    public void initGreenDao(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"kufangset.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * 根据库房的id查数据
     * @param msg
     * @return
     */
    public List getStorId(String msg){
        daoSession = GreenDaoHelp.getInstance(context).getDaoSession();
        QueryBuilder<KuFangSetData> qb = daoSession.queryBuilder(KuFangSetData.class);
        QueryBuilder<KuFangSetData> kuFangSetDataQueryBuilder = qb.where(KuFangSetDataDao.Properties.Name.eq(msg)).orderAsc(KuFangSetDataDao.Properties.Id);
        List<KuFangSetData> kuFangSetDataList = kuFangSetDataQueryBuilder.list();       //查出当前对应的数据
        return kuFangSetDataList;
    }

}
