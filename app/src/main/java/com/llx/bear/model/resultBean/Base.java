/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.llx.bear.model.resultBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author yuyh.
 * @date 2016/8/4.
 */
public class Base implements Parcelable {

    public boolean ok;

    protected Base(Parcel in) {
        ok = in.readByte() != 0;
    }

    public static final Creator<Base> CREATOR = new Creator<Base>() {
        @Override
        public Base createFromParcel(Parcel in) {
            return new Base(in);
        }

        @Override
        public Base[] newArray(int size) {
            return new Base[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (ok ? 1 : 0));
    }
}
