package mobileid.vnpt.vnptmobileid.app.di;

import android.content.Context;



import dagger.Module;
import dagger.Provides;
import mobileid.vnpt.vnptmobileid.app.LineApplication;
import mobileid.vnpt.vnptmobileid.app.di.NetModule;
import mobileid.vnpt.vnptmobileid.app.di.UserModule;
import mobileid.vnpt.vnptmobileid.ui.activity.BaseActivity;
import mobileid.vnpt.vnptmobileid.ui.activity.ForgotPasswordActivity;
import mobileid.vnpt.vnptmobileid.ui.activity.HomeActivity;
import mobileid.vnpt.vnptmobileid.ui.activity.LoginActivity;
import mobileid.vnpt.vnptmobileid.ui.fragment.BaseFragment;

import javax.inject.Singleton;
/**
 * Created by LiKaLi on 1/22/2018.
 */
@Module(
        includes = {

                UserModule.class,
                NetModule.class
        },
        injects = {
                //App
                LineApplication.class,

                // - view
                BaseActivity.class,
                BaseFragment.class,
                //Activity
                LoginActivity.class,
                ForgotPasswordActivity.class,
                HomeActivity.class
//        //Fragment

//        ThongBaoFragment.class
        }, library = true)
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

}
