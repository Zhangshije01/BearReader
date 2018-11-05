package com.llx.bear.model.resultBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author yuyh.
 * @date 2016/11/18.
 */
public class BookMark implements Parcelable {

    public int chapter;

    public String title;

    public int startPos;

    public int endPos;

    public String desc = "";

    protected BookMark(Parcel in) {
        chapter = in.readInt();
        title = in.readString();
        startPos = in.readInt();
        endPos = in.readInt();
        desc = in.readString();
    }

    public static final Creator<BookMark> CREATOR = new Creator<BookMark>() {
        @Override
        public BookMark createFromParcel(Parcel in) {
            return new BookMark(in);
        }

        @Override
        public BookMark[] newArray(int size) {
            return new BookMark[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(chapter);
        dest.writeString(title);
        dest.writeInt(startPos);
        dest.writeInt(endPos);
        dest.writeString(desc);
    }
}
