package baseline.version3.springboot.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

@Slf4j
@Component
public class UUIDGenerationUtils {
    private final static int LENGTH_20_LONG_RADIX = 9;
    private final static int LENGTH_10_INT_RADIX = 9;

    // 10자리의 UUID 생성
    public static String makeShortUUID() {
        UUID uuid = UUID.randomUUID();
        return parseToShortUUID(uuid.toString());
    }

    public static String parseToIntRadixUUID(String uuid, int radix) {
        int l = ByteBuffer.wrap(uuid.getBytes()).getInt();
        return Integer.toString(l, radix);
    }

    public static String parseToLongRadixUUID(String uuid, int radix) {
        long l = ByteBuffer.wrap(uuid.getBytes()).getLong();
        return Long.toString(l, radix);
    }

    // 파라미터로 받은 값을 10자리의 UUID로 변환
    public static String parseToShortUUID(String uuid) {
        return parseToIntRadixUUID(uuid, LENGTH_10_INT_RADIX);
    }

    // 파라미터로 받은 값을 20자리의 UUID로 변환
    public static String parseToLongUUID(String uuid) {
        return parseToLongRadixUUID(uuid, LENGTH_20_LONG_RADIX);
    }
}
