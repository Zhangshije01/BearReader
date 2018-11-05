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

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/7.
 */
public class BookMixAToc extends Base {

    /**
     * _id:577e528e2160421a02d7380d
     * name:优质书源
     * link:http://vip.zhuishushenqi.com/toc/577e528e2160421a02d7380d
     */
    public mixToc mixToc;

    protected BookMixAToc(Parcel in) {
        super(in);
    }

    public static class mixToc implements Parcelable {
        public String _id;
        public String book;
        public String chaptersUpdated;
        /**
         * title : 第一章 死在万花丛中
         * link : http://vip.zhuishushenqi.com/chapter/577e5290260289ff64a29213?cv=1467896464908
         * id : 577e5290260289ff64a29213
         * currency : 15
         * unreadble : false
         * isVip : false
         */

        public List<Chapters> chapters;

        protected mixToc(Parcel in) {
            _id = in.readString();
            book = in.readString();
            chaptersUpdated = in.readString();
            chapters = in.createTypedArrayList(Chapters.CREATOR);
        }

        public static final Creator<mixToc> CREATOR = new Creator<mixToc>() {
            @Override
            public mixToc createFromParcel(Parcel in) {
                return new mixToc(in);
            }

            @Override
            public mixToc[] newArray(int size) {
                return new mixToc[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_id);
            dest.writeString(book);
            dest.writeString(chaptersUpdated);
            dest.writeTypedList(chapters);
        }

        public static class Chapters implements Parcelable {
            public String title;
            public String link;
            public String id;
            public int currency;
            public boolean unreadble;
            public boolean isVip;

            public Chapters() {
            }

            public Chapters(String title, String link) {
                this.title = title;
                this.link = link;
            }

            protected Chapters(Parcel in) {
                title = in.readString();
                link = in.readString();
                id = in.readString();
                currency = in.readInt();
                unreadble = in.readByte() != 0;
                isVip = in.readByte() != 0;
            }

            public static final Creator<Chapters> CREATOR = new Creator<Chapters>() {
                @Override
                public Chapters createFromParcel(Parcel in) {
                    return new Chapters(in);
                }

                @Override
                public Chapters[] newArray(int size) {
                    return new Chapters[size];
                }
            };

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(title);
                dest.writeString(link);
                dest.writeString(id);
                dest.writeInt(currency);
                dest.writeByte((byte) (unreadble ? 1 : 0));
                dest.writeByte((byte) (isVip ? 1 : 0));
            }
        }
    }

}
