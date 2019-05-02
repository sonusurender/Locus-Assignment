package com.locus.assignment.main;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.locus.assignment.BuildConfig;
import com.locus.assignment.R;
import com.locus.assignment.adapter.MyRecyclerAdapter;
import com.locus.assignment.helper.EqualSpacingItemDecoration;
import com.locus.assignment.interfaces.OnCaptureImageListener;
import com.locus.assignment.models.ItemModel;
import com.locus.assignment.utils.DeviceUtils;
import com.locus.assignment.utils.FileUtils;
import com.locus.assignment.utils.JsonUtils;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnCaptureImageListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindDimen(R.dimen.recycler_view_margin)
    int recyclerMargin;
    @BindString(R.string.no_camera_app)
    String noCameraAppString;

    private static final String fileName = "items.json";

    private MyRecyclerAdapter adapter;

    private static final int CAMERA_REQUEST_CODE = 102;
    private Uri cameraFileURI;

    private int captureImagePosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpRecyclerView();
        populateRecyclerView();
    }

    private void setUpRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(recyclerMargin, EqualSpacingItemDecoration.VERTICAL));
        adapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnCaptureImageListener(this);
    }

    //populating recyclerview
    private void populateRecyclerView() {
        progressBar.setVisibility(View.VISIBLE);
        try {
            JSONArray jsonArray = new JSONArray(JsonUtils.loadJSONFromAsset(this, fileName));
            Gson gson = new Gson();
            ItemModel[] itemModels = gson.fromJson(jsonArray.toString(), ItemModel[].class);
            List<ItemModel> itemModelList = new ArrayList<>(Arrays.asList(itemModels));
            adapter.swapData(itemModelList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        progressBar.setVisibility(View.GONE);
    }

    //method to read json data from assets

    @OnClick(R.id.submit_button)
    void onSubmitButton() {
        //Logging all item on click
        List<ItemModel> itemModelList = adapter.getItemModelList();
        Log.d(TAG, "ALL ITEMS : " + itemModelList.toString());
    }


    @Override
    public void captureImage(int position) {
        this.captureImagePosition = position;
        captureImage();
    }

    private void captureImage() {

        if (!DeviceUtils.isDeviceSupportCamera(this)) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File createImageFile = FileUtils.createOutputImageFile(this);
        cameraFileURI = FileProvider.getUriForFile(this, BuildConfig.FILES_AUTHORITY, createImageFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraFileURI);
        for (ResolveInfo resolveInfo : getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)) {
            grantUriPermission(resolveInfo.activityInfo.packageName, cameraFileURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(this, noCameraAppString, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    adapter.updateImage(captureImagePosition, cameraFileURI);
                } else {
                    Log.d(TAG, "User cancelled.");
                }
                break;
            default:

                break;
        }
    }

}
