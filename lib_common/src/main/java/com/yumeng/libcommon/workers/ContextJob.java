package com.yumeng.libcommon.workers;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.WorkerParameters;

import com.yumeng.libcommon.workers.requirements.ContextDependent;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class ContextJob extends Job implements ContextDependent {

    protected transient Context context;

    public ContextJob(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    protected ContextJob(@NonNull Context context, @NonNull JobParameters parameters) {
        super(context, parameters);
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    //在|json|中输入|键|->|值|映射。
    protected void jsonPut(JSONObject json, String key, Object value) {
        try {
            if (value != null)
                json.put(key, value);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}