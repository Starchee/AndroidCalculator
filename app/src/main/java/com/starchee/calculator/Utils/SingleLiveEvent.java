package com.starchee.calculator.Utils;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class SingleLiveEvent<T> extends MutableLiveData<T> {

    private final AtomicBoolean pending = new AtomicBoolean(false);


    @MainThread
    public void observe(LifecycleOwner owner, final Observer<? super T> observer) {

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T t) {
                if (pending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T t) {
        pending.set(true);
        super.setValue(t);
    }
}
