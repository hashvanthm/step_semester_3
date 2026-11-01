abstract class Instrument {
    abstract String play();
}

class StringInstrument extends Instrument {
    String play() {
        return "Strumming the strings";
    }
}

class Violin extends StringInstrument {
    String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class Main {
    public static void main(String[] args) {
        StringInstrument s = new StringInstrument();
        Violin v = new Violin();

        System.out.println(s.play());
        System.out.println(v.play());
    }
}
