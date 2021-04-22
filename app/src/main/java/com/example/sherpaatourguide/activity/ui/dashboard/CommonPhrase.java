package com.example.sherpaatourguide.activity.ui.dashboard;

public class CommonPhrase {

    private String englishPhrase, nepaliPhrase, arabicPhraseEnglishLetters;
    private int phraseIcon;

    public CommonPhrase(String englishPhrase, String nepaliPhrase, String arabicPhraseEnglishLetters, int phraseIcon) {
        this.englishPhrase = englishPhrase;
        this.nepaliPhrase = nepaliPhrase;
        this.arabicPhraseEnglishLetters = arabicPhraseEnglishLetters;
        this.phraseIcon = phraseIcon;
    }

    public String getEnglishPhrase() {
        return englishPhrase;
    }

    public void setEnglishPhrase(String englishPhrase) {
        this.englishPhrase = englishPhrase;
    }

    public String getNepaliPhrase() {
        return nepaliPhrase;
    }

    public void setNepaliPhrase(String nepaliPhrase) {
        this.nepaliPhrase = nepaliPhrase;
    }

    public String getArabicPhraseEnglishLetters() {
        return arabicPhraseEnglishLetters;
    }

    public void setArabicPhraseEnglishLetters(String arabicPhraseEnglishLetters) {
        this.arabicPhraseEnglishLetters = arabicPhraseEnglishLetters;
    }

    public int getPhraseIcon() {
        return phraseIcon;
    }

    public void setPhraseIcon(int phraseIcon) {
        this.phraseIcon = phraseIcon;
    }
}
