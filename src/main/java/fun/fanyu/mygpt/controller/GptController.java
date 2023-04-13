package fun.fanyu.mygpt.controller;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import fun.fanyu.mygpt.dto.GptIn;
import fun.fanyu.mygpt.dto.GptOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @version V3.0
 * @Title: GptController
 * @Description: 描述
 * @author: Trident
 * @date 2023/4/13 3:41 PM
 */
@RestController
@Slf4j
public class GptController {

    @Resource
    private OpenAiClient openAiClient;

    @PostMapping("/chat")
    public GptOut chat(@RequestBody GptIn gptIn) {
        log.info("输入内容：{}", gptIn.getMsg());
        CompletionResponse completions = openAiClient.completions(gptIn.getMsg());
        final String[] msg = {""};
        Arrays.stream(completions.getChoices()).forEach(
                e -> {
                    msg[0] = msg[0] + e.getText();
                }
        );
        GptOut gptOut = new GptOut();
        return gptOut;
    }
}
