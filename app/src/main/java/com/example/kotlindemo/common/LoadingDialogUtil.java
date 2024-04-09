package com.example.kotlindemo.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.example.kotlindemo.R;
import com.example.kotlindemo.databinding.LoadingDialogBinding;
import com.example.kotlindemo.utils.ResourceUtils;


/**
 * Created by guqingbo on 2016-08-03.
 */
public class LoadingDialogUtil {
    private final static String TAG = "LoadingDialogUtil";

    private static Dialog mdialog;

    public static Dialog getDialog() {
        return mdialog;
    }

    public static void showLoadingDialog(Context activity, int tipId) {
        showLoadingDialog(activity, tipId, null, true);
    }

    public static void showLoadingDialog(Context activity, int tipId, boolean canCancel) {
        showLoadingDialog(activity, tipId, null, canCancel);
    }

    public static void showLoadingDialog(Context activity, int tipId, DialogInterface.OnKeyListener onKeyListener, boolean canCancel) {
        String tip = activity.getString(tipId);
        showLoadingDialog(activity, tip, onKeyListener, canCancel);
    }

    public static void showLoadingDialog(Context activity, String tip) {
        showLoadingDialog(activity, tip, null, false);
    }

    public static void showLoadingDialog(Context activity, String tip, boolean canCancel) {
        showLoadingDialog(activity, tip, null, canCancel);
    }

    public static void updateTitle(String title){
        if(mdialog!=null && mdialog.isShowing() && !TextUtils.isEmpty(title)){
            TextView tv_loading_text = mdialog.getWindow().findViewById(R.id.tv_loading_text);
            tv_loading_text.setText(title);
        }
    }

    public static void showLoadingDialog(Context activity, String tip, DialogInterface.OnKeyListener onKeyListener, boolean canCancle) {
        if (activity instanceof Activity){
            closeLoadingDialog();
            LoadingDialogBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.loading_dialog, null, false);
            mdialog = new Dialog(activity, R.style.Theme_dialog);
            mdialog.setContentView(binding.getRoot(), new ViewGroup.LayoutParams(
                    ((Activity)activity).getWindowManager().getDefaultDisplay().getWidth() * 2 / 3, ViewGroup.LayoutParams.MATCH_PARENT));
            binding.tvLoadingText.setText(tip);
            if (android.os.Build.VERSION.SDK_INT > 22) {
                Drawable drawable = ResourceUtils.getDrawable(R.drawable.loading_222);
                binding.progressBar.setIndeterminateDrawable(drawable);
            }

            // 设置点击外围解散
            mdialog.setCanceledOnTouchOutside(canCancle);
            if (!((Activity)activity).isFinishing()) {
                mdialog.show();
            }
        }
    }
    /*
     * 关闭dialog
	 */

    public static void closeLoadingDialog() {
        if (mdialog != null) {
            try{
                mdialog.dismiss();
            }catch (Exception ex){

            }
        }
    }
}
