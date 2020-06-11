import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

public class Reader {
    public static void main(String [] args) throws IOException, InterruptedException {

        int bufferSize = 24;
        int counter = 0;
        RandomAccessFile randomAccessFile = new RandomAccessFile("/Users/shysliannykovdenys/Desktop/tpoFirstAssignment/hello", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, bufferSize);
        while(true){
            if(mappedByteBuffer.hasRemaining() && mappedByteBuffer.get() == Operation.Reader.getVal()) {
                continue;
            }else
                mappedByteBuffer.rewind();
            long a = mappedByteBuffer.getLong();
            long b = mappedByteBuffer.getLong();
            if(a == 0 && b == 0){
                mappedByteBuffer.position(mappedByteBuffer.position() - bufferSize);
            }
            else{
                try{
                    mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, counter, bufferSize);
                    System.out.println("First num: " + a);
                    System.out.println("Second num: " + b);
                    System.out.println("Sum is: " + (a + b));
                    System.out.println();
                    mappedByteBuffer.clear();
                    mappedByteBuffer.putLong(Operation.Reader.getVal());
                    fileChannel.write(mappedByteBuffer);
                    counter += 24;
                    TimeUnit.MILLISECONDS.sleep(1000);
                }catch (IOException ioe){
                    mappedByteBuffer.position(mappedByteBuffer.position() - bufferSize);
                }
            }
        }
    }
}