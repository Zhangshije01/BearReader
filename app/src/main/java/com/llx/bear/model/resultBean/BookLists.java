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
 * Created by lfh on 2016/8/15.
 */
public class BookLists extends Base {

    /**
     * _id : 57a83783c9b799011623ecff
     * title : 【追书盘点】男频类型文（六）体育类竞技文
     * author : 追书白小生
     * desc : 在体育竞技的赛场上！
     运动员们，因为一个坚定的信念，而不断前行，努力，付出。
     他们的目标只有一个：升级！
     当冠军，收小弟，在体育的大道上，走向人生的巅峰！

     本次就让我们来盘点一下，那些正值火热的体育类竞技文吧。
     【排名不分先后】
     * gender : male
     * collectorCount : 2713
     * cover : /agent/http://image.cmfu.com/books/3623405/3623405.jpg
     * bookCount : 20
     */

    public List<BookListsBean> bookLists;

    protected BookLists(Parcel in) {
        super(in);
    }

    public class BookListsBean implements Parcelable {
        public String _id;
        public String title;
        public String author;
        public String desc;
        public String gender;
        public int collectorCount;
        public String cover;
        public int bookCount;

        protected BookListsBean(Parcel in) {
            _id = in.readString();
            title = in.readString();
            author = in.readString();
            desc = in.readString();
            gender = in.readString();
            collectorCount = in.readInt();
            cover = in.readString();
            bookCount = in.readInt();
        }

        public final Creator<BookListsBean> CREATOR = new Creator<BookListsBean>() {
            @Override
            public BookListsBean createFromParcel(Parcel in) {
                return new BookListsBean(in);
            }

            @Override
            public BookListsBean[] newArray(int size) {
                return new BookListsBean[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_id);
            dest.writeString(title);
            dest.writeString(author);
            dest.writeString(desc);
            dest.writeString(gender);
            dest.writeInt(collectorCount);
            dest.writeString(cover);
            dest.writeInt(bookCount);
        }
    }
}
