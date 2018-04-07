package com.jasperhale.myprivacy.Activity.ViewModel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;


import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by ZHANG on 2018/1/3.
 */

public class FragmentViewModel extends BaseObservable implements LifecycleObserver {
    //@Bindable
    private Observable<ObservableList> Observable;
    public ObservableList<ApplistItem> items;
    private final String TAG = "FragmentViewModel";
    //订阅缓存
    public BehaviorSubject<ObservableList> behaviorSubject;
    public Observer<ObservableList> setObserver;

    public FragmentViewModel(Observable<ObservableList> Observable) {
        this.Observable = Observable;
        items = new ObservableArrayList<>();
        behaviorSubject = BehaviorSubject.create();

        setObserver = new Observer<ObservableList>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(ObservableList observableList) {
                LogUtil.d(TAG, "setObserver" + observableList.toString());
                //items.clear();
                setItems(observableList);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        };
        behaviorSubject.subscribe(setObserver);
    }


    public void setItems(ObservableList<ApplistItem> items) {
        LogUtil.d(TAG, "setItems");
        if (this.items.size() == 0) {
            this.items.addAll(items);
        } else {
            this.items.clear();
            this.items.addAll(items);
        }
    }

    public ObservableList<ApplistItem> getItems() {
        LogUtil.d(TAG, "getItems");
        return items;
    }
    public void RefreshList(){
        Observable
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(behaviorSubject);
        LogUtil.d(TAG, "setItems");
        behaviorSubject.subscribe(setObserver);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void creat() {
        LogUtil.d(TAG, getClass().getSimpleName() + "start");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void start() {
        LogUtil.d(TAG, getClass().getSimpleName() + "start");
        //Observable.subscribe(behaviorSubject);
        //this.setItems(mainRepository.getItems_user());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void resume() {
        LogUtil.d(TAG, getClass().getSimpleName() + "resume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void pause() {
        LogUtil.d(TAG, getClass().getSimpleName() + "pause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void stop() {
        LogUtil.d(TAG, getClass().getSimpleName() + "stop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void destory() {
        LogUtil.d(TAG, getClass().getSimpleName() + "destory");
        behaviorSubject.onComplete();
    }

}
