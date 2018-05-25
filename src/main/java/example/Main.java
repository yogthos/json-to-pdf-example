package example;

import org.yogthos.JsonPDF;

public class Main {

    public static void main(String[] args) throws Exception {
        String jsonDoc1 = "[{}, [\"paragraph\", \"hello world\"]]";
        JsonPDF.writeToFile(jsonDoc1, "./out.pdf");
    }
}
