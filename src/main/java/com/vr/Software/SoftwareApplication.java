package com.vr.Software;

import com.vr.Software.view.MainUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoftwareApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SoftwareApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new Thread(() -> MainUI.startGUI()).start();
	}
}
