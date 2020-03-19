package com.moudjames23.coronanews.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;



/**
 * Created by Moudjames23 on 10/12/2016.
 */
public class Pref {

    private SharedPreferences shared;
    private SharedPreferences.Editor edit;

    public Pref(Context context)
    {
        shared = PreferenceManager.getDefaultSharedPreferences(context);
        edit = shared.edit();
    }

    public void updateKey(String key, Object object)
    {

        if(object instanceof Integer)
        {
            edit.putInt(key, (Integer) object);
        }
        else if(object instanceof String)
        {
            edit.putString(key, String.valueOf(object));
        }
        else
            edit.putBoolean(key, (Boolean)object);

        edit.commit();


    }

    public String getStringValue(String key)
    {
        return shared.getString(key, "");
    }

    public int getIntValue(String key)
    {
        return shared.getInt(key, 0);
    }

    public Boolean getBooleanValue(String key, Boolean defaultValue)
    {
        return shared.getBoolean(key, defaultValue);
    }


}
