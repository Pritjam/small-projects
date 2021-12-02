import java.util.ArrayList;

public class PJ2Frame {
    private ArrayList<Note> notes;
    private byte afterByte;
    private byte mixerByte;

    public PJ2Frame(String[] noteNames) {
        afterByte = 0;
        mixerByte = (byte) 0b11111000;
        notes = new ArrayList<>();
        for(int i = 0; i < noteNames.length; i++) {
            String note = noteNames[i].toUpperCase();
            
            if(note.length() > 0 && note.charAt(note.length() - 1) == 'N') {
                afterByte |= 0x1 << i;
                notes.add(new Note(note.substring(0, note.length() - 1)));
            } else if(note.length() == 0) {
                mixerByte |= 0x1 << i;
                notes.add(new Note(note));
            } else {
                notes.add(new Note(note));
            }
        }
        if(afterByte != 0) {
            afterByte |= 0b11111000;
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
        frame[7] = mixerByte;

        frame[11] = (byte) 0b11111111;
        frame[12] = (byte) 0b00001111;
        frame[13] = (byte) 0b00001000;
        frame[14] = afterByte;
        frame[15] = (byte) 0b00000000;
        return frame;
    }

    public byte getAfterByte() {
        return afterByte;
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
    private final int DEFAULT_VOLUME = 5;

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
