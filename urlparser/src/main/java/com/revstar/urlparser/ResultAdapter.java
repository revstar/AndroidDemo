package com.revstar.urlparser;

import android.app.Activity;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Create on 2019/8/21 10:45
 * author revstar
 * Email 1967919189@qq.com
 */
public class ResultAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    private Activity mActivity;
    public ResultAdapter(Activity activity, int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.mActivity=activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (item!=null){
            Glide.with(mActivity).load(item).into((ImageView) helper.getView(R.id.ig_result));
        }
    }
}
