import no.finn.unleash.DefaultUnleash;
import no.finn.unleash.Unleash;
import no.finn.unleash.UnleashContext;
import no.finn.unleash.util.UnleashConfig;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        UnleashConfig config = UnleashConfig.builder()
                .appName("java-test")
                .instanceId("instance x")
                .unleashAPI("http://localhost:32775/api/")
                .build();

        Unleash unleash = new DefaultUnleash(config);
        while(true) {
            if (unleash.isEnabled("AwesomeFeature",true)) {
                System.out.println("AwesomeFeature enabled");
            } else {
                System.out.println("AwesomeFeature disabled");
            }
            int rand= new Random().nextInt(7);
            UnleashContext unleashContext=UnleashContext.builder()
                    .userId(String.valueOf(rand))
                    .build();
            if (unleash.isEnabled("AwesomeFeature",unleashContext)) {
                System.out.println("AwesomeFeature enabled for id "+rand);
            } else {
                System.out.println("AwesomeFeature disabled for id "+rand);
            }
            Thread.sleep(2000);
        }
    }

}
