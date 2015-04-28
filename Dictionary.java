package lzw;

import java.util.ArrayList;

/*
 * Defines the association between an 2-bytes integer code and a string.
 */

public class Dictionary {

    protected ArrayList<Entry> entries;
    protected int numberOfWords;

    public Dictionary() {
        /* Initializes with the ASCII table */
        this.numberOfWords = 0;
        this.entries = new ArrayList<>();

        
        for (int i = 0; i < 256; i++) {
            Integer counter = new Integer(i);
            Entry tmpEntry = new Entry(counter, String.valueOf((char) counter.intValue()));
            this.appendNewEntry(tmpEntry);
            this.numberOfWords = i;
        }
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    /*
     * appendNewEntry: adds an new entry.
     * 
     * given an object of the type Entry, adds it to the dictionary, 
     * at the end of the dictionary. The string defines the word to be coded
     * and the 2-bytes char defines the calculated code for the new entry.
     * 
     * @newEntry: the new entry to be added to the dictionary.
     *
     * returns: void.
     */
    public void appendNewEntry(Entry newEntry) {

        if (newEntry != null) {
            if (isInTheDictionary(newEntry.getWord()) == null) {
                this.entries.add(newEntry);
                this.numberOfWords = this.entries.size();
            }
        } else {
            System.out.println("ERROR: Null pointer at appenNewEntry.");
        }
    }

    /*
     * appendNewEntry: adds an new entry
     * 
     * given an new string, adds it to the dictionary, 
     * at the end of the dictionary. The string defines the word to be coded
     * and the 2-bytes char defines the calculated code for the new entry.
     * 
     * @newStr: the new string which will be added at the end of the dictionary.
     *
     * returns: void.
     */
    public void appendNewEntry(String newStr) {

        if (newStr != null) {
            if (isInTheDictionary(newStr) == null) {
                Entry newEntry = new Entry(this.numberOfWords + 1, newStr);
                this.numberOfWords = this.entries.size();
                this.entries.add(newEntry);
            }
        } else {
            System.out.println("ERROR: Null pointer at appenNewEntry.");
        }
    }
    
    /*
     * isInTheDictionary: look up for an given string in the dictionary.
     * 
     * given an string of the type key, look up for the given string at
     * the dicionary.  Performs an linear search.
     * 
     * @key: the key to be searched at the dictionary.
     *
     * returns: the corresponding entry, if the key was found
     *          null pointer, otherwise.
     */
    public Entry isInTheDictionary(String key) {
        if (key == null || key == "") {
            return null;
        }
        
        for (Entry tmpEntry : this.entries) {
            if (tmpEntry.getWord().equals(key)) {
                return tmpEntry;
            }
        }

        return null;
    }

    /*
     * isInTheDictionary: look up for an given code in the dictionary.
     * 
     * given an int of two bytes, look up for the given code at
     * the dicionary.  Performs an linear search.
     * 
     * @code: the 2-byte int to be searched at the dictionary.
     *
     * returns: the corresponding entry, if the code was found
     *          null pointer, otherwise.
     */
    public Entry isInTheDictionary(int code) {
        Integer wrapper = new Integer(code);
        for (Entry tmpEntry : this.entries) {
            if (tmpEntry.getCode().intValue() == wrapper.intValue()) {
                return tmpEntry;
            }
        }

        return null;
    }
}
