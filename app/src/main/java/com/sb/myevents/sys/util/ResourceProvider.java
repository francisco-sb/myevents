package com.sb.myevents.sys.util;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.util
 * My Events
 */
public class ResourceProvider {

    private Context context;

    @Inject
    public ResourceProvider(Context context) {
        this.context = context;
    }

    public String getString(int id) {
        return context.getString(id);
    }

    public int getColor(int id) {
        return context.getResources().getColor(id);
    }
}
