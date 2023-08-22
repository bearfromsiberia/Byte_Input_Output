import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream f1in = new FileInputStream("src\\f1.txt");
        FileOutputStream f2out = new FileOutputStream("src\\f2.txt");
        FileOutputStream f3out = new FileOutputStream("src\\f3.txt");
        FileOutputStream f4out = new FileOutputStream("src\\f4.txt");
        FileInputStream f2in = new FileInputStream("src\\f2.txt");
        FileInputStream f3in = new FileInputStream("src\\f3.txt");
        byte[] f1b = f1in.readAllBytes();
        int ch = 0;
        int words = 0;
        int shet1 = -1;
        int shet2 = -1;
        for (int i = 0; i<f1b.length; i++){
            ch++;
            if (f1b[i] == '\n'){
                words++;
                f2out.write((ch-1 + "\n").getBytes());
                f3out.write((words + "\n").getBytes());
                ch = 0;
                words = 0;
            }
            if(f1b[i] == ' '){
                words++;
            }
            if(f1b[i] == '-' ) {
                words--;
            }
        }
        f1in.close();
        f2out.close();
        f3out.close();
        byte[] f2b = f2in.readAllBytes();
        byte[] f3b = f3in.readAllBytes();
        f2in.close();
        f3in.close();
        for(int i = 0; i<f1b.length; i++) {
            if (f1b[i] != '\n') {
                f4out.write(f1b[i]);
            } else {
                for (int f2 = shet1 + 1; f2b[f2] != '\n'; f2++) {
                    shet1 = f2;
                    f4out.write(f2b[f2]);
                }
                f4out.write(' ');
                for (int f3 = shet2 + 1; f3b[f3] != '\n'; f3++) {
                    shet2 = f3;
                    f4out.write(f3b[f3]);
                }
                f4out.write("\r\n".getBytes());
                shet2++;
                shet1++;
            }
        }
        f4out.close();
    }
}