package fun.fanyu.mygpt.controller;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import fun.fanyu.mygpt.dto.GptIn;
import fun.fanyu.mygpt.dto.GptOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @version V3.0
 * @Title: DemoController
 * @Description: 描述
 * @author: Trident
 * @date 2023/4/14 9:45 AM
 */
@RestController
@Slf4j
public class DemoController {
    @Resource
    private OpenAiClient openAiClient;

    @RequestMapping("/demo")
    public GptOut demo(@RequestParam(value = "msg") String msg) {
        log.info("输入内容：{}", msg);
        CompletionResponse completions = openAiClient.completions(msg);
        final String[] res = {""};
        Arrays.stream(completions.getChoices()).forEach(
                e -> {
                    res[0] = res[0] + e.getText();
                }
        );
        GptOut gptOut = new GptOut();
        gptOut.setMsg(res[0]);
        return gptOut;
    }
}
