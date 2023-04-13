package socketNew;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class server {
    //创建监听线程，监听客户端的连接，为每一个连接的用户端开启一个处理收发信息的线程
    //在每个客户端的收发线程中接受每个客户端发回的信息，并对其进行转发到相应接受的客户端上
    ///添加处理传输文件的判断，通过在传输的字节数组中添加标志位来区分传输的是文本信息还是文件

        public static void main(String[] args) {
            ServerSocket ss = null;
            Socket s = null;

            // 定义一个List列表来保存每个客户端，每新建一个客户端连接，就添加到List列表里。
            List<Socket> listSocket = new ArrayList<>();
            try {
                // 1. 创建ServerSocket类型的对象并提供端口号
                ss = new ServerSocket(12456);
                // 2. 等待客户端的连接请求，调用accept方法,采用多线程的方式，允许多个用户请求连接。
                int i = 0;
                while(true){

                        System.out.println("等待客户端的连接请求...");
                        s = ss.accept();
                        listSocket.add(s);

                        i++;
                        System.out.printf("欢迎用户%d加入群聊!\n", i);
                        System.out.printf("目前群聊中共有%d人\n", listSocket.size());
                        InetAddress inetAddress = s.getInetAddress();
                        System.out.println("客户端" + inetAddress + "连接成功!");
                        // 调用多线程方法，每一个连上的客户端，服务器都有一个线程为之服务
                        new serverThread(s, inetAddress, listSocket).run();

                }


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭流
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }

//}
