import com.tjl.jdbc.JDBC1;

import java.util.Scanner;

public class userIn {

    public static String show1(){
        clientOut client1=new clientOut();
        Scanner sc=new Scanner(System.in);
        Scanner sc1 = sc;
        String choice= sc1.nextLine();
        switch(choice){
            case "1":
                System.out.println("注册");
                client1.showAccept("1");
                break;
            case "2":
                System.out.println("登录");
                client1.showAccept("2");
                break;
            case "0":
                 System.out.println("退出");
                client1.showAccept("0");
                break;
            default:
                System.out.println("输入内容错误，请按要求输入,现为您结束程序");
                client1.showAccept("4");
        }
        return choice;
    }

    public static void welcome(){
        System.out.println("欢迎来到top的聊天平台！");
        System.out.println("在使用这个平台前，请选择1注册或者2登录，也可以选择0退出");
    }
    //跳转页面到主页面，整合选项
    public static void stepIn(){
        System.out.println("欢迎进入top聊天平台！");
        System.out.println("请输入你的选择：1/2/3/4/5/6/7/0");
        System.out.println("1为查询用户信息，你可以查找到任何用户的信息包括你自己");
        System.out.println("2为修改用户信息，你可以修改你的信息（注意：用户名不可修改）");
        System.out.println("3为在平台上聊天，你可以在平台上和大家聊天");
        System.out.println("4为指定平台上的用户进行聊天，你可以进行私聊");
        System.out.println("5 你可以看到哪些用户在线");
        System.out.println("6 你可以接收到其他用户上线和下线的讯息");
        System.out.println("7 如果你忘记了你的密码，可以通过这个按钮找回");
        System.out.println("0 你可以选择退出这个聊天平台啦哈哈");

        Scanner sc=new Scanner(System.in);
        Scanner sc1 = sc;
        String choice= sc1.nextLine();
        switch(choice){
            case "1":
                JDBC1.userLocalMessageSearch();
                break;
            case "2":
                JDBC1.userLocalMessageChange();
                break;
            case "3":
                System.out.println("好，现在你可以在平台上聊天啦！");

                break;
            case "4":
                System.out.println("你可以进行私聊了");
                break;
            case "5":
                System.out.println("你可以看到哪些用户在线");
                break;
            case "6":
                System.out.println("你可以接收到其他用户上线和下线的讯息");
                break;
            case "7":
                System.out.println("忘记密码了？没关系，我不会嘲笑你，但是如果你忘记了自己的昵称我会狠狠笑你");
                System.out.println("下面给你找回密码，你的原有密码为：123456，如果你不想要这个密码了，那么选择1(得到密码，就此离开)/0(来都来了我就要改密码)告诉我你的选择");
                break;
            case "0":
                System.out.println("好吧，现在退出这个程序");
                break;
            default:
                System.out.println("输入内容错误，请按照要求输入！");
                break;

        }

    }




}
