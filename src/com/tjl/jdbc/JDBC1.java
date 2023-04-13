package com.tjl.jdbc;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class JDBC1 {



    //登录，与数据库连接，然后在数据库里面找用户的信息是否存在
    public static boolean find(){
            //加载驱动mysql
        try{
            Scanner scanner=new Scanner(System.in);
            System.out.println("请输入你的账号");
            String userLoginIn=scanner.nextLine();
            System.out.println("请输入你的账号密码");
            String password=scanner.nextLine();
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库,然后获得连接对象
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermessage","root","Zwhswd1!");
            //创建执行环境,为了后面写sql语句用的
            Statement statement=conn.createStatement();//容器
            //执行sql语句，得到结果集
            ResultSet result =statement.executeQuery("select *from usermessage.user1 where userName='"+userLoginIn+"' and userPassword='"+password+"'");
            if(result.next()){
                System.out.println("登录成功！"+result.getString(1)+"欢迎你");
                return true;
            }else {
                System.out.println("输入账号或密码有误！");
                return false;
            }

        }catch(Exception e){
            //我扩大了异常范围
            System.out.println("驱动加载失败");
            System.out.println("数据库加载失败");
            return false;
        }
    }
    //创建账号，连接数据库，直接将用户输入的信息保存到数据库内
    public static void  creatAccount(){

        try{
            Statement stmt=null;
            Scanner scanner=new Scanner(System.in);
            System.out.println("请创建你的账号");
            String un=scanner.nextLine();
            System.out.println("请设置你的账号密码");
            String password=scanner.nextLine();
            System.out.println("请输入你的手机号");
            String userLoginIn=scanner.nextLine();
            if(un==null||password==null){
                System.out.println("输入的账号或密码为空，请你检验输入是否错误");
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库,然后获得连接对象
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermessage","root","Zwhswd1!");
            //创建执行环境,为了后面写sql语句用的
            Statement statement=conn.createStatement();//容器
            //执行sql语句，得到结果集
            //查询
            String sql="select *from usermessage.user1 where userName='"+un+"'";
            if(statement.executeQuery(sql).next()){
                System.out.println("该账号已经存在,你可以选择换个账号昵称");
            }
            stmt=conn.createStatement();
            String sql1="insert into usermessage.user1(userName,userpassword,userloginIn) values('"+un+"','"+password+"','"+userLoginIn+"')";
            if(conn.createStatement().executeUpdate(sql1)>=1){
                System.out.println("执行插入");
            }

        }catch(Exception e){
            //我扩大了异常范围
            e.printStackTrace();
            System.out.println("驱动加载失败");
            System.out.println("数据库加载失败");
        }

    }
    //用户信息查询
    public static void userLocalMessageSearch(){
        try{
            Scanner scanner=new Scanner(System.in);
            System.out.println("请输入您要查找的账号名称");
            String un=scanner.nextLine();


            if(un==null){
                System.out.println("输入的账号为空，请你检验输入是否错误");
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库,然后获得连接对象
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermessage","root","Zwhswd1!");
            //创建执行环境,为了后面写sql语句用的
            Statement statement=conn.createStatement();//容器
            //执行sql语句，得到结果集
            //查询
            ResultSet resultSet=statement.executeQuery("select *from usermessage.user1 where userName='"+un+"'");
            if(resultSet.next()){
                System.out.println("用户的账号为："+un);
                System.out.println("用户的密码为："+resultSet.getString(2));
                System.out.println("用户的手机号为:"+resultSet.getString(3));
            }else{
                System.out.println("不存在这样的用户名，你再检查一下吧！（温柔口吻）");
            }
        }catch(Exception e){
            //我扩大了异常范围
            e.printStackTrace();
            System.out.println("驱动加载失败");
            System.out.println("数据库加载失败");
        }

    }
    //用户信息修改
    public static void userLocalMessageChange(){
        try{
            Statement stmt=null;
            Scanner scanner=new Scanner(System.in);
            System.out.println("请输入你想修改的账号");
            String un=scanner.nextLine();
            System.out.println("请输入你想修改的账号密码");
            String password =scanner.nextLine();
            System.out.println("请输入你想修改的账号手机号");
            String userLoginIn=scanner.nextLine();

            if(un==null){
                System.out.println("输入的账号为空，请你检验输入是否错误");
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库,然后获得连接对象
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/usermessage","root","Zwhswd1!");
            //创建执行环境,为了后面写sql语句用的
            Statement statement=conn.createStatement();//容器
            //执行sql语句，得到结果集

            stmt=conn.createStatement();
            String sql="update usermessage.user1 set userPassword='"+password+"', userLoginIn='"+userLoginIn+"'where userName='"+un+"'";
            ResultSet resultSet=statement.executeQuery("select *from usermessage.user1 where userName='"+un+"'");
            if(conn.createStatement().executeUpdate(sql)>=1 && resultSet.next()){
                System.out.println("请别介意我先输出一下有关你的用户信息");
                System.out.println("用户的账号为："+un);
                System.out.println("用户的密码为："+resultSet.getString(2));
                System.out.println("用户的手机号为:"+resultSet.getString(3));
                System.out.println("用户"+un+"的信息修改成功！");
            }else System.out.println("不是吧你连自己的信息都不会改（暴躁）");

        }catch(Exception e){
            //我扩大了异常范围
            e.printStackTrace();
            System.out.println("驱动加载失败");
            System.out.println("数据库加载失败");
        }
    }


    public static void main(String[] args) {




    }



}
