package com.util;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.google.gson.Gson;
import com.model.Account;
import com.model.Product;
import com.model.SubCategory;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Helper {
    public static void saveObjectToSharedPreference(Context context, String preferenceFileName, String serializedObjectKey, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, MODE_PRIVATE);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(object);
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject);
        sharedPreferencesEditor.apply();
    }

    public static <GenericClass> GenericClass getSavedObjectFromPreference(Context context, String preferenceFileName, String preferenceKey, Class<GenericClass> classType) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, MODE_PRIVATE);
        if (sharedPreferences.contains(preferenceKey)) {
            final Gson gson = new Gson();
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType);
        }
        return null;
    }

    public static void loadLocale(Context context, String preferenceFileName, String serializedObjectKey) {
        SharedPreferences prefs = context.getSharedPreferences(preferenceFileName, Activity.MODE_PRIVATE);
        String language = prefs.getString(serializedObjectKey, "");
        setLocale(context, preferenceFileName, serializedObjectKey, language);
    }

    public static void setLocale(Context context, String preferenceFileName, String serializedObjectKey, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        // save data to shared prences
        SharedPreferences.Editor editor = context.getSharedPreferences(preferenceFileName, MODE_PRIVATE).edit();
        editor.putString(serializedObjectKey, lang);
        editor.apply();
    }

    public static boolean isCheckUnique(List<Account> lists, String mobile) {
        for (Account a : lists) {
            if (a.getMobile().equalsIgnoreCase(mobile)) {
                return false;
            }
        }
        return true;
    }

    public List<Product> getListProductByIndex(List<Product> getAll, int index) {
        List<Product> lists = new ArrayList<>();
        for (int i = 0; i < getAll.size(); i++) {
            if (index == 1 && i % 2 == 0) { // case even
                lists.add(getAll.get(i));
            } else if (index == 2 && i % 2 != 0) { // case odd
                lists.add(getAll.get(i));
            }
        }
        return lists;
    }

    public List<SubCategory> getListSubCateByIndex(List<SubCategory> getAll, int index) {
        List<SubCategory> lists = new ArrayList<>();
        for (int i = 0; i < getAll.size(); i++) {
            if (index == 1 && i % 2 == 0) {
                lists.add(getAll.get(i));
            } else if (index == 2 && i % 2 != 0) {
                lists.add(getAll.get(i));
            }
        }
        return lists;
    }

    public static String formatNumber(int number) {
        if (Locale.getDefault().toString().equalsIgnoreCase("vi")) {
            return NumberFormat.getNumberInstance(Locale.getDefault()).format(number) + " Đ";
        } else if (Locale.getDefault().toString().equalsIgnoreCase("en")) {
            return "$ " + NumberFormat.getNumberInstance(Locale.getDefault()).format(number / 23);
        }
        return "$ " + NumberFormat.getNumberInstance(Locale.getDefault()).format(number / 23);
    }

    public List<String> getImageSubCateByIndex(List<String> getAll, int index) {
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < getAll.size(); i++) {
            if (index == 1 && i % 2 == 0) {
                lists.add(getAll.get(i));
            } else if (index == 2 && i % 2 != 0) {
                lists.add(getAll.get(i));
            }
        }
        return lists;
    }
}
