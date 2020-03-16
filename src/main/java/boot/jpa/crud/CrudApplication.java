package boot.jpa.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// CreatedDate, LastModifiedDate 등등을 사용가능 하게끔 허용(객체에 붙이게끔)
@EnableJpaAuditing
public class CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudApplication.class, args);
    }

}
