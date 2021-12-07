package com.example.mobileappdev_project;

public class Notes {
    private long _id;
    private String judul_notes;
    private String konten_notes;

    //Constructor untuk Class Notes
    public Notes(){
    }

    //Method untuk set ID Notes
    public void setID(long id){
        this._id = id;
    }

    //Method untuk mendapatkan ID Notes
    public long getID(){
        return this._id;
    }

    //Method untuk set Nama Notes
    public void setJudulNotes(String judulNotes){ this.judul_notes = judulNotes;
    }

    //Method untuk mendapatkan Nama Notes
    public String getJudulNotes(){
        return this.judul_notes;
    }

    //Methode untuk set Konten Notes
    public void setKontenNotes(String kontennotes){
        this.konten_notes = kontennotes;
    }

    //Method untuk mendapatkan Konten Notes
    public String getKontenNotes(){
        return this.konten_notes;
    }


    //Method override yang dipakai untuk mengubah objek Notes menjadi String
    @Override
    public String toString(){
        return "Judul Note\t\t\t\t: " + judul_notes + "\nKonten Note \t: " + konten_notes;
    }

}