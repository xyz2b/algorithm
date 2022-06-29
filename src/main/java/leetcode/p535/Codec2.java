package leetcode.p535;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 发号器
// 对于每个longUrl发一个唯一不重复的号码，即每个longUrl对应到唯一一个号码，将号码和longUrl的映射关系存到数据库中
// 实际应用中就是用一个kv数据库来存
public class Codec2 {
    private final Map<Integer, String> database = new HashMap<>();
    private final Random random = new Random();
    private static final String shortUrlPrefix = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int key;
        while (true) {
            key = random.nextInt();
            if (!database.containsKey(key)) {
                break;
            }
        }

        database.put(key, longUrl);
        return shortUrlPrefix + key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        // lastIndexOf，找到最后一个str在字符串中的位置
        // 我们需要最后一个"/"之后的内容，就是id的字符串，所以需要+1
        int p = shortUrl.lastIndexOf("/") + 1;
        // 获取最后一个"/"之后的内容，并转成int，这就是id
        int id = Integer.parseInt(shortUrl.substring(p));
        return database.get(id);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));