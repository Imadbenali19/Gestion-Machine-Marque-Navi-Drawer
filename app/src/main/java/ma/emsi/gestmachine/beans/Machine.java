package ma.emsi.gestmachine.beans;

public class Machine {
    private int id;
    private String reference;
    private String dateAchat;
    private double prix;
    private String marque;

    public Machine() {
    }

    public Machine(int id, String reference, String dateAchat, double prix, String marque) {
        this.id = id;
        this.reference = reference;
        this.dateAchat = dateAchat;
        this.prix = prix;
        this.marque = marque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(String dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    @Override
    public String toString() {
        return "Machine [id=" + id + ", reference=" + reference + ", dateAchat=" + dateAchat + ", prix=" + prix
                + ", marque=" + marque + "]";
    }




}
