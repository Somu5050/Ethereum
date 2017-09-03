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

public class SendActivity extends AppCompatActivity {

    private TextView textView_amount_eth, textView_amount_usd;
    private String ETH_string = "";
    private String USD_string = "";
    private ToastMessage toastMessage;
    private ArrayList<String>  myETH = new ArrayList<>();
    private int dotPressed = 0;
    private View dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        initViews();
    }

    private void initViews() {
        ImageView imageView_back_button = (ImageView) findViewById(R.id.iv_back_button);
        TextView textView_toolbar_title = (TextView) findViewById(R.id.tv_toolbar_title);
        imageView_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.navigateUpFromSameTask(SendActivity.this);
            }
        });
        textView_toolbar_title.setText(R.string.send_toolbar_title);
        textView_amount_eth = (TextView) findViewById(R.id.tv_amount_eth);
        textView_amount_usd = (TextView) findViewById(R.id.tv_amount_usd);
        Toast toast = new Toast(getApplicationContext());
        toastMessage = new ToastMessage(getApplicationContext(), toast);
    }

    public void goToHome(View view) {
        if(!myETH.isEmpty()){
            Intent intentForEnterPinActivity = new Intent(getApplicationContext(), EnterPinActivity.class);
            startActivity(intentForEnterPinActivity);
        } else {
            toastMessage.displayToast(getString(R.string.provide_amount_toast_string));
        }
    }

    public void onClickOneButton(View view) {
        setupETHText(getString(R.string.one_string));
    }

    public void onClickTwoButton(View view) {
        setupETHText(getString(R.string.two_string));
    }

    public void onClickThreeButton(View view) {
        setupETHText(getString(R.string.three_string));
    }

    public void onClickFourButton(View view) {
        setupETHText(getString(R.string.four_string));
    }

    public void onClickFiveButton(View view) {
        setupETHText(getString(R.string.five_string));
    }

    public void onClickSixButton(View view) {
        setupETHText(getString(R.string.six_string));
    }

    public void onClickSevenButton(View view) {
        setupETHText(getString(R.string.seven_string));
    }

    public void onClickEightButton(View view) {
        setupETHText(getString(R.string.eight_string));
    }

    public void onClickNineButton(View view) {
        setupETHText(getString(R.string.nine_string));
    }

    public void onClickBackSpaceButton(View view) {
        String stringOfETH = getString(R.string.blank_string);
        for (String s : myETH)
        {
            stringOfETH += s + "\t";
        }

        if(!stringOfETH.isEmpty()){
            ETH_string = getString(R.string.blank_string);
            USD_string = getString(R.string.blank_string);
            textView_amount_eth.setText(ETH_string);
            textView_amount_usd.setText(USD_string);
            myETH = new ArrayList<>();
            dotPressed = 0;
            if(dotView != null){
                dotView.setClickable(true);
            }

        } else {
            toastMessage.displayToast(getString(R.string.enter_amount_toast_string));
        }
    }

    public void onClickZeroButton(View view) {
        setupETHText(getString(R.string.zero_string));
    }

    public void onClickDotButton(View view) {
        dotView = view;

        if(ETH_string.equals(getString(R.string.blank_string))){
            toastMessage.displayToast(getString(R.string.invalid_amount_toast_string));
        } else {
            dotView.setClickable(true);
            dotPressed = dotPressed + 1;
            if (dotPressed == 1){
                setupETHText(getString(R.string.dot_string));
            } else if(dotPressed > 1){
                dotView.setClickable(false);
            } else if(dotPressed == 0){
                dotView.setClickable(true);
            }
        }
    }

    public void setupETHText(String i){

        ETH_string = ETH_string.concat(i.concat(" "));

        if(ETH_string.length() <= 15){
            myETH.add(i);
            String ETH_CONSTANT_STRING = " E T H";
            textView_amount_eth.setText(ETH_string.concat(ETH_CONSTANT_STRING));
        } else {
            toastMessage.displayToast(getString(R.string.max_limit_toast_string));
        }

        if (isNumeric(i)){
            StringBuilder sb = new StringBuilder();
            for (String s : myETH)
            {
                sb.append(s);
                double ETH = Double.parseDouble(sb.toString());
                convertETHToUSD(ETH);
            }
        }
    }

    public static boolean isNumeric(String str){
        try {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void convertETHToUSD(double l) {
        double USD = l * 337.96102369;
        USD_string = String.valueOf(USD);
        String EQUAL_CONSTANT_STRING = "= ";
        String USD_CONSTANT_STRING = " E T H";
        textView_amount_usd.setText(EQUAL_CONSTANT_STRING.concat(USD_string.concat(USD_CONSTANT_STRING)));
    }
}
