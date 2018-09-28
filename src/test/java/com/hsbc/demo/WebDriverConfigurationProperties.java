package com.hsbc.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("com.greglturnquist.webdriver")
public class WebDriverConfigurationProperties {

    private Firefox firefox = new Firefox();
    private Safari safari = new Safari();
    private Chrome chrome = new Chrome();

    @Data
    static class Firefox{
        private boolean enabled = true;
    }

    @Data
    static class Safari{
        private boolean enabled = true;
    }

    @Data
    static class Chrome{
        private boolean enabled = true;
    }
}
