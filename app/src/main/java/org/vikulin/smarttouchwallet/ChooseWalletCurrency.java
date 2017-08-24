package org.vikulin.smarttouchwallet;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

/**
 * Created by vadym on 20.08.17.
 */

public class ChooseWalletCurrency extends MainActivity {

    public static final String CURRENCY = "currency";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // landscape TODO
            //setContentView(R.layout.activity_changer_h);
        } else {
            // portrait
            setContentView(R.layout.activity_changer_w);
        }
    }

    public void onClick(View view) {
        String currency = view.getTag().toString();
        Intent result = new Intent();
        result.putExtra(CURRENCY, currency);
        setResult(RESULT_OK, result);
        finish();
    }
}
