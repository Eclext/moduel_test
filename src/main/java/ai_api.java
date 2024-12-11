import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ai_api {

    public static void main(String[] args) {
        try {
            // API的URL
            String url = "https://open.bigmodel.cn/api/paas/v4/chat/completions";

            // 创建URL对象
            URL obj = new URL(url);

            // 创建HttpURLConnection对象
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // 设置请求方法为POST
            con.setRequestMethod("POST");

            // 启用输入输出流
            con.setDoOutput(true);
            con.setDoInput(true);

            // 设置请求头
            String apiKey = "cb3ff5a47abcd640adc0c429b2ad3117.PDzNzb1LTyqQmrjz";  // 替换为实际的API密钥
            con.setRequestProperty("Authorization", "Bearer " + apiKey);  // 检查API密钥是否正确
            con.setRequestProperty("Content-Type", "application/json");

            // 构建请求体，传递模型和消息内容
            String jsonInputString = "{\n" +
                    "    \"model\": \"glm-4\",\n" +
                    "    \"messages\": [\n" +
                    "        {\n" +
                    "            \"role\": \"user\",\n" +
                    "            \"content\": \"你认识贾建亮吗\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";

            // 发送请求数据
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 获取响应码
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // 如果401错误，输出提示信息
            if (responseCode == 401) {
                System.out.println("Error:Please check your API Key.");
            }

            // 读取响应内容
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // 输出返回的响应内容
                System.out.println("Response: " + response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//史大贵

