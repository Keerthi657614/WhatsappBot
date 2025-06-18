// // // package com.example.whatsapp.controller;

// // // import com.google.api.core.ApiFuture;
// // // import com.google.cloud.firestore.DocumentReference;
// // // import com.google.cloud.firestore.Firestore;
// // // import com.google.firebase.cloud.FirestoreClient;
// // // import org.springframework.http.HttpStatus;
// // // import org.springframework.http.ResponseEntity;
// // // import org.springframework.web.bind.annotation.*;

// // // import java.util.HashMap;
// // // import java.util.Map;

// // // @RestController
// // // @RequestMapping("/webhook")
// // // public class WebhookController {

// // //     private final String VERIFY_TOKEN = "EAAKGFmlVZCuoBO9mB8ZAzW0NZAaTnKhBRPdZCuc9egviSsTvFZCV8xIvQMF2mfXiorcYSMJaHoo2V1D1oXopZCDMvpzjWnmxOPbqloROgffkb1nqhq9RxhZBzn06p0ZAJ45mOZCDaVTkka2C1IRHp9tTqc4YDpAWTf1YciBC44JFgkNNZBoTqWkEnoGJOhvZATZATkwNFhAPjl1UPFfQ12BilGnhr5ZCbNnbrFzFc6nYZD"; // Use same token in Meta dashboard

// // //     // STEP 1: Meta Webhook Verification (GET)
// // //     @GetMapping
// // //     public ResponseEntity<String> verifyWebhook(
// // //             @RequestParam(name = "hub.mode") String mode,
// // //             @RequestParam(name = "hub.verify_token") String token,
// // //             @RequestParam(name = "hub.challenge") String challenge) {

// // //         if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
// // //             System.out.println("✅ Webhook verified");
// // //             return ResponseEntity.ok(challenge);
// // //         } else {
// // //             System.out.println("❌ Webhook verification failed");
// // //             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification failed");
// // //         }
// // //     }

// // //     // STEP 2: Handle Incoming Webhook Messages (POST)
// // //     @PostMapping
// // //     public ResponseEntity<String> receiveWebhook(@RequestBody String payload) {
// // //         System.out.println("📩 Incoming WhatsApp message: " + payload);

// // //         try {
// // //             // Get Firestore database instance
// // //             Firestore db = FirestoreClient.getFirestore();

// // //             // Prepare message document
// // //             Map<String, Object> message = new HashMap<>();
// // //             message.put("timestamp", System.currentTimeMillis());
// // //             message.put("raw_payload", payload); // Save full payload

// // //             // Save to "whatsapp_messages" collection and wait for completion
// // //             ApiFuture<DocumentReference> future = db.collection("whatsapp_messages").add(message);
// // //             DocumentReference docRef = future.get();  // Wait for write to complete

// // //             System.out.println("✅ Message saved to Firestore with ID: " + docRef.getId());
// // //         } catch (Exception e) {
// // //             System.out.println("❌ Firebase save failed: " + e.getMessage());
// // //             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving message");
// // //         }

// // //         return ResponseEntity.ok("EVENT_RECEIVED");
// // //     }
// // // }
// // package com.example.whatsapp.controller;

// // import com.example.whatsapp.service.FirebaseService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.HttpStatus;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // import java.util.HashMap;
// // import java.util.Map;

// // @RestController
// // @RequestMapping("/webhook")
// // public class WebhookController {

// //     private final String VERIFY_TOKEN = "EAAKGFmlVZCuoBO9mB8ZAzW0NZAaTnKhBRPdZCuc9egviSsTvFZCV8xIvQMF2mfXiorcYSMJaHoo2V1D1oXopZCDMvpzjWnmxOPbqloROgffkb1nqhq9RxhZBzn06p0ZAJ45mOZCDaVTkka2C1IRHp9tTqc4YDpAWTf1YciBC44JFgkNNZBoTqWkEnoGJOhvZATZATkwNFhAPjl1UPFfQ12BilGnhr5ZCbNnbrFzFc6nYZD"; // Use same token in Meta dashboard
// //     @Autowired
// //     private FirebaseService firebaseService;

