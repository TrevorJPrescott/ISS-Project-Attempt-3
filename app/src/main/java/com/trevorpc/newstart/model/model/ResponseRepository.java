package com.trevorpc.newstart.model.model;

import android.app.Application;
import android.util.Log;

import com.trevorpc.newstart.model.model.object.FullResponse;
import com.trevorpc.newstart.model.model.object.Response;
import com.trevorpc.newstart.web.MyAPI;
import com.trevorpc.newstart.web.RetrofitClient;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class ResponseRepository {
    private static MyAPI api;
    public List<Response> responses;



    private static final String TAG = "REPOMAN";

    public ResponseRepository(Application application) {
        RetrofitClient client = new RetrofitClient();
        Retrofit retrofit = client.getOurInstance();
        api = retrofit.create(MyAPI.class);

    }

    public void fetchData(Double latitude, Double longitude, final Callback callback)
    {
       api.getResponse(latitude,longitude,20)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new SingleObserver<FullResponse>() {
                   @Override
                   public void onSubscribe(Disposable d) {
                       Log.d(TAG, "onSubscribe: ");
                   }

                   @Override
                   public void onSuccess(FullResponse fullResponse) {
                       callback.onSuccess(fullResponse.getResponse());


                   }

                   @Override
                   public void onError(Throwable e) {
                       Log.d(TAG, "onError: ");
                       callback.onFailure(e.getMessage());

                   }
               });
    }

    public interface Callback{
        void onSuccess(List<Response> responses);
        void onFailure(String error);
    }
}
