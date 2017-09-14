package net.hrsoft.transparent_factory_manager.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.TFMApplication;
import net.hrsoft.transparent_factory_manager.account.activities.LoginActivity;
import net.hrsoft.transparent_factory_manager.base.activities.NoBarActivity;
import net.hrsoft.transparent_factory_manager.common.constants.CacheKey;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author abtion.
 * @since 17/9/13 20:34.
 * email caiheng@hrsoft.net
 */

public class FirstActivity extends NoBarActivity {
    @BindView(R.id.txt_time_cut)
    TextView txtTimeCut;
    CountDownTimer timer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_first;
    }

    @Override
    protected void initVariable() {
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTimeCut.setText((millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {
                startAct();
            }
        };
    }

    @Override
    protected void initView() {
        timer.start();
    }

    @Override
    protected void loadData() {

    }



    @OnClick(R.id.btn_jump)
    public void onViewClicked() {
        startAct();
        timer.cancel();
    }

    private void startAct(){
        if (TFMApplication.getInstance().getCacheUtil().getString(CacheKey.TOKEN)!=null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
