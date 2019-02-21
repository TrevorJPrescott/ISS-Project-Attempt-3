package com.trevorpc.newstart.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.trevorpc.newstart.adapter.RecyclerAdapter;
import com.trevorpc.newstart.model.model.ResponseRepository;
import com.trevorpc.newstart.model.model.object.Response;

import java.util.ArrayList;
import java.util.List;

public class ResponseViewModel extends AndroidViewModel {

    private static final String TAG = "VIEWMODEL";

    List<Response> responseList = new ArrayList<>();
    Context context;
    Double latitude, longitude;
    LocationManager manager;
    LocationListener listener;
    ResponseRepository repo;
    RecyclerAdapter adapter;

    Activity activity;

    public ResponseViewModel(@NonNull Application application, Activity activity) {
        super(application);
        ResponseRepository repo = new ResponseRepository(this.getApplication());
        context = application.getApplicationContext();

        latitude =45.0;
        longitude = 45.0;

        this.activity = activity;

    }

    public void fetchLocation(final Callback callback2Main) {
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        adapter = new RecyclerAdapter(context, new ArrayList<Response>());

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                Log.d(TAG, "Location: " + latitude + " " + longitude);

                repo.fetchData(latitude, longitude, new ResponseRepository.Callback() {
                    @Override
                    public void onSuccess(List<Response> responses) {
                        adapter.updateList(responses);
                        callback2Main.onSuccess(responses);
                    }

                    @Override
                    public void onFailure(String error) {
                        Log.d(TAG, "onFailure: ");
                        callback2Main.onFailure("Error");
                    }
                });
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) { Log.d(TAG, "onStatusChanged: "); }
            @Override
            public void onProviderEnabled(String provider) { }
            @Override
            public void onProviderDisabled(String provider) { }
        };

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            manager.requestLocationUpdates("gps",5000,0,listener);
        } else
            {
            manager.requestLocationUpdates("gps", 5000, 0, listener);
            }
    }



    public interface Callback {
        void onSuccess(List<Response> responses);
        void onFailure(String error);
    }



}


