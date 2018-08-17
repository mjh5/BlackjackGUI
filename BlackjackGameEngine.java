public class BlackjackGameEngine {

    private static final int STARTING_BANKROLL = 100;

    Deck deck;
    Hand dealerHand;
    Hand playerHand;

    int playerBalance;

    /**
     * Method to initialize the Blackjack game
     */
    public void init() {
        newRound();
        playerBalance = STARTING_BANKROLL;
    }

    /**
     * Initializes a new game
     */
    public void newGame() {
        init();
    }

    /**
     * Initializes a new round of Blackjack, with a new shuffled deck and new hands for the dealer and player
     */
    public void newRound() {
        deck = new Deck();
        deck.shuffle();

        dealerHand = new Hand();
        dealerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());

        playerHand = new Hand();
        playerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
    }

    /**
     * Player hit
     */
    public void hit() {
        playerHand.addCard(deck.deal());
    }

    /**
     * Plays out the dealer's turn
     */
    public void playDealerTurn() {
        int value = dealerHand.getValue();

        while (value < 17) {
            Card c = deck.deal();
            dealerHand.addCard(c);
        }
    }
}
