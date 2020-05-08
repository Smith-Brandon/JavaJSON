import jdk.nashorn.internal.parser.JSONParser;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonProcessingException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Company {
    public static FileWriter file;
    public static String EntryToJSON(Entry entry) {

        ObjectMapper mapper = new ObjectMapper();
        String s = "";

        try {
            s = mapper.writeValueAsString(entry);
        } catch (JsonProcessingException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            file = new FileWriter("C:/Users/mormo/OneDrive/Desktop/savedJSON/JSON.txt");
            file.write(s);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }

    public static Entry JSONToEntry (String s) {
        ObjectMapper mapper = new ObjectMapper();
        Entry entry = null;

        try {
            entry = mapper.readValue(s, Entry.class);

        } catch (JsonParseException e) {
            System.err.println(e.toString());
        } catch (JsonMappingException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        return entry;
    }

    public static void main(String[] args) {
        Scanner systemInScanner = new Scanner(System.in);

            System.out.printf("Please enter the customer name\n");
            String nameIn = systemInScanner.nextLine();
            String nameOut = "";

        if (Pattern.matches("[0-9]+", nameIn) == false) {
            nameOut = nameIn;
        }
        else{
            System.out.println("Can only enter letters in this field try again!");
            System.exit(0);
        }

            System.out.printf("Please enter the customer phone number\n");
            String phoneIn = systemInScanner.nextLine();
            String phoneOut = "";

        if (Pattern.matches("[a-zA-Z]+", phoneIn) == false) {
            phoneOut = phoneIn;
        }else {
            System.out.println("Can only enter numbers in this field try again!");
            System.exit(0);
        }

            Entry entry = new Entry();
            entry.setName(nameOut);
            entry.setPhone(phoneOut);

            String json = Company.EntryToJSON(entry);
            System.out.println(json);

            Entry RecoveredEntry = Company.JSONToEntry(json);
            System.out.println(RecoveredEntry);

            System.out.printf("This data has been written to C:/Users/mormo/OneDrive/Desktop/savedJSON/JSON.txt \n");

        }
    }
