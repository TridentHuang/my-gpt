package fun.fanyu.mygpt.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @version V3.0
 * @Title: GptConfig
 * @Description: 描述
 * @author: Trident
 * @date 2023/4/7 11:10 AM
 */
@Component
@Data
public class GptConfig {
    @Value("${gpt.api_key}")
    private String apiKey;
}
