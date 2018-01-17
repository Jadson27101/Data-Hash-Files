import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class PMain {
    public static void listf(String directoryName, ArrayList<File> files) throws IOException, NoSuchAlgorithmException {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
                File f = new File(String.valueOf(file));
                FileInputStream fis = new FileInputStream(f);


                StringBuilder builder = new StringBuilder();
                int ch;
                while((ch = fis.read()) != -1){
                    builder.append((char)ch);
                }
                System.out.println(makeSHA1Hash(builder.toString()));
                FileWriter fw = new FileWriter(f);
                fw.write(makeSHA1Hash(builder.toString()));
                builder.setLength(0);
                System.out.println("" + file);
            } else if (file.isDirectory()) {
                listf(file.getAbsolutePath(), files);
            }
        }
    }
    public static String makeSHA1Hash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = input.getBytes("UTF-8");
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return hexStr;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        File f = new File("D://Game//Games//Hearts of Iron IV");
        //processFilesFromFolder(f);
        ArrayList<File> files = new ArrayList<>();
        listf("D://Game//Games//Hearts of Iron IV", files);
    }
}
