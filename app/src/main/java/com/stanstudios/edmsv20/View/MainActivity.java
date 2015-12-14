package com.stanstudios.edmsv20.View;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.stanstudios.edmsv20.R;
import com.stanstudios.edmsv20.View.RecycleView.Products;
import com.stanstudios.edmsv20.View.RecycleView.RecyclerViewAdapter;

import java.util.ArrayList;

import CommonLib.Const;
import CommonLib.EventPool;
import CommonLib.EventType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String LOG_EDMS = "eDMS LifeCycle";
    private Toolbar toolbar;
    private EditText editName, editPass, editPassOld, editPassNew, editPassNewAgain;
    private TextInputLayout inputLayoutName, inputLayoutPassword, inputLayoutPasswordOld, inputLayoutPasswordNew, inputLayoutPasswordNewAgain;
    private LinearLayout linearLogin, linearChangePass, linearListOrder;
    private TextView txtTitle;
    private RecyclerView recyclerProducts;
    private ArrayList<Products> productsArrayList = new ArrayList<>();
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getId();
        event();
    }

    private void event() {
        setSupportActionBar(toolbar);
        ((Button) findViewById(R.id.btn_signin)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_changepass)).setOnClickListener(this);
        editName.addTextChangedListener(new MyTextWatcher(editName));
        editPass.addTextChangedListener(new MyTextWatcher(editPass));
        editPassOld.addTextChangedListener(new MyTextWatcher(editPassOld));
        editPassNew.addTextChangedListener(new MyTextWatcher(editPassNew));
        editPassNewAgain.addTextChangedListener(new MyTextWatcher(editPassNewAgain));
        startService(new Intent(this, BackgroundService.class));
    }

    private void getId() {
        editPassNew = (EditText) findViewById(R.id.input_password_new);
        editPassOld = (EditText) findViewById(R.id.input_password_old);
        editPassNewAgain = (EditText) findViewById(R.id.input_password_new_again);
        inputLayoutPasswordOld = (TextInputLayout) findViewById(R.id.input_layout_password_old);
        inputLayoutPasswordNew = (TextInputLayout) findViewById(R.id.input_layout_password_new);
        inputLayoutPasswordNewAgain = (TextInputLayout) findViewById(R.id.input_layout_password_new_again);
        editName = (EditText) findViewById(R.id.input_name);
        editPass = (EditText) findViewById(R.id.input_password);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        linearLogin = (LinearLayout) findViewById(R.id.linear_login);
        linearChangePass = (LinearLayout) findViewById(R.id.linear_change_pass);
        linearListOrder = (LinearLayout) findViewById(R.id.linear_list_order);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        recyclerProducts = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerProducts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(MainActivity.this, productsArrayList);
        recyclerProducts.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin:
                //XU LY DANG NHAP
                submitFormLogin();
                break;
            case R.id.btn_changepass:
                //XU LY DOI PASS
                submitFormChange();
                break;
            default:
                break;
        }
    }

    private void submitFormLogin() {
        if (!validateName()) {
            return;
        }


        if (!validatePassword()) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        Products products = new Products();
        products.setProductNo_("DH01");
        products.setProductName_("Đơn hàng của Lãng Tử Gió");
        productsArrayList.add(products);
        products.setProductNo_("DH02");
        products.setProductName_("Đơn hàng của Lãng Tử Gió 2");
        productsArrayList.add(products);
        products.setProductNo_("DH03");
        products.setProductName_("Đơn hàng của Lãng Tử Gió 3");
        productsArrayList.add(products);
        adapter.notifyDataSetChanged();
        showLayout(Layouts.ListOrder);

    }

    private void submitFormChange() {
        if (!validatePasswordOld()) {
            return;
        }


        if (!validatePasswordNew()) {
            return;
        }
        if (!validatePasswordNewAgain()) {
            return;
        }
        Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();

        showLayout(Layouts.LogIn);


    }

    private boolean validateName() {
        if (editName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(editName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validatePassword() {
        if (editPass.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(editPass);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePasswordOld() {
        if (editPassOld.getText().toString().trim().isEmpty()) {
            inputLayoutPasswordOld.setError(getString(R.string.err_msg_password_old));
            requestFocus(editPassOld);
            return false;
        } else {
            inputLayoutPasswordOld.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePasswordNew() {
        if (editPassNew.getText().toString().trim().isEmpty()) {
            inputLayoutPasswordNew.setError(getString(R.string.err_msg_password_new));
            requestFocus(editPassNew);
            return false;
        } else {
            inputLayoutPasswordNew.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePasswordNewAgain() {
        if (editPassNewAgain.getText().toString().trim().isEmpty()) {
            inputLayoutPasswordNewAgain.setError(getString(R.string.err_msg_password_new_again));
            requestFocus(editPassNewAgain);
            return false;
        } else {
            inputLayoutPasswordNewAgain.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_password:
                    validatePassword();
                    break;
                case R.id.input_password_old:
                    validatePasswordOld();
                    break;
                case R.id.input_password_new:
                    validatePasswordNew();
                    break;
                case R.id.input_password_new_again:
                    validatePasswordNewAgain();
                    break;
            }
        }
    }

    //LIFE CYCLE
    @Override
    protected void onStart() {
        Log.w(LOG_EDMS, "onStart begin");
        super.onStart();
        isRunning = true;
        handler.postDelayed(runnable, Const.QueueTimerView);
        Log.w(LOG_EDMS, "onStart end");
    }

    @Override
    protected void onResume() {
        Log.w(LOG_EDMS, "onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.w(LOG_EDMS, "onRestart");
        super.onRestart();
    }

    @Override
    protected void onPause() {
        Log.w(LOG_EDMS, "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.w(LOG_EDMS, "onStop begin");
        super.onStop();
        isRunning = false;
        Log.w(LOG_EDMS, "onStop end");
    }

    @Override
    protected void onDestroy() {
        Log.w(LOG_EDMS, "onDestroy begin");
        super.onDestroy();
        System.exit(0);
        Log.w(LOG_EDMS, "onDestroy end");
    }

    private boolean isRunning = false;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.v("QueueTimerView", "timedout");
            EventType.EventBase event = EventPool.view().deQueue();
            while (event != null) {
                processEvent(event);
                if (!isRunning) break;
                event = EventPool.view().deQueue();
            }
            if (isRunning) handler.postDelayed(this, Const.QueueTimerView);
        }
    };

    private enum Layouts {
        LogIn, ChangePass, ListOrder
    }

    private void showLayout(Layouts layout) {
        switch (layout) {
            case LogIn:
                //show hide layouts...
                linearLogin.setVisibility(View.VISIBLE);
                linearChangePass.setVisibility(View.GONE);
                linearListOrder.setVisibility(View.GONE);
                txtTitle.setText(getString(R.string.sign_in_title));
                break;
            case ChangePass:
                linearLogin.setVisibility(View.GONE);
                linearChangePass.setVisibility(View.VISIBLE);
                linearListOrder.setVisibility(View.GONE);
                txtTitle.setText(getString(R.string.change_pass_title));
                break;
            case ListOrder:
                //
                linearLogin.setVisibility(View.GONE);
                linearChangePass.setVisibility(View.GONE);
                linearListOrder.setVisibility(View.VISIBLE);
                txtTitle.setText(getString(R.string.listorder_title));
                break;
        }
    }

    private void processEvent(EventType.EventBase event) {
        switch (event.type) {
            case BEGIN:
                break;
            case END:
                break;
            default:
                Log.w("View_processEvent", "unhandled " + event.type);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (linearLogin.getVisibility() == View.VISIBLE) {

        }
        if (linearListOrder.getVisibility() == View.VISIBLE) {

        }
        if (linearChangePass.getVisibility() == View.VISIBLE) {

        }
    }
}
