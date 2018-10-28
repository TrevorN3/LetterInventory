//Trevor Nichols
//Letter Inventory
//Section: AM

// Creates an inventory  that corresponds to the number of occurrences each
// letter of the alphabet has for a given String.
public class LetterInventory {
   
    // The number of elements in the alphabet set.
    private final int INVENTORY_LENGTH = 26;
    private int[] inventory;
    private int size;

    // Constructs an inventory and populates it with the occurrences of
    // alphabetical characters for a given string.
    // Assumes a string is provided.
    public LetterInventory(String data) { 
        this.inventory = new int[INVENTORY_LENGTH];
        this.size = 0;
        data = data.toLowerCase();

        for (int i = 0; i < data.length(); i++) {
            char letterAtIndexOf = data.charAt(i);
            if (letterAtIndexOf >= 'a' && letterAtIndexOf <= 'z') {
                this.inventory[letterAtIndexOf - 'a']++;
                this.size++;
            }
        }
    }
 
    // Returns an int that represent the location of a given letter.
    private int getElementLocation(char letter) {
        return Character.toLowerCase(letter) - 'a';
    }

    // Returns an int that represents the accumulation of each count.
    public int size() {
        return this.size;
    }

    // Determines if an inventory is empty or populated. 
    // Returns true if all counts are zero and false otherwise.
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Returns the number of times a given letter appears as an int.
    // Throws IllegalArugumentException if a non-alphabetic character
    // is passed.
    public int get(char letter) {
        this.determineLegality(letter, 0);
        return this.inventory[this.getElementLocation(letter)];
    }

    // Takes a letter and value. Sets the count for the given
    // char to the given value.
    // Throws IllegalArgumentException if a value less than zero is passed
    // Throws IllegalArugumentException if a non-alphabetic character is
    // passed.
    public void set(char letter, int value) {
        this.determineLegality(letter, value);
        int letterLocation = this.getElementLocation(letter);
        this.size = this.size - this.inventory[letterLocation];
        this.inventory[letterLocation] = value;
        this.size += value;
    }

    // Combines the elements from two different inventories into a new
    // third letter inventory and returns it.
    public LetterInventory add(LetterInventory other) {
        LetterInventory combinedLetterInventorys = new LetterInventory("");
        for (int i = 0; i < INVENTORY_LENGTH; i++) {
            combinedLetterInventorys.inventory[i] = this.inventory[i] + 
                                                        other.inventory[i];
        }
        combinedLetterInventorys.size = this.size() + other.size();
        return combinedLetterInventorys;
    }

    // Returns a new letter inventory that is comprised of the 
    // differences of the two given letter inventories.
    // If one of the differences is negative returns a null inventory
    public LetterInventory subtract(LetterInventory other) {
        LetterInventory combinedLetterInventorys = new LetterInventory("");
        for (int i = 0; i < INVENTORY_LENGTH; i++) {
            if ((this.inventory[i] - other.inventory[i]) < 0) {
                return combinedLetterInventorys = null;
            }
            combinedLetterInventorys.inventory[i] = this.inventory[i] - 
                                                        other.inventory[i];

        }
        combinedLetterInventorys.size = this.size() - other.size();
        return combinedLetterInventorys;
    }

    // Returns a string representation of the letter inventory where all the 
    // characters are in order, lower case, and surrounded by square
    // brackets.
    // Example: [aabbbccccccz]
    @Override
    public String toString() {
        String printString = "[";
        if(!this.isEmpty()){
            for (int i = 0; i < INVENTORY_LENGTH; i++) {
                for (int j = 0; j < this.inventory[i]; j++) {
                    printString += (char) ('a' + i);
                }
            } 
        }
        return printString += "]";
    }
    
    // Determines if a given char or int value is a legal argument.
    private void determineLegality (char letter, int value){
        if (value < 0) {
            throw new IllegalArgumentException("The value must be greater than"
                                       + " or equal to zero. Value: " + value);
        }
        if ((letter < 'A' || letter > 'Z') && (letter < 'a' || letter > 'z')) {
            throw new IllegalArgumentException(letter + " is not a valid "
                                                + "alphabetic character.");
        }
    }
}
