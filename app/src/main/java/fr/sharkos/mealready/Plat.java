package fr.sharkos.mealready;

public class Plat {

    private long id;
    private String nom;
    private String description;
    private double prixUnitaire;
    private int nbPlat;
    private String photo;

    public long getId() {
        return id;
    }

    public void setId(long id2) {
        this.id = id2;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getNbPlat() {
        return nbPlat;
    }

    public void setNbPlat(int nbPlat) {
        this.nbPlat = nbPlat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}