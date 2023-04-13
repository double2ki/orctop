package userFuction;

public class userUse {
    public static void main(String[] args) {
        userCheck userCheck=new userCheck();
        userCheck.getUserName();
        userCheck.getUserPassword();
        userCheck.getUserLoginIn();
        System.out.println(userCheck.getUserName());
    }
}
