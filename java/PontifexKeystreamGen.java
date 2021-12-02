import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class PontifexKeystreamGen {
    public static void main(String[] args) {
        Integer[] shuffleArr = {1, 2, 10, 15, 19, 13, 3, 7, -2, 6, -1, 8, 14, 12, 16, 5, 4, 17, 20, 9, 18, 11};
        ArrayList<Integer> shuffle = new ArrayList<>(Arrays.asList(shuffleArr));
        int size = shuffle.size();
        int aIndex = shuffle.indexOf(-1);
        int bIndex = shuffle.indexOf(-2);

        ArrayList<Integer> keyStream = new ArrayList<Integer>();
        //System.out.println(shuffle);

        for(int i = 0; i < 13; i++) {
            if(aIndex == size - 1) {
                shuffle.remove(size - 1);
                shuffle.add(0, -1);
                aIndex = 0;
            }

            aIndex = shuffle.indexOf(-1);
            Collections.swap(shuffle, aIndex, aIndex + 1);
            bIndex = shuffle.indexOf(-2);

            if(bIndex == size - 1) {
                shuffle.remove(size - 1);
                shuffle.add(2, -2);
                bIndex = 2;
            } else if(bIndex == size - 2) {
                shuffle.remove(size - 2);
                shuffle.add(1, -2);
                bIndex = 1;
            } else {
                shuffle.remove(bIndex);
                shuffle.add(bIndex + 2, -2);

            }

            System.out.println(shuffle);
            
            aIndex = shuffle.indexOf(-1);
            bIndex = shuffle.indexOf(-2);

            int lowerIndex = Math.min(aIndex, bIndex);
            int higherIndex = Math.max(aIndex, bIndex);

            ArrayList<Integer> lowerBread = new ArrayList<Integer>(shuffle.subList(0, lowerIndex));
            ArrayList<Integer> jam = new ArrayList<Integer>(shuffle.subList(lowerIndex, higherIndex + 1));
            ArrayList<Integer> highBread = new ArrayList<Integer>(shuffle.subList(higherIndex + 1, size));

            highBread.addAll(jam);
            highBread.addAll(lowerBread);
            shuffle = highBread;

            if(shuffle.get(size - 1) > 0) {
                int indexCut = shuffle.get(size - 1);
                ArrayList<Integer> toAdd = new ArrayList<Integer>(shuffle.subList(0, indexCut + 1));
                shuffle.subList(0, indexCut + 1).clear();
                shuffle.remove(shuffle.size() - 1);
                shuffle.addAll(toAdd);
                shuffle.add(indexCut);
            }

            int firstVal = shuffle.get(0);
            if(firstVal == -1 || firstVal == -2) {
                firstVal = 21;
            }

            
            keyStream.add(shuffle.get(firstVal));



        }
        System.out.println("KeyStream: \n");
    System.out.println(keyStream);
    }
    
}