package mobileid.vnpt.vnptmobileid.domain.interactor;



import javax.inject.Inject;

import mobileid.vnpt.vnptmobileid.domain.repository.api.UserApi;
import retrofit2.Response;
import rx.Observable;

/**
 * Created by MinhDN on 7/5/2018.
 */
public class UserInteractorImpl implements UserInteractor {
    @Inject
    UserApi userApi;
    @Override
    public Observable<Response<String>> checkService(String appgamecode, Integer os, String deviceid, String auth, String country) {
        return userApi.checkService(appgamecode, os, deviceid, auth, country);
    }

}
