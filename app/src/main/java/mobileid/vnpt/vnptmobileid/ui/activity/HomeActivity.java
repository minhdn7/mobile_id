package mobileid.vnpt.vnptmobileid.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobileid.vnpt.vnptmobileid.R;


public class HomeActivity extends BaseActivity {
    BottomSheetDialog bottomSheetDialog;

    @BindView(R.id.tv_gioithieu)
    TextView tvGioithieu;

    @BindView(R.id.lo_no_cert)
    LinearLayout loNoCert;

    @BindView(R.id.buttonMenu)
    FloatingActionMenu buttonMenu;
    @BindView(R.id.btnAddCert)
    Button btnAddCert;
    @BindView(R.id.bt_list_cert)
    FloatingActionButton btListCert;
    @BindView(R.id.bt_create_cert)
    FloatingActionButton btCreateCert;
    @BindView(R.id.bt_upgrade_cert)
    FloatingActionButton btUpgradeCert;
    @BindView(R.id.bt_pay_cert)
    FloatingActionButton btPayCert;
    @BindView(R.id.bt_setting)
    FloatingActionButton btSetting;
    @BindView(R.id.bt_info)
    FloatingActionButton btInfo;
    @BindView(R.id.bt_exit)
    FloatingActionButton btExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        addFloatingMenu();
    }

    private void addFloatingMenu() {
        buttonMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonMenu.isOpened()) {
//                    Toast.makeText(HomeActivity.this, buttonMenu.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }

                buttonMenu.toggle(true);
            }
        });

    }


    @OnClick({R.id.bt_list_cert, R.id.bt_create_cert, R.id.bt_upgrade_cert, R.id.bt_pay_cert, R.id.bt_setting, R.id.bt_info, R.id.bt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_list_cert:
                if (buttonMenu.isOpened()) {
                    buttonMenu.close(true);
                }
                break;
            case R.id.bt_create_cert:
                break;
            case R.id.bt_upgrade_cert:
                break;
            case R.id.bt_pay_cert:
                break;
            case R.id.bt_setting:
                break;
            case R.id.bt_info:
                break;
            case R.id.bt_exit:
                break;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_list_cert:

                    return true;
                case R.id.navigation_tool:

                    return true;
                case R.id.navigation_menu:

                    return true;
            }
            return false;
        }
    };
}
