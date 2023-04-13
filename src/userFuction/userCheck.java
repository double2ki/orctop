package userFuction;

/*
* userName:用户名
* userPassword:密码
* userLoginIn:手机号
* */
public class userCheck {

    private String userName;
    private String userPassword;
    private String userLoginIn;

    public userCheck(String userName, String userPassword, String userLoginIn) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userLoginIn = userLoginIn;
    }

    public userCheck() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserLoginIn() {
        return userLoginIn;
    }

    public void setUserLoginIn(String userLoginIn) {
        this.userLoginIn = userLoginIn;
    }
}
