package memory.leak;

import com.codahale.metrics.MetricRegistry;
import com.wavefront.dropwizard.metrics.DropwizardMetricsReporter;
import com.wavefront.sdk.common.clients.WavefrontClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class HealthMonitor {
  @Autowired
  private Environment env;

  @PostConstruct
  public void init() {
    String wfClusterURL = env.getProperty("WF_CLUSTER_URL");
    String wfClusterAPI = env.getProperty("WF_CLUSTER_API");
    String hostName = env.getProperty("HOST_NAME");
    String environment = env.getProperty("ENVIRONMENT");
    MetricRegistry metricRegistry = new MetricRegistry();
    DropwizardMetricsReporter.Builder builder = DropwizardMetricsReporter.forRegistry(metricRegistry);
    builder.withSource(hostName);
    builder.withReporterPointTag("env", environment);
    builder.withJvmMetrics();
    WavefrontClient.Builder wfClientBuilder = new WavefrontClient.Builder(wfClusterURL, wfClusterAPI);
    DropwizardMetricsReporter dropwizardMetricsReporter = builder.build(wfClientBuilder.build());
    dropwizardMetricsReporter.start(30, TimeUnit.SECONDS);
  }
}
