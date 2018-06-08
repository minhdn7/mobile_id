package mobileid.vnpt.vnptmobileid.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mobileid.vnpt.vnptmobileid.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.txtPassword)
    @NotEmpty(messageResId = R.string.str_not_empty_pass)
    EditText txtPassword;
    @BindView(R.id.txt_input_layout)
    TextInputLayout txtInputLayout;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLogin, R.id.tv_forgot_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                validator.validate();
                if(isPassedValidate){
                    LoginActivity.this.finish();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                break;
            case R.id.tv_forgot_password:
                LoginActivity.this.finish();
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;
        }
    }

}
