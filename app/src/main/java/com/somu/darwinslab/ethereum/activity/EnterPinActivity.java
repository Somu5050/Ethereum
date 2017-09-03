package com.somu.darwinslab.ethereum.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.somu.darwinslab.ethereum.R;
import com.somu.darwinslab.ethereum.Util.ToastMessage;

import java.util.ArrayList;

public class EnterPinActivity extends AppCompatActivity {

    private ImageView imageView_pin_one, imageView_pin_two, imageView_pin_three, imageView_pin_four;
    ArrayList<String> myPin;
    private ToastMessage toastMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin);
        initViews();
    }

    private void initViews() {
        myPin = new ArrayList<>();
        ImageView imageView_back_button = (ImageView) findViewById(R.id.iv_back_button);
        TextView textView_toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);
        imageView_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMessage.displayToast(getString(R.string.unsuccessful_transaction_toast_string));
                NavUtils.navigateUpFromSameTask(EnterPinActivity.this);
            }
        });
        textView_toolbar_title.setText(R.string.enter_pin_toolbar_title);
        imageView_pin_one = (ImageView) findViewById(R.id.iv_pin_one);
        imageView_pin_two = (ImageView) findViewById(R.id.iv_pin_two);
        imageView_pin_three = (ImageView) findViewById(R.id.iv_pin_three);
        imageView_pin_four = (ImageView) findViewById(R.id.iv_pin_four);
        Toast toast = new Toast(getApplicationContext());
        toastMessage = new ToastMessage(getApplicationContext(), toast);
    }

    public void goToHome(View view) {
        if(myPin.size() == 4){
            Intent intentForHomeActivity = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intentForHomeActivity);
            toastMessage.displayToast(getString(R.string.successful_transaction_toast_string));
        }else {
            toastMessage.displayToast(getString(R.string.incomplete_pin_toast_string));
        }
    }

    public void fillPin(String i){
        if (myPin.size() < 4){
            myPin.add(i);
            showPinCircles(myPin.size());
        } else {
            toastMessage.displayToast(getString(R.string.complete_pin_toast_string));
        }
    }

    private void showPinCircles(int size) {
        if(size == 1) {
            imageView_pin_one.setImageDrawable(getResources().getDrawable(R.drawable.solidcircle));
        }
        if(size == 2){
            imageView_pin_two.setImageDrawable(getResources().getDrawable(R.drawable.solidcircle));
        }
        if(size == 3) {
            imageView_pin_three.setImageDrawable(getResources().getDrawable(R.drawable.solidcircle));
        }
        if(size == 4){
            imageView_pin_four.setImageDrawable(getResources().getDrawable(R.drawable.solidcircle));
        }
    }

    public void onClickOneButton(View view) {
        fillPin(getString(R.string.one_string));
    }

    public void onClickTwoButton(View view) {
        fillPin(getString(R.string.two_string));
    }

    public void onClickThreeButton(View view) {
        fillPin(getString(R.string.three_string));
    }

    public void onClickFourButton(View view) {
        fillPin(getString(R.string.four_string));
    }

    public void onClickFiveButton(View view) {
        fillPin(getString(R.string.five_string));
    }

    public void onClickSixButton(View view) {
        fillPin(getString(R.string.six_string));
    }

    public void onClickSevenButton(View view) {
        fillPin(getString(R.string.seven_string));
    }

    public void onClickEightButton(View view) {
        fillPin(getString(R.string.eight_string));
    }

    public void onClickNineButton(View view) {
        fillPin(getString(R.string.nine_string));
    }

    public void onClickBackSpaceButton(View view) {
        if(!myPin.isEmpty()){
            myPin.remove(myPin.size() - 1);
            dontShowPinCircles(myPin.size());
        }
    }

    private void dontShowPinCircles(int size) {
        if(size == 0) {
            imageView_pin_one.setImageDrawable(getResources().getDrawable(R.drawable.holocircle));
        }
        if(size == 1){
            imageView_pin_two.setImageDrawable(getResources().getDrawable(R.drawable.holocircle));
        }
        if(size == 2) {
            imageView_pin_three.setImageDrawable(getResources().getDrawable(R.drawable.holocircle));
        }
        if(size == 3){
            imageView_pin_four.setImageDrawable(getResources().getDrawable(R.drawable.holocircle));
        }
    }

    public void onClickZeroButton(View view) {
        fillPin(getString(R.string.zero_string));
    }

    public void onClickDotButton(View view) {
        fillPin(getString(R.string.dot_string));
    }
}
