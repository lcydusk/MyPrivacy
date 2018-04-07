package com.jasperhale.myprivacy.Activity.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jasperhale.myprivacy.Activity.Base.LogUtil;
import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.Activity.item.ApplistItem;
import com.jasperhale.myprivacy.Activity.item.DiffCallBack_ApplistItem;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ZHANG on 2017/12/10.
 */

public class BindAdapter_applist extends RecyclerView.Adapter<BindingHolder> {

    private final String TAG = "BindAdapter_applist";
    private ObservableList<ApplistItem> items;


    public BindAdapter_applist(@Nullable ObservableList<ApplistItem> items) {
        super();
        this.items = items;
        //this.itemsChangeCallback = new ListChangedCallback();
    }

    //获取ObservableList<ApplistItem> 实例
    public ObservableList<ApplistItem> getItems() {
        return items;
    }

    //显示list<item>
    public void setItems(@Nullable ObservableList<ApplistItem> items) {
        this.items = items;
    }

    //清除item
    public void clearItems() {
        items.clear();
    }

    //绑定ObservableList 回掉
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        //this.setItems(items);
    }

    //解绑
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        //this.items.removeOnListChangedCallback(itemsChangeCallback);
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new BindingHolder(binding);
    }

    /*
    * 数据绑定
    * */
    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        holder.bindData(items.get(position));
    }

    /*
    * 定向刷新
    * */
    @Override
    public void onBindViewHolder(BindingHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
            LogUtil.d("Adatper", "");
            for (String key : payload.keySet()) {
                switch (key) {
                    case "AppId":
                        items.get(position).setAppId(payload.getString("AppId"));
                        break;
                    case "AppName":
                        items.get(position).setAppName(payload.getString("AppName"));
                        break;
                    case "AppIcon":
                        Bitmap bitmap = payload.getParcelable("AppIcon");
                        items.get(position).setAppIcon(new BitmapDrawable(MyApplicantion.getContext().getResources(), bitmap));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }


}
