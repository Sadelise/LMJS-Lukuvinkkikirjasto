package lukuvinkkikirjasto.io;

import java.util.ArrayList;
import java.util.List;

public class StubIO implements IO {

    private List<String> input;
    private int i;
    private ArrayList<String> output;

    public StubIO(List<String> inputLines) {
        this.input = inputLines;
        output = new ArrayList<>();
    }

    public void print(String toPrint) {
        output.add(toPrint);
    }

    public ArrayList<String> getOutput() {
        return output;
    }

    public String readLine(String prompt) {
        print(prompt);
        if (i < input.size()) {
            return input.get(i++);
        }
        return "";
    }
}
