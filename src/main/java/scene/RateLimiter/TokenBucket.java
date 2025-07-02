package scene.RateLimiter;

import java.util.concurrent.TimeUnit;

public class TokenBucket {

    private final long capacity;        // 桶的容量（最大令牌数）
    private double currentTokens;      // 当前令牌数量
    private long lastRefillTime;       // 上次补充令牌的时间（纳秒）
    private final double refillRate;   // 每秒生成的令牌数

    public TokenBucket(long capacity, double tokensPerSecond) {
        this.capacity = capacity;
        this.refillRate = tokensPerSecond;
        this.currentTokens = capacity;
        this.lastRefillTime = System.nanoTime();
    }

    public synchronized boolean tryAcquire(int tokens) {
        refillTokens(); // 获取令牌前先补充令牌
        if (currentTokens >= tokens) {
            currentTokens -= tokens;
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long now = System.nanoTime();
        // 计算经过的时间（秒）
        double elapsedTime = (now - lastRefillTime) / 1e9;

        // 计算这段时间内应生成的令牌数
        double tokensToAdd = elapsedTime * refillRate;

        if (tokensToAdd > 0) {
            currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
            lastRefillTime = now; // 更新最后补充时间
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建令牌桶：容量10，速率2个/秒
        TokenBucket bucket = new TokenBucket(1, 2);

        // 测试连续获取令牌
        for (int i = 1; i <= 15; i++) {
            boolean acquired = bucket.tryAcquire(1);
            System.out.printf("Attempt %d: %s%n", i, acquired ? "成功获取令牌" : "被限流");

            Thread.sleep(333);
        }
    }
}
