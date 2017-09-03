package com.somu.darwinslab.ethereum.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.somu.darwinslab.ethereum.R;
import com.somu.darwinslab.ethereum.Util.ToastMessage;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ReceiveActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener{

    private BarcodeReader barcodeReader;
    private TextView textView_ethereum_address;
    private ToastMessage toastMessage;
    private static final int CAMERA_PERMISSIONS =10;
    private static final String [] permissions = new String[] {Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        initViews();
        checkPermission();
    }

    private void initViews() {
        // get the barcode reader instance
        textView_ethereum_address = (TextView) findViewById(R.id.tv_ethereum_address);
        ImageView imageView_back_button = (ImageView) findViewById(R.id.iv_back_button);
        TextView textView_toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);
        imageView_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.navigateUpFromSameTask(ReceiveActivity.this);
            }
        });
        textView_toolbar_title.setText(R.string.receive_toolbar_title);
        Toast toast = new Toast(getApplicationContext());
        toastMessage = new ToastMessage(getApplicationContext(), toast);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    private void checkPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED){
            barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
        } else {
            requestRunTimePermissions(this,Manifest.permission.CAMERA);
        }
    }

    private void requestRunTimePermissions(final ReceiveActivity receiveActivity, final String camera) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, camera)){

            Snackbar.make(findViewById(android.R.id.content), R.string.permission_info_string,Snackbar.LENGTH_INDEFINITE).setAction(R.string.permission_enable_string,
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(receiveActivity, permissions, CAMERA_PERMISSIONS);
                            barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(this,new String[]{permissions[0]},CAMERA_PERMISSIONS);
        }
    }

    @Override
    public void onScanned(Barcode barcode) {
        // playing barcode reader beep sound
        barcodeReader.playBeep();

        // set code to text view
        textView_ethereum_address.setText(barcode.displayValue);
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        toastMessage.displayToast(getString(R.string.scanning_qrcode_string) + errorMessage);
    }

    public void copyEthereumAddress(View view) {

        if(!textView_ethereum_address.getText().toString().equals(getString(R.string.blank_string)) || textView_ethereum_address.length() != 0){
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(getString(R.string.qrcode_label_string), textView_ethereum_address.getText().toString().trim());
            clipboard.setPrimaryClip(clip);
            toastMessage.displayToast(getString(R.string.copy_onsuccess_string));
        } else {
            toastMessage.displayToast(getString(R.string.copy_unsuccessful_string));
        }
    }
}
