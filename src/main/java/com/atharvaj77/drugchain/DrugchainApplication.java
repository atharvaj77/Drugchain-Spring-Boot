package com.atharvaj77.drugchain;

import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class DrugchainApplication {
	public static void main(String[] args) throws IOException{

		ClassLoader classLoader = DrugchainApplication.class.getClassLoader();
		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());

		FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());

		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://drugchain-99759-default-rtdb.firebaseio.com")
				.build();

		if(FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(DrugchainApplication.class, args);
	}

}
