package com.trevorpc.newstart.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.trevorpc.newstart.R;
import com.trevorpc.newstart.adapter.RecyclerAdapter;
import com.trevorpc.newstart.databinding.ActivityMainBinding;
import com.trevorpc.newstart.model.model.object.Response;
import com.trevorpc.newstart.viewmodel.ResponseViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN";
    private ActivityMainBinding mainBinding;
    private ResponseViewModel viewModel;
    private RecyclerAdapter adapterMain;
    List<Response> listResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapterMain = new RecyclerAdapter(this, listResponse);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.RecyclerView.setHasFixedSize(true);

        ResponseViewModel viewModel = new ResponseViewModel(this.getApplication(), this);
        viewModel.fetchLocation(new ResponseViewModel.Callback() {
            @Override
            public void onSuccess(List<Response> responses) {
                adapterMain.updateList(responses);
                mainBinding.RecyclerView.setAdapter(adapterMain);
            }

            @Override
            public void onFailure(String error) {

            }

        });
    }
}


//        ResponseViewModel viewModel = new ResponseViewModel(this.getApplication(),this);
//        viewModel.fetchLocation(new ResponseViewModel.Callback() {
//                                    @Override
//                                    public void onSuccess(RecyclerAdapter adapter) {
//                                        mainBinding.RecyclerView.setAdapter(adapter);
//                                    }
//
//                                    @Override
//                                    public void onFailure(String error) {
//                                        Log.d(TAG, "onFailure: " + error);
//                                    }
//                                });


//        setContentView(R.layout.activity_main);

//      REPO TEST
//        ResponseRepository repo = new ResponseRepository(this.getApplication());
//
//        repo.fetchData(45.0, 45.0, new ResponseRepository.Callback() {
//            @Override
//            public void onSuccess(List<Response> responses) {
//                Log.d(TAG, "onSuccess: " + responses.size());
//            }
//
//            @Override
//            public void onFailure(String error) {
//
//            }
//        });
//        List<Response> responseList = repo.responses;
//        Log.d(TAG, "onCreate: " + responseList.get(0).toString());

                //Log.d(TAG, "onCreate: " + responseList.get(1).getDuration());


//        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        mainBinding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mainBinding.RecyclerView.setHasFixedSize(true);
//        ResponseViewModel viewModel = new ResponseViewModel(this.getApplication(),this);
//        viewModel.fetchLocation(new ResponseViewModel.Callback() {
//            @Override
//            public void onSuccess(RecyclerAdapter adapter) {
//
//                mainBinding.RecyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(String error) {
//                Log.d(TAG, "onFailure: ");
//            }
//        });

