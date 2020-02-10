package hdn.example.springbatchexemple.batch;

import org.springframework.batch.item.ItemProcessor;

import hdn.example.springbatchexemple.model.Utilisateur;


public class PersonneItemProcessor implements ItemProcessor<Utilisateur, Utilisateur> {

	@Override
	public Utilisateur process(Utilisateur utilisateur) throws Exception {
		utilisateur.setFirstName(utilisateur.getFirstName().toUpperCase());
		return utilisateur;
	}

}
