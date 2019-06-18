package pl.edu.pb;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.Thread.sleep;

public class StatAggregatorClient {
  private static ObjectMapper objectMapper = new ObjectMapper();

  public static void main(String[] args) throws Exception {
    HttpClient client = HttpClient.newHttpClient();

    String mode = args[0];
    Integer totalMemory = Integer.valueOf(args[1]);
    String hostname = args[2];

    while (true) {
      HttpRequest request =
          HttpRequest.newBuilder()
              .uri(URI.create("http://localhost:8080/stats"))
              .header("Content-Type", "application/json")
              .POST(HttpRequest.BodyPublishers.ofString(getBody(mode, totalMemory, hostname)))
              .build();
      HttpResponse<Void> send = client.send(request, HttpResponse.BodyHandlers.discarding());
      System.out.println(send.statusCode());

      sleep(1000);
    }
  }

  private static String getBody(String mode, Integer totalMemory, String hostname) throws Exception {
    StatsDto statsDto = new StatsDto();
    statsDto.setMemoryTotal(totalMemory);
    statsDto.setHostname(hostname);

    double min = 0;
    double max = 1;
    if ("low".equals(mode)) {
      min = 0.05;
      max = 0.25;
    }
    if ("medium".equals(mode)) {
      min = 0.3;
      max = 0.6;
    }
    if ("high".equals(mode)) {
      min = 0.65;
      max = 0.99;
    }
    double randomCpu = min + Math.random() * (max - min);
    double randomMem = min + Math.random() * (max - min);
    statsDto.setCpuUsage(randomCpu * 100);
    statsDto.setMemoryUsed(randomMem * totalMemory);
    return objectMapper.writeValueAsString(statsDto);
  }
}
