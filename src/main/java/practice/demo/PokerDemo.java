package practice.demo;

import lombok.Getter;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author Jay Yang
 * @date 2023/2/27
 */
public class PokerDemo {

    public static void main(String[] args) {

        Poker poker = new Poker();

        TreeSet<Card> diZhu = new TreeSet<>();
        TreeSet<Card> pingMin1 = new TreeSet<>();
        TreeSet<Card> pingMin2 = new TreeSet<>();

        for (int i = 0; i < 17; i++) {
            diZhu.add(poker.get());
            pingMin1.add(poker.get());
            pingMin2.add(poker.get());
        }

        while (poker.hasNext()) {
            diZhu.add(poker.get());
        }

        System.out.println(diZhu);
        System.out.println(pingMin1);
        System.out.println(pingMin2);

    }

    private static class Poker {

        private static final int totalCard = 54;

        private final Card[] cards;
        private int leftCard;

        public Poker() {
            cards = init();
            leftCard = totalCard;
        }

        private Card[] init() {
            Card[] cards = new Card[totalCard];
            AtomicInteger ai = new AtomicInteger(0);
            cards[ai.getAndIncrement()] = new Card(true);
            cards[ai.getAndIncrement()] = new Card(false);
            Stream.of(Card.heart, Card.club, Card.diamond, Card.spade)
                    .forEach(suit -> {
                        for (int i = 1; i <= 13; i++) {
                            cards[ai.getAndIncrement()] = new Card(i, suit);
                        }
                    });
            Random random = new Random();
            for (int i = ai.get(); i > 0; i--) {
                int randomIndex = random.nextInt(i);
                Card b = cards[randomIndex];
                cards[randomIndex] = cards[i - 1];
                cards[i - 1] = b;
            }
            return cards;
        }

        public boolean hasNext() {
            return leftCard > 0;
        }

        public Card get() {
            leftCard--;
            return cards[leftCard];
        }

    }

    @Getter
    private static class Card implements Comparable<Card> {

        public static final char heart = '♥';
        public static final char club = '♣';
        public static final char diamond = '♦';
        public static final char spade = '♠';

        private final int num;
        private final char suit;
        private final boolean joker;

        Comparator<Card> jokerComparator = (o1, o2) -> Boolean.compare(o2.joker, o1.joker);

        Comparator<Card> numComparator = (o2, o1) -> {
            int num1 = o1.num;
            int num2 = o2.num;
            return (num1 == 1 ? 14 : num1) - (num2 == 1 ? 14 : num2);
        };

        Comparator<Card> suitComparator = new Comparator<Card>() {

            final Map<Character, Integer> charScoreMap = new HashMap<>();

            {
                charScoreMap.put(heart, 0);
                charScoreMap.put(club, 1);
                charScoreMap.put(diamond, 2);
                charScoreMap.put(spade, 3);
            }

            @Override
            public int compare(Card o1, Card o2) {
                return charScoreMap.get(o1.suit) - charScoreMap.get(o2.suit);
            }
        };

        Comparator<Card> cardComparator = jokerComparator.thenComparing(numComparator)
                .thenComparing(suitComparator);

        public Card(int num, char suit) {
            this.num = num;
            this.suit = suit;
            this.joker = false;
        }

        public Card(boolean chief) {
            this.joker = true;
            this.num = 0;
            this.suit = chief ? heart : spade;
        }

        @Override
        public String toString() {
            String numStr;
            if (joker) {
                numStr = "Joker";
            } else if (num == 1) {
                numStr = "A";
            } else if (num == 11) {
                numStr = "J";
            } else if (num == 12) {
                numStr = "Q";
            } else if (num == 13) {
                numStr = "K";
            } else {
                numStr = num + "";
            }

            if (suit == heart || suit == diamond) {
                return "\033[31;3m" + suit + numStr + "\033[0m";
            } else {
                return "\033[30;3m" + suit + numStr + "\033[0m";
            }
        }

        @Override
        public int compareTo(Card card) {
            return cardComparator.compare(this, card);
        }

    }

}
