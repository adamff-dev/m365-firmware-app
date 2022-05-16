package com.example.m365.model;

import android.text.InputFilter;
import android.text.Spanned;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloatFilter implements InputFilter {
    private final float min;
    private final float max;
    private int decimals;
    Pattern pattern;

    public FloatFilter(float min, float max, int decimals) {
        this.min = min;
        this.max = max;
        this.pattern = Pattern.compile("[0-9]*(\\.[0-9]{0," + (decimals - 1) + "})?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        // Check matcher
        Matcher matcher = pattern.matcher(dest);
        if (!matcher.matches()) return "";

        try {
            float input = Float.parseFloat(dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length()));
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(float a, float b, float c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}