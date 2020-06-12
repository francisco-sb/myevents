package com.sb.myevents.sys.util;

import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sb on 12/06/2020
 * com.sb.myevents.sys.util
 * My Events
 */
public class ReactiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = ReactiveEvent.class.getSimpleName();

    private final AtomicBoolean isPending = new AtomicBoolean(false);

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer observer) {

        if (this.hasActiveObservers())
            Log.w(TAG, "Multiple observers registered but only one will be dispatched!");

        super.observe(owner, t -> {
            if (isPending.compareAndSet(true, false))
                observer.onChanged(t);
        });
    }

    @MainThread
    public void setValue(T value) {
        isPending.set(true);
        super.setValue(value);
    }

    @MainThread
    public void call() {
        setValue(null);
    }

    public void callOnThread() {
        super.postValue(null);
    }
}
