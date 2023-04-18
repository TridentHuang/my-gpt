package fun.fanyu.mygpt.controller;

import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.chat.ChatCompletion;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import com.unfbx.chatgpt.entity.chat.Message;
import com.unfbx.chatgpt.entity.common.Choice;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;
import com.unfbx.chatgpt.entity.edits.Edit;
import com.unfbx.chatgpt.entity.edits.EditResponse;
import com.unfbx.chatgpt.entity.images.ImageResponse;
import com.unfbx.chatgpt.entity.whisper.Transcriptions;
import com.unfbx.chatgpt.entity.whisper.Whisper;
import com.unfbx.chatgpt.entity.whisper.WhisperResponse;
import fun.fanyu.mygpt.dto.GptFileIn;
import fun.fanyu.mygpt.dto.GptIn;
import fun.fanyu.mygpt.dto.GptOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
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
//    @CrossOrigin(origins = "*", maxAge = 3600)
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
        gptOut.setMsg(msg[0]);
        System.out.println(msg[0]);
        return gptOut;
    }

    @PostMapping("/change_code")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public GptOut changeCode(@RequestBody GptIn gptIn) {
        Edit edit = Edit.builder().input(
                gptIn.getMsg()
        ).instruction("帮我修改这个java代码").model(Edit.Model.CODE_DAVINCI_EDIT_001.getName()).build();
        EditResponse editResponse = openAiClient.edit(edit);
        System.out.println(editResponse);
        final String[] msg = {""};
        Arrays.stream(editResponse.getChoices()).forEach(
                e -> {
                    msg[0] = msg[0] + e.getText();
                }
        );
        GptOut gptOut = new GptOut();
        gptOut.setMsg(msg[0]);
        return gptOut;
    }

    @PostMapping("/get_img")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public GptOut getImg(@RequestBody GptIn gptIn) {
        ImageResponse imageResponse = openAiClient.genImages(gptIn.getMsg());
        System.out.println(imageResponse);
        String url = imageResponse.getData().get(0).getUrl();
        GptOut gptOut = new GptOut();
        gptOut.setMsg(url);
        return gptOut;
    }

    @PostMapping("/radio_text")
    public GptOut radioText(@RequestParam("file") MultipartFile multipartFile) {
        //文件上传前的名称
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        OutputStream out = null;
        try {
            //获取文件流，以文件流的方式输出到新文件
            out = new FileOutputStream(file);
            byte[] ss = multipartFile.getBytes();
            for (int i = 0; i < ss.length; i++) {
                out.write(ss[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Transcriptions transcriptions = Transcriptions.builder()
                .model(Whisper.Model.WHISPER_1.getName())
                .prompt("提示语")
                .language("zh")
                .temperature(0.2)
                .responseFormat(Whisper.ResponseFormat.VTT.getName())
                .build();
        //语音转文字
        WhisperResponse whisperResponse =
                openAiClient.speechToTextTranscriptions(file, transcriptions);
        System.out.println(whisperResponse.getText());
        GptOut gptOut = new GptOut();
        gptOut.setMsg(whisperResponse.getText());
        return gptOut;
    }
}
