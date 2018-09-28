package com.hsbc.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnClass(WebDriver.class)
@EnableConfigurationProperties(WebDriverConfigurationProperties.class)
@Import({ChromeDriverFactory.class, FirefoxDriverFactory.class, SafariDriverFactory.class})
public class WebDriverAutoConfiguration {

    @Primary
    @Bean (destroyMethod = "quit")
    @ConditionalOnMissingBean(WebDriver.class)
    public WebDriver webDriver(
            FirefoxDriverFactory firefoxDriverFactory,
            SafariDriverFactory safariDriverFactory,
            ChromeDriverFactory chromeDriverFactory
    ){
        WebDriver driver = firefoxDriverFactory.getObject();

        if(driver == null){
            driver = safariDriverFactory.getObject();
        }

        if(driver == null){
            driver = chromeDriverFactory.getObject();
        }

        if(driver == null){
            driver = new HtmlUnitDriver();
        }
        return driver;
    }

}

class FirefoxDriverFactory implements ObjectFactory<FirefoxDriver>{

    private WebDriverConfigurationProperties properties;

    FirefoxDriverFactory(WebDriverConfigurationProperties properties){
        this.properties = properties;
    }

    @Override
    public FirefoxDriver getObject() throws BeansException {
        if(properties.getFirefox().isEnabled()){
            try{
                return new FirefoxDriver();
            }catch (WebDriverException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}

class SafariDriverFactory implements ObjectFactory<SafariDriver>{

    private WebDriverConfigurationProperties properties;

    SafariDriverFactory(WebDriverConfigurationProperties properties){
        this.properties = properties;
    }

    @Override
    public SafariDriver getObject() throws BeansException {
        if(properties.getSafari().isEnabled()){
            try{
                return new SafariDriver();
            }catch (WebDriverException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}

class ChromeDriverFactory implements ObjectFactory<ChromeDriver>{

    private WebDriverConfigurationProperties properties;

    ChromeDriverFactory(WebDriverConfigurationProperties properties){
        this.properties = properties;
    }

    @Override
    public ChromeDriver getObject() throws BeansException {
        if(properties.getChrome().isEnabled()){
            try{
                return new ChromeDriver();
            }catch (WebDriverException e){
                e.printStackTrace();
            }
        }
        return null;
    }
}