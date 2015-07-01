

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetClientPost {

	public static void main(String[] args) {
		testPost();
	}

	public static void testPost() {
		try {

			URL url = new URL("");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			final long l = System.currentTimeMillis();
			final int i = (int) (l % 100);

			String stremail = i + "@coach360.net";
			String input1 = "{\"email\":";
			String input2 = stremail;
			String input3 = ", \"password\":\"aa\", \"password_confirm\":\"aa\", \"name\":\"aa\",\"phone_number\":\"111-123\",\"client_name\":\"aa\",\"client_type\":\"ios\",\"client_specifier\":\"OS\",\"notification_key\":\"APNS\"}";
			String input = input1 + "\"" + input2 + "\"" + input3;
			System.out.println(input);
			// String input = "{\"qty\":100,\"name\":\"iPad 4\"}";

			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			if (conn.getResponseCode() != 400) {
				System.out.println("400" + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			if (conn.getResponseCode() != 500) {
				System.out.println("500" + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}