package hdn.example.springbatchexemple.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import hdn.example.springbatchexemple.batch.PersonneItemProcessor;
import hdn.example.springbatchexemple.model.Utilisateur;

@Configuration
@EnableBatchProcessing
public class SpringBatchtoCvsConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource datasource;

	@Bean
	public JdbcCursorItemReader<Utilisateur> reader() {

		JdbcCursorItemReader<Utilisateur> reader = new JdbcCursorItemReader<Utilisateur>();
		reader.setDataSource(datasource);
		reader.setSql("select id,civilite,nom,prenom from user");
		reader.setRowMapper((rs, rowNum) -> {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setId(rs.getInt("id"));
			utilisateur.setFirstName(rs.getString("nom"));
			utilisateur.setLastName(rs.getString("prenom"));
			utilisateur.setCivilite(rs.getString("civilite"));
			return utilisateur;
		});

		return reader;
	}

	@Bean
	public PersonneItemProcessor personneItemProcessor() {
		return new PersonneItemProcessor();
	}

	/*@Bean
	public FlatFileItemWriter<Utilisateur> writer () {
		FlatFileItemWriter<Utilisateur> writer = new FlatFileItemWriter<Utilisateur>();
		
		writer.setResource(new ClassPathResource("personnesDB.csv"));
		writer.setLineAggregator(lineAggregator());
		return writer;
	}*/
	
	
	 @Bean
	 public FlatFileItemWriter<Utilisateur> writer(){
	  FlatFileItemWriter<Utilisateur> writer = new FlatFileItemWriter<Utilisateur>();
	  writer.setResource(new ClassPathResource("personnesDB.csv"));
	  writer.setLineAggregator(new DelimitedLineAggregator<Utilisateur>() {{
	   setDelimiter(",");
	   setFieldExtractor(new BeanWrapperFieldExtractor<Utilisateur>() {{
	    setNames(new String[] { "id", "firstName", "lastName", "civilite" });
	   }});
	  }});
	  
	  return writer;
	 }

	@Bean
	public LineAggregator<Utilisateur> lineAggregator() {
		DelimitedLineAggregator<Utilisateur> lineAggregator = new DelimitedLineAggregator<Utilisateur>();

		lineAggregator.setDelimiter(",");
		BeanWrapperFieldExtractor<Utilisateur> fieldExtractor = new BeanWrapperFieldExtractor<Utilisateur>();
		fieldExtractor.setNames(new String[] { "id", "firstName", "lastName", "civilite" });
		lineAggregator.setFieldExtractor(fieldExtractor);

		return lineAggregator;
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Utilisateur, Utilisateur>chunk(100)
				.reader(reader())
				.processor(personneItemProcessor())
				.writer(writer())
				.build();
	}
	
	@Bean
	public Job exportPersonneJob() {
		return jobBuilderFactory.get("exportPersonneJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
	
	

}
