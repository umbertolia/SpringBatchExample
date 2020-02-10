package hdn.example.springbatchexemple.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
                
	@Id
    private int id;
	
    private String nom;
    
    private String prenom;
    
    private String civilite;
    
    

    public User() {
		super();
	}

	public User(int id, String nom, String prenom, String civilite) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.civilite = civilite;
	}

	public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", civilite=" + civilite + "]";
	}
    
    
    
}