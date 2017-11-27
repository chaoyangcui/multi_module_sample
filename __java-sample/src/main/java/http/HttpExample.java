package http;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/28 17:15
 * Desc    Setting | Editor | File and Code Templates
 */
public class HttpExample {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InterruptedException {
        /*String url = "https://wx.hwabaotrust.com/api/v1/is.bound?openid=ofLyRjuNziT6A8Xr4FGiVbQeauSE";
        try {

            try (InputStream is = HttpClient.New(new URL(url), false).getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                System.out.println(builder.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*HttpClient client = HttpClient.newBuilder()
                .sslContext(SSLContext.getDefault())
                .version(HttpClient.Version.HTTP_2)
                .build();
        URI uri = URI.create("https://www.baidu.com/");
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString(Charset.forName("UTF-8")));
        System.out.println(response.body());*/
    }

}
