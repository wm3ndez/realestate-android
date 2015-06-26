package com.wmendez.realestate.utils;

import android.content.Context;
import android.widget.TextView;

import com.wmendez.realestate.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Formatter {

    public static String currency(float value) {
        return String.format("$%.0f", value);
    }


    public static void setMoney(Context context, TextView v, double amount) {
        setMoney(context, v, amount, true, true);
    }

    public static void setMoney(Context context, TextView v, double amount, boolean currency, boolean trailingZeros) {
        if (amount < 0)
            v.setTextColor(context.getResources().getColor(R.color.red_500_primary));
        else if (amount > 0)
            v.setTextColor(context.getResources().getColor(R.color.green_500));

        if (currency) {
            v.setText(moneyFormat(amount, trailingZeros));
        } else {
            v.setText(formatNumber(amount, trailingZeros));
        }
    }

    public static String moneyFormat(double number, boolean trailingZeros) {
        DecimalFormat nf = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
        String pattern;
        if (trailingZeros) pattern = "$#,##0.00;-$#,##0.00";
        else pattern = "$#,##0;-$#,##0";

        nf.applyPattern(pattern);
        return nf.format(number);

    }

    public static String formatNumber(double number, boolean trailingZeros) {
        if (trailingZeros)
            return new DecimalFormat("#,##0.00").format(number);
        return new DecimalFormat("#,##0").format(number);
    }

}
