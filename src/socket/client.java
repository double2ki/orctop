package socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public  class client extends JFrame implements ActionListener {
    //定义按钮组件，文本组件，消息组件
    Container container=getContentPane();
    private JButton jButton;
    private JTextArea jTextArea;
    private JTextField jtfMessage;
    private String chatServer;
    //链接
    private Socket socket;
    //初始化输入输出（也许是这个备注
    private ObjectOutputStream out=null;
    private ObjectInputStream in;
    String msg=new String();

    public client(){
        //标题
        super("用户1:");

        //聊天窗口大小
        setSize(400,320);
        //设置固定位置
        setLocation(100,100);
        //设置容器

        JPanel jPanel=new JPanel();
        container.add(jPanel,BorderLayout.NORTH);


        //设置布局方式
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(jPanel,BorderLayout.NORTH);
         JPanel panels=new JPanel();
        panels.add(new Label("聊天内容"));
        jPanel.add(panels);


        jTextArea=new JTextArea(12,30);
        //设置不可编辑
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        //把文本域添加到面板上
        jPanel.add(jTextArea);
        //组件
        JScrollPane scroll=new JScrollPane(jTextArea);
        //设置宽高
        scroll.setSize(336,113);
        jPanel.add(scroll);

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

    public void runClient() {
        try {
            //连接服务器端
            connectServer();
            //读取流信息
            getStream();
            //连接成功进行聊天
            communication();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            //关闭资源
            try {
                closeConnection();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

    }

    private void closeConnection()throws IOException{
        //out in socket
        displayMessage("\n关闭连接!\n");
        setTextFieldEditable(false);
        out.close();
        in.close();
        socket.close();
    }

    public void connectServer()throws IOException {
        displayMessage("正在尝试连接：...\n");
        //括号内参数为：地址，端口号
        socket=new Socket(InetAddress.getByName(chatServer), 15483);
        //一个端口代表一个程序，比如9999，9596，8256
        displayMessage("已经连接到"+socket.getInetAddress().getHostName());

    }
    //显示消息
    private void displayMessage(final String msg){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //append
                jTextArea.append(msg);
                jTextArea.setCaretPosition(jTextArea.getText().length());
            }
        });
    }

    public void getStream() throws IOException {
        //输出流
        out=new ObjectOutputStream(socket.getOutputStream());
        //刷新
        out.flush();
        //输入流
        in=new ObjectInputStream(socket.getInputStream());

    }
    private void communication(){
        //设置框，使其能编辑能写字
        setTextFieldEditable(true);
        do {
            try {
                msg=(String)in.readObject();
            }catch(Exception e){
                displayMessage("1");
            }

        }while (!msg.equals("服务器：stop"));
        displayMessage("\n+msg");
    }

    //设置文本框的状态
    private void setTextFieldEditable(final boolean b){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                jtfMessage.setEditable(b);
            }
        });
    }

    //发送信息
    public  void sendData(String msg){
        try{
            out.writeObject("客户端沙"+msg);
            out.flush();
            displayMessage("\n客户端"+msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
     new client().runClient();
    }

    @Override
    public void actionPerformed(ActionEvent event){
        sendData(jtfMessage.getText());
        String actionCommand =event.getActionCommand();
        switch (actionCommand){
            case "发送":
                jtfMessage.setText("");
                break;
        }

    }


}
