package leetcode.p535;

import java.util.HashMap;
import java.util.Map;

public class Codec {
    private final Map<Integer, String> database = new HashMap<>();
    private int id = 0;
    private static final String shortUrlPrefix = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        id++;
        database.put(id, longUrl);

        return shortUrlPrefix + id;
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