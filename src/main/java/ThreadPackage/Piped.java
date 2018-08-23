package ThreadPackage;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author: xiaoran
 * @date: 2018-08-23 10:29
 *
 * PipedOutputStream  PipedInputStream     ⾯向字节
 * PipedReader  PipedWriter                ⾯向字符
 *
 */
public class Piped {
    public static void main(String[] args) {
        try {
            PipedWriter out = new PipedWriter();
            PipedReader in = new PipedReader();

            PipedOutputStream outputStream = new PipedOutputStream();
            PipedInputStream inputStream = new PipedInputStream();
            // 将输出流和输⼊流进⾏连接，否则在使⽤时会抛出IOException
            out.connect(in);
            outputStream.connect(inputStream);
            Thread printThread = new Thread(new PipPrint(in,inputStream), "PrintThread");
            printThread.start();

            int receive = 0;
            try {
                //接收系统输入
                while ((receive = System.in.read()) != -1) {
                    out.write(receive);
                    outputStream.write(receive);
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


class PipPrint implements Runnable{
    private PipedReader in;

    private PipedInputStream pipedInputStream;

    public PipPrint(PipedReader pipedReader,PipedInputStream pipedInputStream){
        this.in = pipedReader;
        this.pipedInputStream = pipedInputStream;
    }

    @Override
    public void run() {
        int receive = 0;

        int receiveByStream = 0;
        try{
//            while(((receive = in.read()) != -1)){
//                System.out.println((char) receive);
//            }
            System.out.println("-------");
            while(((receiveByStream = pipedInputStream.read()) != -1)){
                System.out.println((char) receiveByStream);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
