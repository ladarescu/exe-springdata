package exe.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExeSpringdataApplication {

    public static void main(String[] args) {
        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        SpringApplication.run(ExeSpringdataApplication.class, args);
    }

}
