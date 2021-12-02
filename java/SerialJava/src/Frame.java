import java.util.ArrayList;

public class Frame {
    private ArrayList<Note> notes;

    public Frame(String[] noteNames) {
        notes = new ArrayList<>();
        for(String note : noteNames) {
            notes.add(new Note(note));
        }
    }

    public byte[] getFrame() {
        byte[] frame = new byte[16];
        for(int i = 0; i < 3; i++) {
            frame[2 * i] = notes.get(i).getFrequency()[0];
            frame[2 * i + 1] = notes.get(i).getFrequency()[1];
            frame[i + 8] = notes.get(i).getVolume();
        }

        frame[6] = (byte) 0b00010101;
        frame[7] = (byte) 0b11111000;

        frame[11] = (byte) 0b11111111;
        frame[12] = (byte) 0b00001111;
        frame[13] = (byte) 0b00001000;
        frame[14] = (byte) 0b00000000;
        frame[15] = (byte) 0b00000000;
        return frame;
    }

    public String toString() {
        String retString = "\nNumber of notes in this frame: " + notes.size() + ".";
        for(Note n : notes) {
            retString += n.name;
            retString += ".";
        }
        return retString;
    }

private class Note {
    private int frequency;
    private int volume;
    private String name;
    private final int DEFAULT_VOLUME = 8;

    public Note(String note) {
        name = note;
        if (note.equals("")) {
            frequency = 440;
            volume = 0;
            return;
        }
        frequency = Constants.note(note);
        volume = DEFAULT_VOLUME;
    }

    public byte[] getFrequency() {
        byte[] returnArr = new byte[2];
        int tp = 2_000_000 / (16 * frequency);
        returnArr[0] = (byte) tp;
        returnArr[1] = (byte) (tp / 256);
        return returnArr;
    }

    public byte getVolume() {
        return (byte) volume;
    }
}

}
