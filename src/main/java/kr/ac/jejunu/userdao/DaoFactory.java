package kr.ac.jejunu.userdao;

/**
 * Created by jhkang on 4/22/16.
 */
public class DaoFactory {

    public UserDao getUserDao() {
        return new UserDao(new JejuConnectionMaker());
    }
}
