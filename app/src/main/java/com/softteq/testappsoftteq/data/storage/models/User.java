package com.softteq.testappsoftteq.data.storage.models;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by anray on 11.08.2016.
 */
@Entity(active = true, nameInDb = "USER")
public class User {


    @Id
    private Long id;

    @NotNull
    @Unique
    private int mUserId;


    private String mFullName;
    private String mNickName;
    private String mEmail;
    private String mWebSite;
    private String mPhone;
    private String mCity;
    private String mLatitude;
    private String mLongitude;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public User(UserDTO user) {

        mUserId = user.getUserId();

        mFullName = user.getFullName(); //full name
        mNickName = user.getNickName();//nick
        mEmail = user.getEmail();
        mWebSite = user.getWebSite();
        mPhone = user.getPhone();
        mCity = user.getCity();

        mLatitude = user.getLatitude();
        mLongitude = user.getLongitude();
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }

    public String getMLongitude() {
        return this.mLongitude;
    }

    public void setMLongitude(String mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getMLatitude() {
        return this.mLatitude;
    }

    public void setMLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getMCity() {
        return this.mCity;
    }

    public void setMCity(String mCity) {
        this.mCity = mCity;
    }

    public String getMPhone() {
        return this.mPhone;
    }

    public void setMPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getMWebSite() {
        return this.mWebSite;
    }

    public void setMWebSite(String mWebSite) {
        this.mWebSite = mWebSite;
    }

    public String getMEmail() {
        return this.mEmail;
    }

    public void setMEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getMNickName() {
        return this.mNickName;
    }

    public void setMNickName(String mNickName) {
        this.mNickName = mNickName;
    }

    public String getMFullName() {
        return this.mFullName;
    }

    public void setMFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public int getMUserId() {
        return this.mUserId;
    }

    public void setMUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 13572490)
    public User(Long id, int mUserId, String mFullName, String mNickName,
                String mEmail, String mWebSite, String mPhone, String mCity,
                String mLatitude, String mLongitude) {
        this.id = id;
        this.mUserId = mUserId;
        this.mFullName = mFullName;
        this.mNickName = mNickName;
        this.mEmail = mEmail;
        this.mWebSite = mWebSite;
        this.mPhone = mPhone;
        this.mCity = mCity;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    @Generated(hash = 586692638)
    public User() {
    }

}