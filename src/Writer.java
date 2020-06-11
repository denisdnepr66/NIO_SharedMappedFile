import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Writer {
    public static void main(String args[]) throws IOException, InterruptedException {

        int bufferSize = 24;
        int counter = 0;
        long startPoint;
        Random rand = new Random();
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/shysliannykovdenys/Desktop/tpoFirstAssignment/hello","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        startPoint = fileChannel.size();
        if(startPoint > 0){
            startPoint -= 24;
        }
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, startPoint, bufferSize);
        while (true){
            if(mappedByteBuffer.get() == Operation.Writer.getVal()){
                continue;
            }
            long a;
            long b;
            do{
                a = rand.nextLong();
                b = rand.nextLong();

            }while (a == 0 && b == 0);
            mappedByteBuffer.clear();
            mappedByteBuffer.putLong(Operation.Writer.getVal());
            mappedByteBuffer.putLong(a);
            mappedByteBuffer.putLong(b);
            mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, startPoint + (counter += 24), bufferSize);
            TimeUnit.MILLISECONDS.sleep(1000);

        }
    }

}