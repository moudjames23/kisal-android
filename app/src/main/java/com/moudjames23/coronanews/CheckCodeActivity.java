package com.moudjames23.coronanews;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.moudjames23.coronanews.app.Constant;
import com.moudjames23.coronanews.app.Helper;
import com.moudjames23.coronanews.app.Pref;
import com.moudjames23.coronanews.model.ConfirmationResponse;
import com.moudjames23.coronanews.networkapi.NetworkCallBack;
import com.moudjames23.coronanews.networkapi.WebService;
import com.moudjames23.coronanews.views.PinEntryEditText;

import java.util.List;

public class CheckCodeActivity extends AppCompatActivity implements NetworkCallBack<ConfirmationResponse> {

    private PinEntryEditText edtCodeConfirmation;

    private Button btnResend;
    
    private String phone, code;

    private ProgressDialog dialog;

    private final String TAG = "CHECKCODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_code);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dialog = new ProgressDialog(this);
        dialog.setTitle(getString(R.string.dialog_verification_title));
        dialog.setMessage(getString(R.string.please_wait));
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        Helper.setupBackToolbar(toolbar, this);

        phone = getIntent().getStringExtra(Constant.PHONE_NUMBER);
        code = getIntent().getStringExtra(Constant.CODE_CONFIRMATION);

        Log.d(TAG, "onCreate: ");

        btnResend = (Button)findViewById(R.id.btn_resend);
        btnResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckCodeActivity.this, PhoneNumberActivity.class));
            }
        });
        
        edtCodeConfirmation = (PinEntryEditText)findViewById(R.id.edt_code_confirmation);
        edtCodeConfirmation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String codeInput = edtCodeConfirmation.getText().toString();

                if(codeInput.length() == 4)
                {
                    if(codeInput.equals(code))
                        checkConfirmation();
                    else
                        Toast.makeText(CheckCodeActivity.this, "Code incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    
    private void checkConfirmation()
    {
        WebService webService = new WebService(this, this);
        webService.confirmation(phone, code);
    }

    @Override
    public void showLoading() {
        dialog.show();
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void noInternetConnexion() {
        Toast.makeText(CheckCodeActivity.this, getString(R.string.no_internet_connexion), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noItem() {

    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(CheckCodeActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(ConfirmationResponse confirmationResponse) {
        if(confirmationResponse.isExpired())
        {
            Toast.makeText(CheckCodeActivity.this, getString(R.string.message_code_expired), Toast.LENGTH_SHORT).show();
        }
        else
        {
            new Pref(this).updateKey(Constant.FIRST_LAUNCH, false);
            startActivity(new Intent(this, MainActivity.class));
        }

       
    }

    @Override
    public void onResponse(List<ConfirmationResponse> t) {

    }
}
