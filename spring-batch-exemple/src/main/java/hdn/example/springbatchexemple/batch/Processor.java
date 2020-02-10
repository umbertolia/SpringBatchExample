package hdn.example.springbatchexemple.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import hdn.example.springbatchexemple.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {

	private static final Map<String, String> civilites = new HashMap<String, String>();
	
	public Processor() {
		civilites.put("1", "Monsieur");
		civilites.put("2", "Madame");
	}



	@Override
	public User process(User user) throws Exception {
		String userCiviliteName = civilites.get(user.getCivilite());
		user.setCivilite(userCiviliteName);
		return user;
	}

}
