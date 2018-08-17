public class BlackjackGameEngine {

    private static final int STARTING_BANKROLL = 100;

    Deck deck;
    Hand dealerHand;
    Hand playerHand;

    int playerBalance;
    int playerBet;

    /**
     * Initializes player balance for the game
     */
    public void init() {
        playerBalance = STARTING_BANKROLL;
    }

    /**
     * Starts a new game, with a new player balance
     */
    public void newGame() {
        init();
    }

    /**
     * Checks if the game is over
     *
     * @return true if the player still has money to bet, false otherwise
     */
    public boolean isOver() {
        return playerBalance <= 0;
    }

    /**
     * Plays a round of Blackjack
     */
    public void playRound(int bet) {
        deck = new Deck();
        deck.shuffle();

        dealerHand = new Hand();
        dealerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());

        playerHand = new Hand();
        playerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());

        playerBet = bet;
        playerBalance -= playerBet;
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

    /**
     * Finds the winner of the round and adjusts the player balance based on the bet
     *
     * @return the winner of the round, null if a draw
     */
    public Hand getWinner() {
        if (playerHand.getValue() > dealerHand.getValue() || dealerHand.busted()) {
            playerBalance += (2 * playerBet);
            return playerHand;
        } else if (playerHand.getValue() < dealerHand.getValue() || playerHand.busted()) {
            //This case is when the dealer wins, so the player balance is not raised
            return dealerHand;
        } else { //Case where the dealer and player push
            playerBalance += playerBet;
            return null;
        }
    }

    //Below are auto-generated getter methods

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    public int getPlayerBet() {
        return playerBet;
    }
}
