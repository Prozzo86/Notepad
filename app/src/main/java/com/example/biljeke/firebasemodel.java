package com.example.biljeke;

public class firebasemodel {

    private String naslov;
    private String sadržaj;

    public firebasemodel(){

    }

    public firebasemodel (String naslov, String sadržaj )
    {
        this.naslov  = naslov;
        this.sadržaj = sadržaj;

    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getSadržaj() {
        return sadržaj;
    }

    public void setSadržaj(String sadržaj) {
        this.sadržaj = sadržaj;
    }
}


