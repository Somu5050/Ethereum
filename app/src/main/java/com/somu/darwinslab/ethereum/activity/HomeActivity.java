package com.somu.darwinslab.ethereum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.somu.darwinslab.ethereum.R;
import com.somu.darwinslab.ethereum.Util.ToastMessage;

public class HomeActivity extends AppCompatActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private Boolean exit = false;
    private ToastMessage toastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toast toast = new Toast(this);
        toastMessage = new ToastMessage(this, toast);
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));
        capturingBottomSheetBehaviour();
    }

    private void capturingBottomSheetBehaviour() {


        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        bottomSheetBehavior.setHideable(false);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        bottomSheetBehavior.setHideable(false);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        bottomSheetBehavior.setHideable(false);
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        bottomSheetBehavior.setHideable(false);
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        bottomSheetBehavior.setHideable(false);
                        break;
                }
            }


            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                bottomSheetBehavior.setHideable(false);
            }
        });

    }


    public void goToSendActivity(View view) {
        Intent intentForSendActivity = new Intent(getBaseContext(), SendActivity.class);
        startActivity(intentForSendActivity);
    }

    public void goToReceiveActivity(View view) {
        Intent intentForReceiveActivity = new Intent(getBaseContext(), ReceiveActivity.class);
        startActivity(intentForReceiveActivity);
    }

    public void goToBuySellActivity(View view) {
        toastMessage.displayToast(getString(R.string.screen_not_provided_toast_string));
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            toastMessage.displayToast(getString(R.string.press_back_to_exit_toast_string));
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }
}
