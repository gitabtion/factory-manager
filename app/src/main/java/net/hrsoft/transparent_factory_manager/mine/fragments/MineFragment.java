package net.hrsoft.transparent_factory_manager.mine.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.hrsoft.transparent_factory_manager.R;
import net.hrsoft.transparent_factory_manager.base.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author abtion.
 * @since 17/8/26 21:10.
 * email caiheng@hrsoft.net
 */

public class MineFragment extends BaseFragment {
    @BindView(R.id.img_avatar)
    CircleImageView imgAvatar;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_mobile)
    TextView txtMobile;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData() {

    }


    @OnClick(R.id.rl_personal_info)
    public void onRlPersonalInfoClicked() {
    }

    @OnClick(R.id.rl_update_password)
    public void onRlUpdatePasswordClicked() {
    }

    @OnClick(R.id.rl_edit_mobile)
    public void onRlEditMobileClicked() {
    }

    @OnClick(R.id.rl_employ_manager)
    public void onRlEmployManagerClicked() {
    }

    @OnClick(R.id.rl_exit_account)
    public void onRlExitAccountClicked() {
    }
}
