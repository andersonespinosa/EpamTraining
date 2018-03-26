package JavaSE04.Task3;

import java.io.*;

public class EncodingChanger {
    public static void main(String[] args) {
        EncodingChanger encodingChanger = new EncodingChanger();
        encodingChanger.changeEncoding();
    }

    private void changeEncoding() {


        try {
            FileReader reader = new FileReader("D:\\text.txt");
            FileOutputStream outputStream = new FileOutputStream("D:\\text1.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            int value;
            while ((value = reader.read()) != -1) {
                bufferedWriter.write(value);
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
