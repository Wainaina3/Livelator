
package com.ibm.mobileappbuilder.livelator20161017172308.ds;
import android.graphics.Bitmap;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class UserDbDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("username") public String username;
    @SerializedName("password") public String password;
    @SerializedName("image") public String image;
    @SerializedName("id") public String id;
    @SerializedName("imageUri") public transient Uri imageUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(image);
        dest.writeString(id);
    }

    public static final Creator<UserDbDSItem> CREATOR = new Creator<UserDbDSItem>() {
        @Override
        public UserDbDSItem createFromParcel(Parcel in) {
            UserDbDSItem item = new UserDbDSItem();

            item.username = in.readString();
            item.password = in.readString();
            item.image = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public UserDbDSItem[] newArray(int size) {
            return new UserDbDSItem[size];
        }
    };

}

