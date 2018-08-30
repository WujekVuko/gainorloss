package webapp.sharetransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"webapp.sharetransactions", "basic.dbconnect"})
public class ShareTransactionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareTransactionsApplication.class, args);
    }
}
