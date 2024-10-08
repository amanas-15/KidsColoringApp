package com.example.kidscolouringapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thebluealliance.spectrum.SpectrumPalette;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

import common.Common;
import widget.PaintView;

public class PaintActivity extends AppCompatActivity implements SpectrumPalette.OnColorSelectedListener {

    private static final int PERMISION_REQUEST = 1000 ;
    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);

        initToolbar();
        SpectrumPalette spectrumPalette = findViewById(R.id.palette);
        spectrumPalette.setOnColorSelectedListener(this);

        paintView = findViewById(R.id.paint_view);

    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.tollbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_bar_save){
            showDialogForSave();

        }else if (id == android.R.id.home){
           super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogForSave() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISION_REQUEST);
            }
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Save Picture ?");
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            });
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    save();
                    dialogInterface.dismiss();

                }
            });
            builder.show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == PERMISION_REQUEST && grantResults.length > 0&& grantResults[0]== PackageManager.PERMISSION_GRANTED){
            save();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void save() {

        Bitmap bitmap = paintView.getBitmap();
        String file_name = UUID.randomUUID()+".png";
        File folder = new File(Environment.getExternalStorageDirectory()+File.separator+Environment.DIRECTORY_PICTURES+File.separator+getString(R.string.app_name));
        if (!folder.exists()){
            folder.mkdir();

        }
        try {
            FileOutputStream outputStream = new FileOutputStream(folder+File.separator+file_name);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Toast.makeText(this,"Picture Saved",Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public void onColorSelected(int color) {
        Common.COLOR_SELECTED =  color;
    }
}

