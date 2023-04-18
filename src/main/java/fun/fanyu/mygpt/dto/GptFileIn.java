package fun.fanyu.mygpt.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version V3.0
 * @Title: GptIn
 * @Description: 描述
 * @author: Trident
 * @date 2023/4/13 3:43 PM
 */

@Data
public class GptFileIn {
    private String msg;
    private MultipartFile file;
}
