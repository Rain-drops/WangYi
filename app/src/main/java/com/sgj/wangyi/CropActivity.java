package com.sgj.wangyi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.sgj.wangyi.imagecrop.Crop;

import java.io.File;

/**
 * Created by John on 2016/5/12.
 */
public class CropActivity extends Activity {

    private ImageView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        resultView = (ImageView) findViewById(R.id.result_image);

        init();
    }

    private void init() {
        Uri source = Uri.parse(getIntent().getExtras().getString("source"));
        beginCrop(source);
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent();
            intent.putExtra("source", Crop.getOutput(result).toString());
            setResult(100, intent);
            this.finish();
//            resultView.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
//            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("type", "error");
            setResult(100, intent);
            this.finish();
        }
    }
}
