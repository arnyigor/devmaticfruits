package com.devmatic.fruits.data.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

public class Config {

    private static SharedPreferences settings = null;

    private static SharedPreferences getSettings(Context context) {
        if(settings == null) {
            settings = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return settings;
    }

    public static SharedPreferences getSettings(Context context, String prefname) {
        if(settings == null) {
            settings =  context.getSharedPreferences(prefname, Context.MODE_PRIVATE);
        }
        return settings;
    }

    /**
     * Получение конфига по ключу
     * @param key
     * Ключ
     * @param context
     * Контекст
     * @return
     * Значение конфига
     */
    @Nullable
    public static String getString(String key, Context context) {
        settings = getSettings(context);
        return settings.getString(key, null);
    }

    @Nullable
    public static String getStringPref(String key, Context context, String prefname) {
        settings = getSettings(context, prefname);
        return settings.getString(key, null);
    }

    /**
     * Получение конфига по ключу
     * @param key
     * Ключ
     * @param context
     * Контекст
     * @param defaultVal
     * Значение по умолчанию
     * @return
     * Значение конфига
     */
    @Nullable
    public static String getString(String key, Context context,String defaultVal) {
        settings = getSettings(context);
        return settings.getString(key, defaultVal);
    }

    /**
     * Получение конфига по ключу
     * @param key
     * Ключ
     * @param context
     * Контекст
     * @return
     * Значение конфига
     */
    public static Integer getInt(String key, Context context) {
        return getInt(key, context, 0);
    }

    /**
     * Получение конфига по ключу
     * @param key
     * Ключ
     * @param context
     * Контекст
     * @param defaultVal значение по-умолчанию
     * @return
     * Значение конфига
     */
    public static Integer getInt(String key, Context context, int defaultVal) {
        settings = getSettings(context);
        return settings.getInt(key, defaultVal);
    }

    /**
     * Получение конфига по ключу
     *
     * @param key        Ключ
     * @param context    Контекст
     * @param defaultVal значение по-умолчанию
     * @return Значение конфига
     */
    public static long getLong(String key, Context context, long defaultVal) {
        settings = getSettings(context);
        return settings.getLong(key, defaultVal);
    }

    public static Integer getIntPref(String key, Context context, int defaultVal, String prefName) {
        settings = getSettings(context, prefName);
        return settings.getInt(key, defaultVal);
    }

    /**
     * Получение конфига по ключу
     * @param key
     * Ключ
     * @param context
     * Контекст
     * @return
     * Значение конфига
     */
    @Nullable
    public static boolean getBoolean(String key,boolean defaultVal, Context context) {
        settings = getSettings(context);
        return settings.getBoolean(key, defaultVal);
    }

    @Nullable
    public static boolean getBooleanPref(String key, boolean defaultVal, Context context, String pref) {
        settings = getSettings(context, pref);
        return settings.getBoolean(key, defaultVal);
    }

    /**
     * Установка конфига
     * @param key
     * Ключ
     * @param value
     * Значение
     * @param context
     * Текущий контекст
     */
    @Nullable
    public static void setString(String key, String value, Context context) {
        settings = getSettings(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    @Nullable
    public static void setStringPref(String key, String value, Context context, String prefName) {
        settings = getSettings(context, prefName);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Установка числового конфига
     * @param key
     * Ключ
     * @param value
     * Значение
     * @param context
     * Текущий контекст
     */
    public static void setBoolean(String key, boolean value, Context context) {
        settings = getSettings(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void setBooleanPref(String key, boolean value, Context context, String prefName) {
        settings = getSettings(context, prefName);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Установка числового конфига
     * @param key
     * Ключ
     * @param value
     * Значение
     * @param context
     * Текущий контекст
     */
    public static void setInt(String key, Integer value, Context context) {
        settings = getSettings(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Установка числового конфига
     *
     * @param key     Ключ
     * @param value   Значение
     * @param context Текущий контекст
     */
    public static void setLong(String key, Long value, Context context) {
        settings = getSettings(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static void setIntPref(String key, Integer value, Context context, String prefName) {
        settings = getSettings(context, prefName);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    /**
     * Удаление ключа из конфига
     * @param key
     * Ключ
     * @param context
     * Контекст
     */
    public static void remove(String key, Context context) {
        settings = getSettings(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removePref(String key, Context context, String prefName) {
        settings = getSettings(context, prefName);
        SharedPreferences.Editor editor = settings.edit();
        editor.remove(key);
        editor.apply();
    }

    public static boolean isDebugApi(Context context) {
        return Config.getBoolean("debug_api_requests", false, context);
    }
}