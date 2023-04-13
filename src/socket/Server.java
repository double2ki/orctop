package socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server extends JFrame implements ActionListener {
   //定义按钮组件，文本组件，消息组件
    private JButton jButton;
    private JTextArea jTextArea=null;
    private JTextField jtfMessage;
  //  private String chatServer;
    //链接
    private Socket socket;
    //初始化输入输出（也许是这个备注
    private ObjectOutputStream out;
    private ObjectInputStream in;

    JTextArea jtaRecord=new JTextArea();
    String msg=new String();

    //定义ServerSocket对象
    private ServerSocket serverSocket=null;
    Socket getClients[]=new Socket[100];


  public Server(){
        //标题
        super("服务端1");
        //聊天窗口大小
        setSize(400,320);
        //设置固定位置
        setLocation(100,100);
        //设置一个容器
        Container container=getContentPane();
        JPanel jPanel=new JPanel();
        JPanel jPanel2=new JPanel();
        //设置布局方式
        container.add(jPanel,BorderLayout.NORTH);
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel.setBounds(12,12,45,45);
        jPanel2.add(new Label("聊天内容"));
        jPanel.add(jPanel2);


        jtaRecord=new JTextArea(12,30);
        //设置不可编辑
        jtaRecord.setEditable(false);
        jtaRecord.setLineWrap(true);
        //把文本域添加到面板上
        jPanel.add(jtaRecord);
        //组件
        JScrollPane scrollPane=new JScrollPane(jtaRecord);
        //设置宽高
        scrollPane.setSize(380,200);
        jPanel.add(scrollPane);

        JPanel jPanel1=new JPanel();
        //设置布局
        jPanel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(jPanel1,BorderLayout.SOUTH);
        //组件 消息框 按钮
        jPanel1.add(new JLabel("消息"));
        jtfMessage=new JTextField(25);
        jPanel1.add(jtfMessage);
        jButton=new JButton("发送");
        jPanel1.add(jButton);
        //添加点击时间，实现一个监听对象
        jButton.addActionListener(this);
        //设置让控件显示
        setVisible(true);
        //设置程序关闭方式 （点击×即可退出）
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }




    public void getConnection() throws IOException, InterruptedException {
        serverSocket=new ServerSocket(15483);

        //接受请求，建立连接
        displayMessage("正在监听...");

            //建立连接
            Socket socket_c=serverSocket.accept();
            displayMessage("正在尝试连接...");
           // Socket socket = getClients[i] = socket_c;
            displayMessage("服务器已经连接到客户端,"+"客户端为"+socket_c.getLocalPort());
            Thread.sleep(1000);

    }
    private void displayMessage(final  String msg){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               if(jTextArea!=null){
                   jTextArea.append(msg);
                   jTextArea.setCaretPosition(jTextArea.getText().length());
               }else System.out.println("你输入的文本为空！");
            }
        });
    }

    public void getStream()throws  IOException{
        //输入流
        in=new ObjectInputStream(serverSocket.accept().getInputStream());
        //输出流
        out=new ObjectOutputStream(serverSocket.accept().getOutputStream());

    }

    private void communication(){
        //设置框，使其能写字
        setTextFieldEditable(true);
        do{
            try{

            }catch (Exception e){
                displayMessage("1");
            }

        }while(!msg.equals("服务器：stop"));
    }

    //设置文本框状态
    private void setTextFieldEditable(final  boolean b){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jtfMessage.setEditable(b);
            }
        });
    }

    public void runServer() throws IOException {
        try{
            //链接客户端
            getConnection();
            //读取流信息
            getStream();
            //连接成功进行聊天
            communication();
            //关闭资源
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭资源
            closeConnection();
        }
    }

    private void closeConnection()throws IOException{
        //out in socket
        System.out.println("关闭链接");
        if(out!=null){
            out.close();
        }else System.out.println("out.close出错");
        in.close();
       if(socket==null){
           socket.close();
       }else socket.close();
    }

    //发信息
    public void sendData(String msg){
        try{
            out.writeObject("服务端沙雕"+msg);
            out.flush();
            displayMessage("\n服务端"+msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        }.runServer();

    }

    public void actionPerformed(ActionEvent event){
        sendData(jtfMessage.getText());
        String actionCommand=event.getActionCommand();
        switch (actionCommand){
            case "发送":
                jtfMessage.setText("");
                break;
        }
    }












}
