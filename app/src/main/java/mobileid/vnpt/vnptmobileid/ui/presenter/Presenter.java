package mobileid.vnpt.vnptmobileid.ui.presenter;

import mobileid.vnpt.vnptmobileid.ui.view.View;

/**
 * Created by MinhDN on 7/5/2018.
 */
public interface Presenter<T extends View> {

    void setView(T view);

    void onViewCreate();

    void onViewStart();

    void onViewResume();

    void onViewPause();

    void onViewStop();

    void onViewDestroy();

}
