package fun.fanyu.mygpt.config;

import com.unfbx.chatgpt.OpenAiClient;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @version V3.0
 * @Title: ThreadCofig
 * @Description: 描述
 * @author: Trident
 * @date 2023/4/10 2:05 PM
 */
@Configuration
public class ThreadConfig {

    @Resource
    GptConfig config;

    @Bean
    public OpenAiClient openAiClient() {
        return OpenAiClient.builder()
                .apiKey(Arrays.asList(config.getApiKey()))
                .build();
    }
}
