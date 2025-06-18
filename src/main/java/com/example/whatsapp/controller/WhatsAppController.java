// // // package com.example.whatsapp.controller;

// // // import org.springframework.beans.factory.annotation.Autowired;
// // // import org.springframework.web.bind.annotation.GetMapping;
// // // import org.springframework.web.bind.annotation.PostMapping;
// // // import org.springframework.web.bind.annotation.RequestMapping;
// // // import org.springframework.web.bind.annotation.RequestParam;
// // // import org.springframework.web.bind.annotation.RestController;

// // // import com.example.whatsapp.service.WhatsAppService;

// // // @RestController
// // // @RequestMapping("/api")
// // // public class WhatsAppController {

// // //     @Autowired
// // //     private WhatsAppService service;

// // //     @PostMapping("/send")
// // //     public String sendMessage(@RequestParam String to, @RequestParam String message) {
// // //         return service.sendMessage(to, message);
// // //     }

// // //     @GetMapping("/health")
// // //     public String check() {
// // //         return "Bot running!";
// // //     }
// // // }
// // // File: WhatsAppController.java
// // package com.example.whatsapp.controller;

// // import com.example.whatsapp.model.MessageRequest;
// // import com.example.whatsapp.service.WhatsAppService;
// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.*;

// // @RestController
// // @RequestMapping("/api")
// // public class WhatsAppController {

// //     @Autowired
// //     private WhatsAppService whatsAppService;

// //     @PostMapping("/send")
// //     public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {
// //         whatsAppService.sendMessage(request.getTo(), request.getMessage());
// //         return ResponseEntity.ok("Message sent to " + request.getTo());
// //     }
// // }
// package com.example.whatsapp.controller;

// import com.example.whatsapp.model.MessageRequest;
// import com.example.whatsapp.service.WhatsAppService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api")
// public class WhatsAppController {

//     @Autowired
//     private WhatsAppService whatsAppService;

//     @PostMapping("/send")
//     public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {
//         whatsAppService.sendMessage(request.getTo(), request.getMessage());
//         return ResponseEntity.ok("Message sent to " + request.getTo());
//     }
// }
package com.example.whatsapp.controller;

import com.example.whatsapp.service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class WhatsAppController {

    @Autowired
    private WhatsAppService whatsAppService;

    @PostMapping("/send")
    public String sendMessage() {
        return whatsAppService.sendMessage("Hello from Java Chatbot!", "919849357344"); // Your number here
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "✅ Server is running";
    }

    @GetMapping("/messages")
    public Object getMessages() {
        return whatsAppService.getMessages();
    }
}
