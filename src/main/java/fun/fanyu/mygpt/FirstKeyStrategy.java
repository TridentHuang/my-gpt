package fun.fanyu.mygpt;

import com.unfbx.chatgpt.function.KeyStrategyFunction;

import java.util.List;

/**
 * @version V3.0
 * @Title: FirstKeyStrategy
 * @Description: 描述
 * @author: Trident
 * @date 2023/4/7 10:21 AM
 */
class FirstKeyStrategy implements KeyStrategyFunction<List<String>, String> {

    /**
     * 总是使用第一个
     * @param keys
     * @return
     */
    @Override
    public String apply(List<String> keys) {
        return keys.get(0);
    }
}
