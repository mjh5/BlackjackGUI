public class DeckTester extends ConsoleProgram
{
    public void run()
    {
        Deck aDeck = new Deck();  
        System.out.println("Deck before shuffling");
        aDeck.print();
        
        aDeck.shuffle();
        System.out.println("Deck after shuffling");
        aDeck.print();
        
        System.out.println("========");
        
        System.out.println(aDeck.deal());
        System.out.println(aDeck.deal());
    }
}

