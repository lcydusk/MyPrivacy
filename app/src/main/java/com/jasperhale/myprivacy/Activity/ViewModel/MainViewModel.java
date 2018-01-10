package com.jasperhale.myprivacy.Activity.ViewModel;

import android.arch.lifecycle.*;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.Repository.MainRepository;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;


/**
 * Created by ZHANG on 2017/12/31.
 */

public class MainViewModel extends android.arch.lifecycle.ViewModel implements LifecycleObserver {
    private MainRepository mainRepository;
    public ObservableList<ApplistItem> items_system;
    public ObservableList<ApplistItem> items_user;
    public ObservableList<ApplistItem> items_limit;
    private String TAG = "MainViewModel";

    public MainViewModel() {
        mainRepository = new MainRepository();
        this.items_user = new ObservableArrayList<>();
        this.items_system = new ObservableArrayList<>();
        this.items_limit = new ObservableArrayList<>();
    }

    public ObservableList<ApplistItem> getItems_user() {
        return items_user;
    }

    public ObservableList<ApplistItem> getItems_system() {
        return items_system;
    }

    public ObservableList<ApplistItem> getItems_limit() {
        return items_limit;
    }


    //activity creat 初始化数据
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void creat() {
        items_user = mainRepository.getItems_user();
        items_system = mainRepository.getItems_system();
        items_limit = mainRepository.getItems_limit();
        //items_user.addAll(mainRepository.getItems_user());
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

    public void SearchRecyclerview(String query,int position){
        switch (position){
            case 0:{
                LogUtil.d(TAG,"SearchRecyclerview"+position);
                //items_user.setItems(mainRepository.getItems_user(query));
                //mainRepository.getItems_user(items_user.items,query);
                break;
            }
            case 1:{
                LogUtil.d(TAG,"SearchRecyclerview"+position);
                //items_system.items = mainRepository.getItems_system(qiery);
                break;
            }
            case 2:{
                LogUtil.d(TAG,"SearchRecyclerview"+position);
                //items_limit.items = mainRepository.getItems_user(qiery);
                break;
            }
            default: break;
        }
    }
}