import java.util.Scanner;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind Man’s Bluff\n");

        int player1Score = 0;
        int computerScore = 0;
        Scanner scanner = new Scanner(System.in);

        while (player1.size > 0 && computer.size > 0) {
            System.out.println("\nPress Enter to play the next round...");
            scanner.nextLine();

            // Each player draws the top card
            Card player1Card = player1.remove_from_head();
            Card computerCard = computer.remove_from_head();

            // Display cards (opponent's card is seen first)
            System.out.print("Computer drew: ");
            computerCard.print_card();
            System.out.print("\nPlayer 1 drew: ");
            player1Card.print_card();
            System.out.println();

            // Compare ranks
            if (player1Card.rank.ordinal() > computerCard.rank.ordinal()) {
                System.out.println("Player 1 wins this round!\n");
                player1Score++;
            } else if (player1Card.rank.ordinal() < computerCard.rank.ordinal()) {
                System.out.println("Computer wins this round!\n");
                computerScore++;
            } else {
                System.out.println("It’s a tie!\n");
            }
        }
    }

    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one

        // shuffle the deck (random order)
        deck.shuffle(512);
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }
}
