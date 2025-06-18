// package com.example.whatsapp;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// import javax.annotation.PostConstruct;
// import java.io.InputStream;

// @SpringBootApplication
// public class WhatsappApplication {

//     @PostConstruct
//     public void initFirebase() {
//         try {
//             InputStream serviceAccount =
//                     getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");

//             if (serviceAccount == null) {
//                 throw new RuntimeException("Firebase service account file not found");
//             }

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .build();

//             if (FirebaseApp.getApps().isEmpty()) {
//                 FirebaseApp.initializeApp(options);
//                 System.out.println("✅ Firebase initialized");
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("❌ Failed to initialize Firebase");
//         }
//     }

//     public static void main(String[] args) {
//         SpringApplication.run(WhatsappApplication.class, args);
//     }
// }
package com.example.whatsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WhatsappApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhatsappApplication.class, args);
        System.out.println("🚀 WhatsApp Chatbot Application Started...");
    }
}

