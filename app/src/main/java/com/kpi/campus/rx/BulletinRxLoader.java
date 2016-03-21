package com.kpi.campus.rx;

import android.util.Log;

import com.kpi.campus.Config;
import com.kpi.campus.api.service.BulletinService;
import com.kpi.campus.api.service.ServiceCreator;
import com.kpi.campus.model.Bulletin;
import com.kpi.campus.model.User;
import com.kpi.campus.model.dao.BulletinDao;
import com.kpi.campus.model.dao.IDataAccessObject;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 21.03.2016.
 */
public class BulletinRxLoader extends BaseRxLoader {

    @Override
    public void apiCall() {
        BulletinService service = ServiceCreator.createService(BulletinService.class);
        Observable<List<Bulletin>> observable = service.getBulletins("bearer " + User.getInstance().token);

        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNextAction, onErrorAction);
    }

    Action1<List<Bulletin>> onNextAction = new Action1<List<Bulletin>>() {

        @Override
        public void call(List<Bulletin> bulletins) {
            IDataAccessObject<Bulletin> dao = new BulletinDao();
            dao.setData(bulletins);

            Log.d(Config.LOG, "Load successful");
        }
    };

    Action1<Throwable> onErrorAction = new Action1<Throwable>() {

        @Override
        public void call(Throwable e) {
            Log.e(Config.LOG, e.getMessage());
        }
    };

}
