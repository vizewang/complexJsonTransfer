package com.littleboss.app.jsonUtil;

/**
 * Created by PHPnew6 on 2016/5/11.
 */
public class Birthday {
    private String birthday;

//    public Birthday(String birthday) {
//        this.birthday = birthday;
//    }

    public Birthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
//getter、setter


    @Override
    public String toString() {
        return this.birthday;
    }
}
