package com.github.quyen.qlthuvien;

import com.github.quyen.qlthuvien.logic.MenuLogic;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class QlthuvienApplication implements CommandLineRunner {


	MenuLogic menuLogic;

	public static void main(String[] args) {
		SpringApplication.run(QlthuvienApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuLogic.run();
	}
}
