package fun.fanyu.mygpt.config;

import com.unfbx.chatgpt.OpenAiClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public OpenAiClient openAiClient() {
        return OpenAiClient.builder()
                .apiKey(Arrays.asList("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",
                        "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"))
                //自定义key的获取策略：默认KeyRandomStrategy
                //.keyStrategy(new KeyRandomStrategy())
//                .keyStrategy(new FirstKeyStrategy())
                //自己做了代理就传代理地址，没有可不不传
//                .apiHost("https://自己代理的服务器地址/")
                .build();
    }
}
