package qa.commonfuctions;

import qa.utils.ReportUtil;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TelegramBotSender {

    private static final String BOT_TOKEN = "5120397875:AAEy6pvDAaoRABf5RiohOmWrxm5b561INOA";
    private static final String CHAT_ID = "5202542384";

    public static void sendTelegramMessage(String messageText) throws Exception {

        ReportUtil.report(true, "INFO", "-- Function -- Starting -- sendTelegramMessage function ","");

        String message = messageText;

        String urlString = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set up the connection properties
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Encode and write data
            String data = "chat_id=" + CHAT_ID + "&text=" + URLEncoder.encode(messageText, "UTF-8");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.getBytes());
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            conn.disconnect();


        }catch (Exception e) {
            System.out.println("sendTelegramMessage: " + e.getMessage());
            ReportUtil.report( false, "FAIL", "sendTelegramMessage, ",  e.getMessage());
        }

        ReportUtil.report(true, "INFO", "-- Function -- Ending -- sendTelegramMessage function ", "");

    }
}
