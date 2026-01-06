package mycode.masabiliardspring;

import mycode.masabiliardspring.model.Masina;
import mycode.masabiliardspring.repository.MasinaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MasaBiliardSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasaBiliardSpringApplication.class, args);
    }



}
