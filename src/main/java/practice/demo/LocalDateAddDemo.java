package practice.demo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

/**
 * @author Jay Yang
 * @date 2023/8/30
 */
public class LocalDateAddDemo {

    public static void main(String[] args) {

        Random random = new Random();

        int[] cardTypeAmountOptions = {2000, 5000, 10_000, 20_000, 50_000};

        LocalDate startDate = LocalDate.of(2021, Month.AUGUST, 1);
        while (startDate.isBefore(LocalDate.now())) {

            int cardTypeAmount = cardTypeAmountOptions[random.nextInt(cardTypeAmountOptions.length)];
            int amount = Math.min(random.nextInt(cardTypeAmount) / 100 * 100 + 1000, cardTypeAmount);

            System.out.println(startDate + "充值" + amount + "元，卡项：" + cardTypeAmount + "卡");

            int randomNextDay = random.nextInt(10 + random.nextInt(50));
            startDate = startDate.plusDays(randomNextDay);
        }

    }

}
