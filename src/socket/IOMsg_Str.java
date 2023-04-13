package socket;

import org.omg.CORBA.portable.OutputStream;

import java.io.IOException;
import java.io.InputStream;

public class IOMsg_Str {
    public void SendStr(OutputStream outputStream, String str) throws IOException {
        byte[] ByteList_Str = str.getBytes();
        outputStream.write(ByteList_Str.length);
        outputStream.write(ByteList_Str);
    }
    public String receiveStr(InputStream inputStream) throws IOException {
        int length = inputStream.read();
        System.out.println("端读取信息的长度为：" + length);
        byte[] ByteList_Str= new byte[length];
        inputStream.read(ByteList_Str);
        String result = new String(ByteList_Str);
        return result;
    }

    public void SendStr(java.io.OutputStream os, String bye) {

    }
}
