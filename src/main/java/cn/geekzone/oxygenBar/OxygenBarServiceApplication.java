package cn.geekzone.oxygenBar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"cn.geekzone.oxygenBar"})
public class OxygenBarServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OxygenBarServiceApplication.class, args);
	}

}