// //     // STEP 1: Webhook Verification (GET)
// //     @GetMapping
// //     public ResponseEntity<String> verifyWebhook(
// //             @RequestParam("hub.mode") String mode,
// //             @RequestParam("hub.verify_token") String token,
// //             @RequestParam("hub.challenge") String challenge) {

// //         if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
// //             System.out.println("✅ Webhook verified");
// //             return ResponseEntity.ok(challenge);
// //         } else {
// //             System.out.println("❌ Webhook verification failed");
// //             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification failed");
// //         }
// //     }

// //     // STEP 2: Webhook Event Handling (POST)
// //     @PostMapping
// //     public ResponseEntity<String> receiveWebhook(@RequestBody String payload) {
// //         System.out.println("📩 Incoming WhatsApp message: " + payload);

// //         try {
// //             Map<String, Object> data = new HashMap<>();
// //             data.put("timestamp", System.currentTimeMillis());
// //             data.put("payload", payload);

// //             String docId = firebaseService.saveData("whatsapp_messages", data);
// //             System.out.println("✅ Message saved to Firestore: " + docId);
// //         } catch (Exception e) {
// //             System.out.println("❌ Error saving to Firestore: " + e.getMessage());
// //             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving message");
// //         }

// //         return ResponseEntity.ok("EVENT_RECEIVED");
// //     }
// // }
// package com.example.whatsapp.controller;

// import com.example.whatsapp.service.FirebaseService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.HashMap;
// import java.util.Map;

// @RestController
// @RequestMapping("/webhook")
// public class WebhookController {

//     // ✅ Use any string you want, but it must match exactly in the Meta dashboard
//     private final String VERIFY_TOKEN = "my_custom_verify_token";

//     @Autowired
//     private FirebaseService firebaseService;

//     // STEP 1: Webhook Verification (GET)
//     @GetMapping
//     public ResponseEntity<String> verifyWebhook(
//             @RequestParam("hub.mode") String mode,
//             @RequestParam("hub.verify_token") String token,
//             @RequestParam("hub.challenge") String challenge) {

//         if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
//             System.out.println("\u2705 Webhook verified");
//             return ResponseEntity.ok(challenge);
//         } else {
//             System.out.println("\u274C Webhook verification failed");
//             return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification failed");
//         }
//     }

//     // STEP 2: Handle Incoming Webhook Messages (POST)
//     @PostMapping
//     public ResponseEntity<String> receiveWebhook(@RequestBody String payload) {
//         System.out.println("\ud83d\udce9 Incoming WhatsApp message: " + payload);

//         try {
//             Map<String, Object> data = new HashMap<>();
//             data.put("timestamp", System.currentTimeMillis());
//             data.put("payload", payload);

//             String docId = firebaseService.saveData("whatsapp_messages", data);
//             System.out.println("\u2705 Message saved to Firestore: " + docId);
//         } catch (Exception e) {
//             System.out.println("\u274C Error saving to Firestore: " + e.getMessage());
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving message");
//         }

//         return ResponseEntity.ok("EVENT_RECEIVED");
//     }
// }
package com.example.whatsapp.controller;

import com.example.whatsapp.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final String VERIFY_TOKEN = "your_verify_token_here";

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping
    public ResponseEntity<String> verifyWebhook(
            @RequestParam("hub.mode") String mode,
            @RequestParam("hub.verify_token") String token,
            @RequestParam("hub.challenge") String challenge) {

        if ("subscribe".equals(mode) && VERIFY_TOKEN.equals(token)) {
            System.out.println("✅ Webhook verified");
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Verification failed");
        }
    }

    @PostMapping
    public ResponseEntity<String> receiveWebhook(@RequestBody String payload) {
        System.out.println("📩 Incoming WhatsApp message: " + payload);
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("timestamp", System.currentTimeMillis());
            data.put("payload", payload);

            String docId = firebaseService.saveData("whatsapp_messages", data);
            System.out.println("✅ Message saved to Firestore: " + docId);
        } catch (Exception e) {
            System.out.println("❌ Error saving to Firestore: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving message");
        }

        return ResponseEntity.ok("EVENT_RECEIVED");
    }
}
