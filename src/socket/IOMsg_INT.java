package socket;

import org.omg.CORBA.portable.OutputStream;

import java.io.IOException;
import java.io.InputStream;

public class IOMsg_INT {
    public void sendInt(OutputStream outputStream, int number) throws IOException {
        int[] buffer = new int[4];
        for(int i=0;i<4;i++) {
            buffer[i] = (number >> (24 - i * 8)) & 0xff;
            outputStream.write(buffer[i]);
        }

    }

    public int receiveInt(InputStream inputStream) throws IOException {
        int number = 0;
        for(int i=0;i<4;i++)
            number += inputStream.read()<<(24-i*8);
        return number;

    }
}
