public class BlackjackBackend {

    public int balance;

    Deck deck;
    Hand dealerHand;
    Hand playerHand;

    public void init() {
        deck = new Deck();

        dealerHand = new Hand();
        dealerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());

        playerHand = new Hand();
        playerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());


    }

    public void newRound() {

    }
}
