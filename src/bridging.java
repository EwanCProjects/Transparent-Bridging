import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class bridging {
    public bridging() {
    }

    public static void main(String[] args) throws IOException {
        LinkedHashMap<String, String> FDB = new LinkedHashMap();
        File myObj = new File("src/BridgeFDB.txt");
        Scanner myReader = new Scanner(myObj);

        while(myReader.hasNextLine()) {
            String macAddr = myReader.nextLine();
            String port = myReader.nextLine();
            FDB.put(macAddr, port);
        }

        myReader.close();
        FileWriter myWriter1 = new FileWriter("src/BridgeOutput.txt");
        File myObj1 = new File("src/RandomFrames.txt");
        Scanner myReader1 = new Scanner(myObj1);

        String lineTemplate;
        while(myReader1.hasNextLine()) {
            String sourceMacAddr = myReader1.nextLine();
            String destMacAddr = myReader1.nextLine();
            String sourcePort = myReader1.nextLine();
            lineTemplate = sourceMacAddr + "     " + destMacAddr + "     " + sourcePort + "     ";
            FDB.put(sourceMacAddr, sourcePort);
            if (FDB.containsKey(destMacAddr)) {
                if (((String)FDB.get(destMacAddr)).equals(sourcePort)) {
                    myWriter1.write(lineTemplate + "Discarded\n");
                } else {
                    myWriter1.write(lineTemplate + "Forwarded on port " + (String)FDB.get(destMacAddr) + "\n");
                }
            } else {
                myWriter1.write(lineTemplate + "Broadcast\n");
            }
        }

        myWriter1.close();
        myReader1.close();
        PrintWriter pw = new PrintWriter("src/BridgeFDB.txt");
        pw.close();
        FileWriter myWriter = new FileWriter("src/BridgeFDB.txt");
        Iterator var17 = FDB.keySet().iterator();

        while(var17.hasNext()) {
            lineTemplate = (String)var17.next();
            String value = (String)FDB.get(lineTemplate);
            myWriter.write(lineTemplate + "\n");
            myWriter.write(value + "\n");
        }

        myWriter.close();
    }
}
