package com.jasperhale.myprivacy.Activity.ViewModel;

import android.arch.lifecycle.*;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.Repository.MainRepository;


/**
 * Created by ZHANG on 2017/12/31.
 */

public class MainViewModel extends android.arch.lifecycle.ViewModel implements LifecycleObserver {
    public MainRepository mainRepository;
    public FragmentViewModel items_system;
    public FragmentViewModel items_user;
    public FragmentViewModel items_limit;
    private String TAG = "MainViewModel";

    public MainViewModel() {
        mainRepository = new MainRepository();
        this.items_user = new FragmentViewModel(mainRepository.items_userObservable);
        this.items_system = new FragmentViewModel(mainRepository.items_systemObservable);
        this.items_limit = new FragmentViewModel(mainRepository.items_limitObservable);
    }


    //activity creat 初始化数据
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void creat() {
        LogUtil.d(TAG, "caeat");
        mainRepository.items_userObservable.subscribe(items_user.behaviorSubject);
        mainRepository.items_systemObservable.subscribe(items_system.behaviorSubject);
        mainRepository.items_limitObservable.subscribe(items_limit.behaviorSubject);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        LogUtil.d(TAG, "start");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void resume() {
        LogUtil.d(TAG, "resume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void pause() {
        LogUtil.d(TAG, "pause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        LogUtil.d(TAG, "stop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void destory() {
        LogUtil.d(TAG, "destory");
    }

    public void SearchRecyclerview(String query, int position) {
        switch (position) {
            case 0: {
                LogUtil.d(TAG, "SearchRecyclerview" + position);
                break;
            }
            case 1: {
                LogUtil.d(TAG, "SearchRecyclerview" + position);
                break;
            }
            case 2: {
                LogUtil.d(TAG, "SearchRecyclerview" + position);
                break;
            }
            default:
                break;
        }
    }
}
