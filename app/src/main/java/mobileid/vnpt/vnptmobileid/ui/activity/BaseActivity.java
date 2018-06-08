package mobileid.vnpt.vnptmobileid.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.multidex.MultiDex;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;


import com.google.android.gms.analytics.Tracker;
import com.kaopiz.kprogresshud.KProgressHUD;


import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import mobileid.vnpt.vnptmobileid.R;
import mobileid.vnpt.vnptmobileid.app.LineApplication;
import mobileid.vnpt.vnptmobileid.app.utils.NetworkStateChanged;
import mobileid.vnpt.vnptmobileid.domain.repository.TinyDB;

public class BaseActivity extends AppCompatActivity implements Validator.ValidationListener{
    protected boolean isPassedValidate;
    public Validator validator;
    private KProgressHUD hud;
    private Tracker mTracker;
    public TinyDB tinyDB;
    MaterialDialog dialog;
    private android.app.AlertDialog alertDialog;

    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT <= 20) {
            // only for gingerbread and newer versions
            MultiDex.install(this);
        }
        validator = new Validator(this);
        validator.setValidationListener(this);
        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDetailsLabel("Đang kết nối...")
                .setCancellable(true)
                .setAnimationSpeed(3)
                .setDimAmount(0.5f);
//        googleAnalyticEvent();
        injectDependencies();
        tinyDB = new TinyDB(this);
    }

    private void initAds() {
        try {

//            mAdView = findViewById(R.id.adView);
//            AdRequest adRequest = new AdRequest.Builder().build();
//            mAdView.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initAds();
    }

    @Override
    public void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onValidationSucceeded() {
        isPassedValidate = true;
        Log.e("Valid: ", "Valid Success");
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isPassedValidate = false;
        Log.e("Valid: ", "" + isPassedValidate);
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void injectDependencies() {
        ((LineApplication) getApplication()).inject(this);
    }

    public boolean isConnectedNetwork() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(getApplication().INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }


    public Boolean isSpecialCharAvailable(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
//        Pattern p = Pattern.compile("[^A-Za-z0-9]");//replace this with your needs
        Pattern p = Pattern.compile("[^A-Za-z0-9]");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }

    public Boolean kiemTraKyTuSo(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[0-9]");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }

    public Boolean validatePassWord(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;

    }
    public Boolean kiemTraKyTuChu(String s) {
        //int counter =0;
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[A-Za-z]");//replace this with your needs
        Matcher m = p.matcher(s);
        // boolean b = m.matches();

        boolean b = m.find();
        if (b == true)
            return true;
        else
            return false;
    }


    public void showProgressBar() {
        try{
            if (hud != null && !hud.isShowing())
                hud.show();
        }catch (Exception ex){

        }

    }

    public void hideProgressBar() {
        try{
            if (hud != null && hud.isShowing())
                hud.dismiss();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }catch (Exception ex){

        }

    }


    public void showProgress(String titleProgress) {
        if (dialog == null) {
            dialog = new MaterialDialog.Builder(this).title(titleProgress)
                    .content(R.string.str_vui_long_doi)
                    .progress(true, 0)
                    .progressIndeterminateStyle(true)
                    .cancelable(false)
                    .show();
        }
    }

    public void showProgress() {
        if (dialog == null) {
            dialog = new MaterialDialog.Builder(this).content(R.string.str_vui_long_doi)
                    .progress(true, 0)
                    .progressIndeterminateStyle(true)
                    .cancelable(false)
                    .show();
        }
    }

    public void showDialog(String contentDialog) {
        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.custom_dialog_show_toast, null);
        final android.app.AlertDialog yesDialog = new android.app.AlertDialog.Builder(this).create();
        yesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        yesDialog.setView(deleteDialogView);
        TextView text = deleteDialogView.findViewById(R.id.text_message);
        Typeface face = Typeface.createFromAsset(this.getAssets(), "fonts/SFUFuturaBook.TTF");
        text.setTypeface(face);
        text.setText(contentDialog);
        deleteDialogView.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesDialog.dismiss();
            }
        });

        yesDialog.show();
    }

    public void showDialog(String contentDialog, final boolean isFinish) {
        MaterialDialog dialog = new MaterialDialog.Builder(this).title(R.string.str_thong_bao)
                .content(contentDialog)
                .positiveText(R.string.str_oke)
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialogInterface) {
                        if (isFinish) {
                            finish();
                        }
                    }
                })
                .show();
    }

    public void dismissProgress() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }



    public void dilogThongBao(String title, String noiDung, String sButtton){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_thong_bao, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(false);
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
        // set width
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(b.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        width = (int) (width * 0.8);
        lp.width = width;
        lp.gravity = Gravity.CENTER;
        b.getWindow().setAttributes(lp);
        //
        TextView txtHeaderDialog = (TextView) dialogView.findViewById(R.id.txtHeaderDialog);
        TextView txtNoiDung = (TextView) dialogView.findViewById(R.id.txtNoiDung);

        Button btnTiepTuc = (Button) dialogView.findViewById(R.id.btnTiepTuc);

        txtHeaderDialog.setText(title);
        txtNoiDung.setText(noiDung);
        btnTiepTuc.setText(sButtton);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

    }


    public boolean isInternetAvailable() {
        try {
            final InetAddress address = InetAddress.getByName("www.google.com");
            return !address.equals("");
        } catch (UnknownHostException e) {
            // Log error
        }
        return false;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(final NetworkStateChanged networkStateChanged) {

        if (networkStateChanged.isInternetConnected()) {

            if(alertDialog.isShowing()){
                alertDialog.dismiss();
            }

        } else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle("Mất kết nối mạng");
            builder.setMessage("Vui lòng kiểm tra lại kết nối");
            builder.setCancelable(false);
            builder.setPositiveButton("Thử lại", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View view) {
                    if (isNetworkConnected()) {
                        alertDialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    } else {
                        Intent intent = new Intent(Settings.ACTION_SETTINGS);
                        startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }

    public void showToast(String message) {
        if (toast != null) {
          toast.cancel();
          toast = null;
        }
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }


    public void dialogExit(){

        LayoutInflater factory = LayoutInflater.from(this);
        final View deleteDialogView = factory.inflate(R.layout.custom_dialog_logout, null);
        final android.app.AlertDialog yesDialog = new android.app.AlertDialog.Builder(this).create();
        yesDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        yesDialog.setView(deleteDialogView);
        TextView text = deleteDialogView.findViewById(R.id.text);
        Typeface face = Typeface.createFromAsset(this.getAssets(), "fonts/SFUFuturaBook.TTF");
        text.setTypeface(face);
        deleteDialogView.findViewById(R.id.btnYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        deleteDialogView.findViewById(R.id.btnNo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yesDialog.dismiss();
            }
        });

        yesDialog.show();
    }

}
