package mobileid.vnpt.vnptmobileid.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mobileid.vnpt.vnptmobileid.R;

public class ForgotPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setTitle(getString(R.string.str_forgot_password_2));
    }
}
