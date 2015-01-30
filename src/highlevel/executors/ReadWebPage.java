package highlevel.executors;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ReadWebPage {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<List<String>> future = executor
				.submit(new ReadWebPageCallable());

		try {
			List<String> lines = future.get(5, TimeUnit.SECONDS);
			for (String line : lines)
				System.out.println(line);
		} catch (InterruptedException e) {
			System.err.println("Callable through exception: " + e.getMessage());
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.err.println("Callable through exception: " + e.getMessage());
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.err.println("URL not responding");
			e.printStackTrace();
		}

		executor.shutdown();
	}
}

class ReadWebPageCallable implements Callable<List<String>> {

	@Override
	public List<String> call() throws Exception {
		List<String> lines = new ArrayList<>();
		URL url = new URL("http://www.vk.com");
		HttpURLConnection connection;
		InputStreamReader inputStreamReader;
		BufferedReader bufferedReader;
		String line;

		connection = (HttpURLConnection) url.openConnection();
		inputStreamReader = new InputStreamReader(connection.getInputStream());
		bufferedReader = new BufferedReader(inputStreamReader);

		while ((line = bufferedReader.readLine()) != null)
			lines.add(line);
		return lines;
	}

}
