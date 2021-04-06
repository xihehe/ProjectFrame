package com.yumeng.libbaseProject.dataSupport;

import android.text.Spannable;

import com.yumeng.aillo.core.listener.DataBindingSpan;
import com.yumeng.aillo.core.listener.DirtySpan;

import org.jetbrains.annotations.NotNull;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class User extends LitePalSupport implements DataBindingSpan, DirtySpan, Serializable {
    private String userId;
    private String userName;

    public User() {
    }

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean isDirty(@NotNull Spannable text) {
        int spanStart = text.getSpanStart(this);
        int spanEnd = text.getSpanEnd(this);
        return spanStart >= 0 && spanEnd >= 0 && !text.subSequence(spanStart, spanEnd).equals("@" + userName);
    }

}
