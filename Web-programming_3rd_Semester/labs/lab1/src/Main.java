import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.fastcgi.FCGIInterface;

public class Main {
    
    public static void main(String[] args) {
        

        FCGIInterface fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            try {
                long startTime = System.nanoTime();
                String content = """
                {
                    "status": "%s",
                    "time": "%s"
                }
                """;
                float[] floats = readRequestBody();
                //float x = floats[0];
                //float y = floats[1];
                //float r = floats[2];
                boolean status;
                if(floats.length == 0){
                    status = false;
                    content = content.formatted("false");
                }else{
                    status = true;
                    content = content.formatted("true");
                }
                
                long endTime = System.nanoTime();
                long executeTime = (endTime - startTime);
                content = content.formatted(status, String.format("%.3f",(double) executeTime/1000000));

                String response = """
                        HTTP/2 200 OK
                        Content-Type: application/json
                        Content-Length: %d
                        
                        %s
                        
                        """.formatted(content.getBytes(StandardCharsets.UTF_8).length, content); 

                System.out.println(response);
            } catch (Exception e) {
                throw e;
            }
        }
    }


    private static float[] readRequestBody(){
        try{
            FCGIInterface.request.inStream.fill();
            var contentLength = FCGIInterface.request.inStream.available();
            var buffer = ByteBuffer.allocate(contentLength);
            var readBytes =
                    FCGIInterface.request.inStream.read(buffer.array(), 0,
                            contentLength);
            var requestBodyRaw = new byte[readBytes];
            buffer.get(requestBodyRaw);
            buffer.clear();
            String requestString = new String(requestBodyRaw, StandardCharsets.UTF_8);
            String[] elements = requestString.split(":");
            float[] floats = new float[3];
            if(elements.length!=4){
                return new float[]{-20,-20,-20};
            }
            try{
                floats[0] = Float.parseFloat((elements[1].split(",")[0]));
                floats[1] = Float.parseFloat((elements[2].split(",")[0]));
                floats[2] = Float.parseFloat(elements[3].split("}")[0].replace("}", ""));
                return floats;
            }catch (Exception e){
                return new float[]{-20,-20,-20};
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}