package com.revauc.revolutionbuy.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.revauc.revolutionbuy.R;
import com.revauc.revolutionbuy.databinding.BottomAlertLayoutBinding;
import com.revauc.revolutionbuy.databinding.SingleBottomAlertLayoutBinding;
import com.revauc.revolutionbuy.eventbusmodel.OnButtonClicked;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;


public class SingleActionBottomSheetAlert implements View.OnClickListener {
    private static final int SPINNER_COUNT = 3;

    private SingleBottomAlertLayoutBinding mBinding;
    private BottomSheetDialog mBottomSheetDialog;
    private Context mContext;
    private Calendar mCalendar = Calendar.getInstance();
    private static SingleActionBottomSheetAlert ourInstance;


    public static SingleActionBottomSheetAlert getInstance(final Context context, String message, String positiveText) {
        if (ourInstance == null) {
            synchronized (SingleActionBottomSheetAlert.class) {
                if (ourInstance == null) {
                    ourInstance = new SingleActionBottomSheetAlert(context, message, positiveText);
                }
            }
        }

        return ourInstance;
    }

    public void show() {
        if (mBottomSheetDialog != null && !mBottomSheetDialog.isShowing()) {
            mBottomSheetDialog.show();
        }
    }


    private SingleActionBottomSheetAlert(final Context context, String message, String positiveText) {
        mContext = context;
        mBottomSheetDialog = new BottomSheetDialog(mContext);
        mBottomSheetDialog.setCancelable(false);
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.single_bottom_alert_layout, null, false);

        //Setting Values
        mBinding.textMessage.setText(message);
        mBinding.textPositive.setText(positiveText);

        mBottomSheetDialog.setContentView(mBinding.getRoot());
        mBottomSheetDialog.getWindow()
                .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });

        mBinding.textPositive.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_positive:
//                EventBus.getDefault().post(new OnButtonClicked(true));
                clearInstance();
                break;

            default:
                break;
        }
    }

    private void clearInstance() {
        mBottomSheetDialog.dismiss();
        ourInstance = null;
    }
}
