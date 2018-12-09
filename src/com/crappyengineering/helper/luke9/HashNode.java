package com.crappyengineering.helper.luke9;

import com.google.gson.annotations.SerializedName;

public class HashNode {

    @SerializedName("hash")
    private String md5Hash;

    @SerializedName("ch")
    private String letter;

    public HashNode(String md5Hash, String letter) {
        this.md5Hash = md5Hash;
        this.letter = letter;
    }

    public HashNode(String md5Hash) {
        this.md5Hash = md5Hash;
        this.letter = null;
    }

    public String getMd5Hash() {
        return md5Hash;
    }

    public String getLetter() {
        return letter;
    }



    @Override
    public String toString() {
        return "HashNode{" +
                "md5Hash='" + md5Hash + '\'' +
                ", letter='" + letter + '\'' +
                '}';
    }
}
