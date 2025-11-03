package domain;

import java.util.Map;

public record LottoResult(Map<Rank, Integer> results) {
    public int getCount(Rank rank) {
        return results.getOrDefault(rank, 0);
    }

    public double calculateProfitRate(int purchaseAmount) {
        return (double) getTotalPrizeMoney() / purchaseAmount;
    }

    public int getTotalPrizeMoney() {
        return results.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrizeMoney() * entry.getValue())
                .sum();
    }
}