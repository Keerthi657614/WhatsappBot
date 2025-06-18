// // package com.example.whatsapp.config;


// // import com.google.auth.oauth2.GoogleCredentials;
// // import com.google.firebase.FirebaseApp;
// // import com.google.firebase.FirebaseOptions;
// // import org.springframework.context.annotation.Configuration;

// // import javax.annotation.PostConstruct;
// // import java.io.IOException;
// // import java.io.InputStream;

// // @Configuration
// // public class FirebaseConfig {

// //     @PostConstruct
// //     public void init() throws IOException {
// //         InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");

// //         FirebaseOptions options = new FirebaseOptions.Builder()
// //                 .setCredentials(GoogleCredentials.fromStream(serviceAccount))
// //                 .build();

// //         if (FirebaseApp.getApps().isEmpty()) {
// //             FirebaseApp.initializeApp(options);
// //             System.out.println("✅ Firebase has been initialized");
// //         }
// //     }
// // }

// package com.example.whatsapp.config;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import jakarta.annotation.PostConstruct;
// import org.springframework.stereotype.Component;
// import java.io.FileInputStream;

// import java.io.IOException;

// @Component
// public class FirebaseConfig {

//     @PostConstruct
//     public void init() {
//         try {
//             System.out.println("🔥 FirebaseConfig: Initializing Firebase...");

//             FileInputStream serviceAccount =new FileInputStream("/etc/secrets/firebase.json");


//             if (serviceAccount == null) {
//                 throw new RuntimeException("❌ Firebase serviceAccountKey.json not found in resources/firebase");
//             }

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .build();

//             if (FirebaseApp.getApps().isEmpty()) {
//                 FirebaseApp.initializeApp(options);
//                 System.out.println("✅ Firebase initialized successfully");
//             }
//         } catch (IOException e) {
//             throw new RuntimeException("❌ Failed to initialize Firebase", e);
//         }
//     }
// }
// package com.example.whatsapp.config;

// import com.google.auth.oauth2.GoogleCredentials;
// import com.google.firebase.FirebaseApp;
// import com.google.firebase.FirebaseOptions;
// import jakarta.annotation.PostConstruct;
// import org.springframework.stereotype.Component;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.InputStream;
// import java.io.IOException;

// @Component
// public class FirebaseConfig {

//     @PostConstruct
//     public void init() {
//         try {
//             System.out.println("🔥 FirebaseConfig: Initializing Firebase...");

//             InputStream serviceAccount;

//             // Check for production secret path first (Render)
//             File prodKeyFile = new File("/etc/secrets/firebase.json");
//             if (prodKeyFile.exists()) {
//                 serviceAccount = new FileInputStream(prodKeyFile);
//                 System.out.println("📁 Using Firebase service account from /etc/secrets/firebase.json");
//             } else {
//                 // Fallback to local development path
//                 serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");
//                 if (serviceAccount == null) {
//                     throw new RuntimeException("❌ serviceAccountKey.json not found in resources folder");
//                 }
//                 System.out.println("📁 Using Firebase service account from resources");
//             }

//             FirebaseOptions options = FirebaseOptions.builder()
//                     .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                     .build();

//             if (FirebaseApp.getApps().isEmpty()) {
//                 FirebaseApp.initializeApp(options);
//                 System.out.println("✅ Firebase initialized successfully");
//             }

//         } catch (IOException e) {
//             throw new RuntimeException("❌ Failed to initialize Firebase", e);
//         }
//     }
// }
package com.example.whatsapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirebaseConfig {

    @PostConstruct
    public void init() {
        try {
            System.out.println("🔥 FirebaseConfig: Initializing Firebase...");

            // Support both local and Render paths
            File file = new File("/etc/secrets/firebase.json"); // Render path
            if (!file.exists()) {
                file = new File("src/main/resources/serviceAccountKey.json"); // Local fallback
            }

            FileInputStream serviceAccount = new FileInputStream(file);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully");
            }
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to initialize Firebase", e);
        }
    }
}
