package com.softteq.testappsoftteq.data.storage.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.softteq.testappsoftteq.network.response.Posts;
import com.softteq.testappsoftteq.network.response.Users;

/**
 * Created by anray on 11.08.2016.
 */
public class UserDTO implements Parcelable {


    private int mUserId;
    private int mPostId;
    private String mFullName;
    private String mNickName;
    private String mEmail;
    private String mWebSite;
    private String mPhone;
    private String mCity;
    private String mLatitude;
    private String mLongitude;

    public UserDTO(Posts post, Users user) {
        mUserId = post.getUserId();
        mPostId = post.getId(); //post id
        mFullName = user.getName(); //full name
        mNickName = user.getUsername();//nick
        mEmail = user.getEmail();
        mWebSite = user.getWebsite();
        mPhone = user.getPhone();
        mCity = user.getAddress().getCity();

        mLatitude = user.getAddress().getGeo().getLat();
        mLongitude = user.getAddress().getGeo().getLng();
    }

    protected UserDTO(Parcel in) {


        mUserId = in.readInt();
        mPostId = in.readInt();
        mFullName = in.readString();
        mNickName = in.readString();
        mEmail = in.readString();
        mWebSite = in.readString();
        mPhone = in.readString();
        mCity = in.readString();
        mLatitude = in.readString();
        mLongitude = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mUserId);
        dest.writeInt(mPostId);
        dest.writeString(mFullName);
        dest.writeString(mNickName);
        dest.writeString(mEmail);
        dest.writeString(mWebSite);
        dest.writeString(mPhone);
        dest.writeString(mCity);
        dest.writeString(mLatitude);
        dest.writeString(mLongitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserDTO> CREATOR = new Parcelable.Creator<UserDTO>() {
        @Override
        public UserDTO createFromParcel(Parcel in) {
            return new UserDTO(in);
        }

        @Override
        public UserDTO[] newArray(int size) {
            return new UserDTO[size];
        }
    };

    public int getUserId() {
        return mUserId;
    }

    public int getPostId() {
        return mPostId;
    }

    public String getFullName() {
        return mFullName;
    }

    public String getNickName() {
        return mNickName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getWebSite() {
        return mWebSite;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getCity() {
        return mCity;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public String getLongitude() {
        return mLongitude;
    }
}
