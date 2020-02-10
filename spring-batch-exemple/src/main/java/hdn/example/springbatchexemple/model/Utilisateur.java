/**
 * 
 */
package hdn.example.springbatchexemple.model;

/**
 * @author aragorn
 *
 */
public class Utilisateur {

	private Integer id;
	
	private String civilite;
	
	private String firstName;
	
	private String lastName;
	
	public Utilisateur() {
			
	}

	public Utilisateur(Integer id, String civilite, String firstName, String lastName) {
		super();
		this.id = id;
		this.civilite = civilite;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
