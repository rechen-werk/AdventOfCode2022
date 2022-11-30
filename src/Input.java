import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Input {
	private final String cookie;

	public Input(String cookie) {
		this.cookie = cookie;
	}

	String get(int day) {
		Path input = Path.of("res").resolve("december_"+day).resolve("input.txt");
		try {
			return Files.readString(input);
		} catch (IOException e) {
			try {
				Process process = Runtime.getRuntime().exec("curl --cookie " + cookie + " https://adventofcode.com/2022/day/" + day + "/input");
				process.waitFor();
				String content = new String(process.getInputStream().readAllBytes());
				Files.createFile(input);
				Files.writeString(input, content);
				return content;
			} catch (IOException | InterruptedException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
	List<String> lines(int day) {
		return Arrays.stream(get(day).split("\n")).toList();
	}

	int[] ints(int day) {
		return Arrays.stream(get(day).split("\n")).mapToInt(Integer::parseInt).toArray();
	}


}
