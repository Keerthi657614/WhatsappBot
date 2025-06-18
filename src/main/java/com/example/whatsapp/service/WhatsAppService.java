package com.example.whatsapp.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class WhatsAppService {

    private final String PHONE_NUMBER_ID = "700331663158980";

    @Value("${ACCESS_TOKEN}")
    private String ACCESS_TOKEN;

    public String sendMessage(String message, String to) {
        try {
            URL url = new URL("https://graph.facebook.com/v18.0/" + PHONE_NUMBER_ID + "/messages");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
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
