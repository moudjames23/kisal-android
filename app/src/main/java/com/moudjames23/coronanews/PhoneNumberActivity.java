package com.moudjames23.coronanews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moudjames23.coronanews.app.Constant;
import com.moudjames23.coronanews.app.Pref;
import com.moudjames23.coronanews.model.Verification;
import com.moudjames23.coronanews.networkapi.NetworkCallBack;
import com.moudjames23.coronanews.networkapi.WebService;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneNumberActivity extends AppCompatActivity implements NetworkCallBack<Verification> {

    private EditText edtPhoneNumber;
    private Button btnNext;

    private String number;

    private ProgressDialog dialog;

    private final String TAG = "PhoneNumberTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);


        ButterKnife.bind(this);

        dialog = new ProgressDialog(this);
        dialog.setTitle(getString(R.string.dialog_verification_title));
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        edtPhoneNumber = (EditText)findViewById(R.id.edt_phone_number);
        btnNext = (Button)findViewById(R.id.btn_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                number = edtPhoneNumber.getText().toString().trim();

                if(number.isEmpty())
                    Toast.makeText(PhoneNumberActivity.this, getString(R.string.message_put_phone_number), Toast.LENGTH_SHORT).show();
                else if(number.length() != 9)
                    Toast.makeText(PhoneNumberActivity.this, getString(R.string.message_error_phone_number), Toast.LENGTH_SHORT).show();
                else
                {
                    sendPhoneNumber(number);
                }
            }
        });

    }

    private void sendPhoneNumber(String phone)
    {
        WebService webService = new WebService(this, this);
        webService.sendPhoneNumber(phone);

    }

    @OnClick(R.id.btn_ignorer)
    public void ignorerClick(View view)
    {
        new Pref(this).updateKey(Constant.FIRST_LAUNCH, false);
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showLoading() {
        Log.d(TAG, "show: ");
        dialog.show();
    }

    @Override
    public void hideLoading() {
        Log.d(TAG, "hide: ");
        dialog.dismiss();
    }

    @Override
    public void noInternetConnexion() {
        Log.d(TAG, "noInternetConnexion: ");
        Toast.makeText(PhoneNumberActivity.this, getString(R.string.no_internet_connexion), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noItem() {

    }

    @Override
    public void onFailure(String message) {
        Log.d(TAG, "onFailure: " +message);
    }

    @Override
    public void onResponse(Verification verification) {

        Log.d(TAG, "onResponse: ");
        Intent next = new Intent(this, CheckCodeActivity.class);
        next.putExtra(Constant.PHONE_NUMBER, number);
        next.putExtra(Constant.CODE_CONFIRMATION, verification.getCode());

        startActivity(next);
    }

    @Override
    public void onResponse(List<Verification> t) {

    }
}
