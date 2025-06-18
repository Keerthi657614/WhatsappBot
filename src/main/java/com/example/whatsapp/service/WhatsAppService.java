// package com.example.whatsapp.service;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.web.client.RestTemplate;

// @Service
// public class WhatsAppService {

//     private final String PHONE_NUMBER_ID = "700331663158980";
//     private final String ACCESS_TOKEN = "EAAKGFmlVZCuoBO9mB8ZAzW0NZAaTnKhBRPdZCuc9egviSsTvFZCV8xIvQMF2mfXiorcYSMJaHoo2V1D1oXopZCDMvpzjWnmxOPbqloROgffkb1nqhq9RxhZBzn06p0ZAJ45mOZCDaVTkka2C1IRHp9tTqc4YDpAWTf1YciBC44JFgkNNZBoTqWkEnoGJOhvZATZATkwNFhAPjl1UPFfQ12BilGnhr5ZCbNnbrFzFc6nYZD";

//     public String sendMessage(String to, String message) {
//         RestTemplate restTemplate = new RestTemplate();

//         String url = "https://graph.facebook.com/v18.0/" + PHONE_NUMBER_ID + "/messages";

//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_JSON);
//         headers.setBearerAuth(ACCESS_TOKEN);

//         Map<String, Object> body = new HashMap<>();
//         body.put("messaging_product", "whatsapp");
//         body.put("to", to);
//         body.put("type", "text");
//         body.put("text", Map.of("body", message));

//         HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

//         try {
//             ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
//             return "Message sent! Response: " + response.getBody();
//         } catch (Exception e) {
//             return "Error sending message: " + e.getMessage();
//         }
//     }

//     public Object getMessages() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getMessages'");
//     }
// }
package com.example.whatsapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class WhatsAppService {

    private final String PHONE_NUMBER_ID = "700331663158980";
    private final String ACCESS_TOKEN = "EAAKGFmlVZCuoBO9mB8ZAzW0NZAaTnKhBRPdZCuc9egviSsTvFZCV8xIvQMF2mfXiorcYSMJaHoo2V1D1oXopZCDMvpzjWnmxOPbqloROgffkb1nqhq9RxhZBzn06p0ZAJ45mOZCDaVTkka2C1IRHp9tTqc4YDpAWTf1YciBC44JFgkNNZBoTqWkEnoGJOhvZATZATkwNFhAPjl1UPFfQ12BilGnhr5ZCbNnbrFzFc6nYZD";

    public String sendMessage(String message, String to) {
        try {
            URL url = new URL("https://graph.facebook.com/v18.0/" + PHONE_NUMBER_ID + "/messages");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN); // ✅ FIXED HERE
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = String.format("""
                    {
                      "messaging_product": "whatsapp",
                      "to": "%s",
                      "type": "text",
                      "text": { "body": "%s" }
                    }
                    """, to, message);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
            }

            int responseCode = conn.getResponseCode();
            return "Sent with response code: " + responseCode;
        } catch (Exception e) {
            return "Error sending: " + e.getMessage();
        }
    }

    public List<Map<String, Object>> getMessages() {
        try {
            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("whatsapp_messages").get();
            List<QueryDocumentSnapshot> docs = future.get().getDocuments();

            List<Map<String, Object>> messages = new ArrayList<>();
            for (QueryDocumentSnapshot doc : docs) {
                messages.add(doc.getData());
            }
            return messages;
        } catch (Exception e) {
            return List.of(Map.of("error", e.getMessage()));
        }
    }
}
