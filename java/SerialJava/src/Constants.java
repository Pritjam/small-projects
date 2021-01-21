import java.util.HashMap;

public class Constants {
    public static HashMap<String, Integer> notesMap;

    public static int note(String note) {
        return notesMap.get(note);
    }

    static{
        notesMap = new HashMap<>();
        notesMap.put("B0", 31);
        notesMap.put("C1", 33);
        notesMap.put("CS1", 35);
        notesMap.put("D1", 37);
        notesMap.put("DS1", 39);
        notesMap.put("E1", 41);
        notesMap.put("F1", 44);
        notesMap.put("FS1", 46);
        notesMap.put("G1", 49);
        notesMap.put("GS1", 52);
        notesMap.put("A1", 55);
        notesMap.put("AS1", 58);
        notesMap.put("B1", 62);
        notesMap.put("C2", 65);
        notesMap.put("CS2", 69);
        notesMap.put("D2", 73);
        notesMap.put("DS2", 78);
        notesMap.put("E2", 82);
        notesMap.put("F2", 87);
        notesMap.put("FS2", 93);
        notesMap.put("G2", 98);
        notesMap.put("GS2", 104);
        notesMap.put("A2", 110);
        notesMap.put("AS2", 117);
        notesMap.put("B2", 123);
        notesMap.put("C3", 131);
        notesMap.put("CS3", 139);
        notesMap.put("D3", 147);
        notesMap.put("DS3", 156);
        notesMap.put("E3", 165);
        notesMap.put("F3", 175);
        notesMap.put("FS3", 185);
        notesMap.put("G3", 196);
        notesMap.put("GS3", 208);
        notesMap.put("A3", 220);
        notesMap.put("AS3", 233);
        notesMap.put("B3", 247);
        notesMap.put("C4", 262);
        notesMap.put("CS4", 277);
        notesMap.put("D4", 294);
        notesMap.put("DS4", 311);
        notesMap.put("E4", 330);
        notesMap.put("F4", 349);
        notesMap.put("FS4", 370);
        notesMap.put("G4", 392);
        notesMap.put("GS4", 415);
        notesMap.put("A4", 440);
        notesMap.put("AS4", 466);
        notesMap.put("B4", 494);
        notesMap.put("C5", 523);
        notesMap.put("CS5", 554);
        notesMap.put("D5", 587);
        notesMap.put("DS5", 622);
        notesMap.put("E5", 659);
        notesMap.put("F5", 698);
        notesMap.put("FS5", 740);
        notesMap.put("G5", 784);
        notesMap.put("GS5", 831);
        notesMap.put("A5", 880);
        notesMap.put("AS5", 932);
        notesMap.put("B5", 988);
        notesMap.put("C6", 1047);
        notesMap.put("CS6", 1109);
        notesMap.put("D6", 1175);
        notesMap.put("DS6", 1245);
        notesMap.put("E6", 1319);
        notesMap.put("F6", 1397);
        notesMap.put("FS6", 1480);
        notesMap.put("G6", 1568);
        notesMap.put("GS6", 1661);
        notesMap.put("A6", 1760);
        notesMap.put("AS6", 1865);
        notesMap.put("B6", 1976);
        notesMap.put("C7", 2093);
        notesMap.put("CS7", 2217);
        notesMap.put("D7", 2349);
        notesMap.put("DS7", 2489);
        notesMap.put("E7", 2637);
        notesMap.put("F7", 2794);
        notesMap.put("FS7", 2960);
        notesMap.put("G7", 3136);
        notesMap.put("GS7", 3322);
        notesMap.put("A7", 3520);
        notesMap.put("AS7", 3729);
        notesMap.put("B7", 3951);
        notesMap.put("C8", 4186);
        notesMap.put("CS8", 4435);
        notesMap.put("D8", 4699);
        notesMap.put("DS8", 4978);
    }
}
