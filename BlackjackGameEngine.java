public class BlackjackGameEngine {

    private static final int STARTING_BANKROLL = 100;

    Deck deck;
    Hand dealerHand;
    Hand playerHand;

    int playerBalance;
    int playerBet;

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
        if (playerHand.getValue() > dealerHand.getValue()) {
            playerBalance += (2 * playerBet);
            return playerHand;
        } else if (playerHand.getValue() == dealerHand.getValue()) {
            playerBalance += playerBet;
            return null;
        } else {
            //This case is when the dealer wins, so the player balance is not raised
            return dealerHand;
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
