/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

/**
 *
 * @author barboza
 */
public class Entry {

    private Integer code;
    private String word;

    public Entry(Integer newCode, String newWord) {
        if (newCode != null && newWord != null) {
            this.code = newCode;
            this.word = newWord;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
