package com.softteq.testappsoftteq.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anray on 11.08.2016.
 */
public class Users {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("address")
    @Expose
    public Address address;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("company")
    @Expose
    public Company company;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public class Address {

        @SerializedName("street")
        @Expose
        public String street;
        @SerializedName("suite")
        @Expose
        public String suite;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("zipcode")
        @Expose
        public String zipcode;
        @SerializedName("geo")
        @Expose
        public Geo geo;

        public Geo getGeo() {
            return geo;
        }

        public String getCity() {
            return city;
        }

        public class Geo {

            @SerializedName("lat")
            @Expose
            public String lat;
            @SerializedName("lng")
            @Expose
            public String lng;

            public String getLat() {
                return lat;
            }

            public String getLng() {
                return lng;
            }
        }
    }



    public class Company {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("catchPhrase")
        @Expose
        public String catchPhrase;
        @SerializedName("bs")
        @Expose
        public String bs;

    }

}
