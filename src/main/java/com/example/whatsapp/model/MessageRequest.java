// package com.example.whatsapp.model;

// import lombok.Data;

// @Data
// public class MessageRequest {
//     private String from;
//     private String message;
//     public String getTo() {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getTo'");
//     }

//     public String getMessage() {
//         throw new UnsupportedOperationException("Not supported yet.");
//     }
// }
package com.example.whatsapp.model;

public class MessageRequest {
    private String to;
    private String message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
