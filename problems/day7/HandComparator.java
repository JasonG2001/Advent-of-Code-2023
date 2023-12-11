import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class HandComparator implements Comparator<String> {

    public Map<Character, Integer> getVal() {
        Map<Character, Integer> value = new HashMap<>() {{
            put('A', 14);
            put('K', 13);
            put('Q', 12);
            put('J', 11);
            put('T', 10);
        }};
        return value;
    }

    public Map<Character, Integer> count(String hand) {
        Map<Character, Integer> counter = new HashMap<>();
        for (int i = 0; i < hand.length(); i++) {
            char card = hand.charAt(i);
            if (counter.containsKey(card)) {
                counter.put(card, counter.get(card) + 1);
            } else {
                counter.put(card, 1);
            }
        }
        return counter;
    }

    public boolean isFive(String hand) {
        Map<Character, Integer> counter = count(hand);
        return counter.size() == 1;
    }

    public boolean isFour(String hand) {
        Map<Character, Integer> counter = count(hand);
        return counter.size() == 2 && (counter.get(hand.charAt(0)) == 1 || counter.get(hand.charAt(0)) == 4);
    }

    public boolean isFullHouse(String hand) {
        Map<Character, Integer> counter = count(hand);
        return counter.size() == 2;
    }

    public boolean isThree(String hand) {
        Map<Character, Integer> counter = count(hand);
        if (counter.size() != 3) {
            return false;
        } 
        for (Map.Entry<Character, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == 3) {
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair(String hand) {
        Map<Character, Integer> counter = count(hand);
        return counter.size() == 3;
    }

    public boolean isPair(String hand) {
        Map<Character, Integer> counter = count(hand);
        return counter.size() == 4;
    }

    public int getHandPoints(String hand) {
        if (isFive(hand)) {
            return 7;
        } else if (isFour(hand)) {
            return 6;
        } else if (isFullHouse(hand)) {
            return 5;
        } else if (isThree(hand)) {
            return 4;
        } else if (isTwoPair(hand)) {
            return 3;
        } else if (isPair(hand)) {
            return 2;
        } else {
            return 1;
        }
    }

    public int hasGreaterFirstCard(String hand1, String hand2) {
        char hand1Card, hand2Card;
        for (int i = 0; i < hand1.length(); i++) {
            hand1Card = hand1.charAt(i);
            hand2Card = hand2.charAt(i);
            if (getValue(hand1Card) > getValue(hand2Card)) {
                return -1;
            } else if (getValue(hand2Card) > getValue(hand1Card)) {
                return 1;
            }
        }
        return 1;
    }

    public int getValue(char card) {
        Map<Character, Integer> map = getVal();
        if (Character.isDigit(card)) {
            return Integer.parseInt(String.valueOf(card));
        } else {
            return map.get(card);
        }
    }

    public int compare(String hand1, String hand2) {
        if (getHandPoints(hand2) > getHandPoints(hand1)) {
            return 1;
        } else if (getHandPoints(hand2) < getHandPoints(hand1)) {
            return -1;
        } else {
            return hasGreaterFirstCard(hand1, hand2);
        }
    }
}
