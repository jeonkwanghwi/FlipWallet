package Money.Saver.FW.response;

import org.springframework.http.ResponseEntity;

public class ResponseBody {

    public static ResponseEntity<Object> successResponse() {
        SuccessBody successBody = new SuccessBody();
        return ResponseEntity.ok(successBody);
    }

    public static ResponseEntity<Object> successResponse(Object data) {
        SuccessBody successBody = new SuccessBody(data);
        return ResponseEntity.ok(successBody);
    }
}
