/**
 * This plays the Blackjack card game that we wrote throughout 
 * the videos in this lesson.
 * 
 * Try to play the game and test it out. As you play it, can you think
 * of ways to improve the game? Can you think of ways to improve the code
 * or organize the code?
 * 
 * @author jkeesh
 *
 */
public class Blackjack extends ConsoleProgram
{

    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;
    
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    // The starting bankroll for the player.
    private static final int STARTING_BANKROLL = 100;
    
    /**
     * Ask the player for a move, hit or stand.
     * 
     * @return A lowercase string of "hit" or "stand"
     * to indicate the player's move.
     */
    private String getPlayerMove()
    {
        while(true)
        {
            String move = readLine("Enter move (hit/stand): ");
            move = move.toLowerCase();
            
            if(move.equals("hit") || move.equals("stand"))
            {
                return move;
            }
            System.out.println("Please try again."); 
        }
    }
    
    /**
     * Play the dealer's turn.
     * 
     * The dealer must hit if the value of the hand is less
     * than 17. 
     * 
     * @param dealer The hand for the dealer.
     * @param deck The deck.
     */
    private void dealerTurn(Hand dealer, Deck deck)
    {
        while(true)
        {
            System.out.println("Dealer's hand");
            System.out.println(dealer);
            
            int value = dealer.getValue();
            System.out.println("Dealer's hand has value " + value);
            
            readLine("Enter to continue...");
            
            if(value < 17)
            {
                System.out.println("Dealer hits");
                Card c = deck.deal();
                dealer.addCard(c);
                
                System.out.println("Dealer card was " + c);
                
                if(dealer.busted())
                {
                    System.out.println("Dealer busted!");
                    break;
                }
            }
            else
            {
                System.out.println("Dealer stands.");
                break;
            }
        }
    }
    
    /**
     * Play a player turn by asking the player to hit
     * or stand.
     * 
     * Return whether or not the player busted.
     */
    private boolean playerTurn(Hand player, Deck deck)
    {
        while(true)
        {
            String move = getPlayerMove();
            
            if(move.equals("hit"))
            {
                Card c = deck.deal();
                System.out.println("Your card was: " + c);
                player.addCard(c);
                System.out.println("Player's hand");
                System.out.println(player);
                
                if(player.busted())
                {
                    return true;
                }
            }
            else
            {
                // If we didn't hit, the player chose to 
                // stand, which means the turn is over.
                return false;
            }
            
        }
    }
    
    /**
     * Determine if the player wins. 
     * 
     * If the player busted, they lose. If the player did 
     * not bust but the dealer busted, the player wins.
     * 
     * Then check the values of the hands.
     * 
     * @param player The player hand.
     * @param dealer The dealer hand.
     * @return
     */
    private boolean playerWins(Hand player, Hand dealer)
    {
        if(player.busted())
        {
            return false;
        }
        
        if(dealer.busted())
        {
            return true;
        }
        
        return player.getValue() > dealer.getValue();
    }
    
    /**
     * Check if there was a push, which means the player and
     * dealer tied.
     * 
     * @param player The player hand.
     * @param dealer The dealer hand.
     * @return
     */
    private boolean push(Hand player, Hand dealer)
    {
        return player.getValue() == dealer.getValue();
    }
    
    /**
     * Find the winner between the player hand and dealer
     * hand. Return how much was won or lost.
     */
    private double findWinner(Hand dealer, Hand player, int bet)
    {
        if(playerWins(player, dealer))
        {
            System.out.println("Player wins!");
            
            if(player.hasBlackjack())
            {
                return 1.5 * bet;
            }
            
            return bet;
        }
        else if(push(player, dealer))
        {
            System.out.println("You push");
            return 0;
        }
        else
        {
            System.out.println("Dealer wins");
            return -bet;
        }
    }
    
    /**
     * This plays a round of blackjack which includes:
     * - Creating a deck
     * - Creating the hands
     * - Dealing the round
     * - Playing the player turn
     * - Playing the dealer turn
     * - Finding the winner
     * 
     * @param bankroll
     * @return The new bankroll for the player.
     */
    private double playRound(double bankroll)
    {
        int bet = readInt("What is your bet? ");

        Deck deck = new Deck();
        deck.shuffle();
        
        Hand player = new Hand();
        Hand dealer = new Hand();
        
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        
        System.out.println("Player's Hand");
        System.out.println(player);
        
        
        System.out.println("Dealer's hand");
        //System.out.println(dealer);
        dealer.printDealerHand();
        
        boolean playerBusted = playerTurn(player, deck);
        
        if(playerBusted)
        {
            System.out.println("You busted :(");
        }

        readLine("Enter for dealer turn...");
        dealerTurn(dealer, deck);
        
        double bankrollChange = findWinner(dealer, player, bet);
        
        bankroll += bankrollChange;
        
        System.out.println("New bankroll: " + bankroll);
        
        return bankroll;
    }
    
    /**
     * Play the blackjack game. Initialize the bankroll and keep
     * playing roudns as long as the user wants to.
     */
    public void run()
    {
        double bankroll = STARTING_BANKROLL;
        System.out.println("Starting bankroll: " + bankroll);
   
        while(true)
        {
            bankroll = playRound(bankroll);
            
            String playAgain = readLine("Would you like to play again? (Y/N)");
            if(playAgain.equalsIgnoreCase("N"))
            {
                break;
            }
        }
        
        System.out.println("Thanks for playing!");
    }
	
}

