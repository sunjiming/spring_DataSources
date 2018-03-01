package spring_DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ServletComponentScan
public class JMApp implements CommandLineRunner{
	
	@Autowired
	@Qualifier("jdbcTemplateDs1")
	protected JdbcTemplate jdbcTemplateDs1;
	
	@Autowired
	@Qualifier("jdbcTemplateDs2")
	protected JdbcTemplate jdbcTemplateDs2;
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(JMApp.class, args);
	}
	
	public void run(String... args) throws Exception {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		int x = jdbcTemplateDs1.queryForObject("select count(*) from ds1",Integer.class);
		System.out.println("ds1表有"+x+"条数据");
		int y = jdbcTemplateDs2.queryForObject("select count(*) from ds2",Integer.class);
		System.out.println("ds2表有"+y+"条数据");
	}

}
