package com.example.m365.model;

import android.text.InputFilter;
import android.text.Spanned;

public class IntFilter implements InputFilter {
    private final int min;
    private final int max;

    public IntFilter(int minValue, int maxValue) {
        this.min = minValue;
        this.max = maxValue;
    }

    public IntFilter(String minValue, String maxValue) {
        this.min = Integer.parseInt(minValue);
        this.max = Integer.parseInt(maxValue);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}