import com.tjl.jdbc.JDBC1;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class clientOut {
    public void showAccept(@NotNull String choice){
        Scanner sc=new Scanner(System.in);

        switch(choice){
            case "1":
                 JDBC1.creatAccount();
                 userIn.stepIn();
                break;

            case "2":
                boolean m=JDBC1.find();
                if(m==true){
                    userIn.stepIn();
                }else
                    System.out.println("登录失败，请检查用户名和密码是否正确");
                break;

            case "0":
                System.out.println("退出，欢迎下次再来");
                break;
            default:
                System.out.println("你输入的好像不太符合要求啊");
//                throw new IllegalStateException("Unexpected value: " + choice);

        }

    }


    public static void main(String[] args) {

    }
}




