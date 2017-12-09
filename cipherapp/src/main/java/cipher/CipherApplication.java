package cipher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cipher")
public class CipherApplication{

        public static void main(String args[]) {
        SpringApplication.run(CipherApplication.class, args);
    }

}
