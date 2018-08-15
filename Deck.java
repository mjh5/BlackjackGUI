import java.util.*; //need to import ArrayList

public class Deck
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;

    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    // Instance variables
    
    // This stores the deck which is a list of the Card objects.
    private ArrayList<Card> aDeck;
    
    /**
     * This creates a Deck. A Deck starts as a list of 52 cards.
     * We loop through each suit and rank and construct a card
     * and add it to the deck.
     */
    public Deck()
    {
        aDeck = new ArrayList<Card>();
        
        for(int rank = 2; rank <= ACE; rank++)
        {
            for(int suit = HEARTS; suit <= CLUBS; suit++)  //use constants!
            {
                Card card = new Card(rank, suit);  //create a card
                aDeck.add(card);
            }
        }
    }
    
    // Getter method
    
    /**
     * This getter method returns the ArrayList of cards.
     * @return ArrayList<Card> of the Cards.
     */
    public ArrayList<Card> getCards()
    {
        return aDeck;
    }
    
    /**
     * This deals the first Card from the deck by removing it.
     * @return The first Card in the deck.
     */
    public Card deal()
    {
        return aDeck.remove(0);
    }
    
    /**
     * This prints out the current state of the deck.
     */
    public void print()
    {
        for(Card card: aDeck)
        {
            System.out.println(card);
        }
    }
    
    /**
     * This shuffles the deck by making 52 swaps of
     * card positions.
     */
    public void shuffle()
    {
        for(int i = 0; i < aDeck.size(); i++)
        {
            int randomIndex = Randomizer.nextInt(52);
            Card x = aDeck.get(i);
            Card y = aDeck.get(randomIndex);
            
            aDeck.set(i, y);
            aDeck.set(randomIndex, x);
        }
    }

}

