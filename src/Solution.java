import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    static HashMap<Integer, User> userMap = new HashMap<>();
    PriorityQueue<orderSell> sellQueue = new PriorityQueue<>();
    List<orderBuy> buyList = new ArrayList<>();

    public String[] solution(String[] req_id, int[][] req_info) {

        for (int i = 0; i < req_id.length; i++) {
            userMap.put(i, new User(req_id[i]));
        }

        for (int i = 0; i < req_info.length; i++) {
            if(req_info[i][0] == 1) {
                sellQueue.offer(new orderSell(i, req_info[i][1], req_info[i][2]));
            }
            else {
                buyList.add(new orderBuy(i, req_info[i][1], req_info[i][2]));
            }
        }

        //orderBuy 기준으로 처리해야 한다.
        for (int i = 0; i < buyList.size(); i++) {
            orderBuy nowBuy = buyList.get(i);
            while (nowBuy.amount != 0) {

                if(!sellQueue.isEmpty()) {
                    orderSell minPrice = sellQueue.poll();
                    if(minPrice.price <= nowBuy.price) {
                        if(minPrice.amount > nowBuy.amount) {
                            deal(userMap.get(nowBuy.userId), userMap.get(minPrice.userId), nowBuy, minPrice);
                            //minPrice 개수 줄여서 queue 에 추가
                            minPrice.amount -= nowBuy.amount;
                            nowBuy.amount = 0;
                            sellQueue.offer(minPrice);
                        }
                        //개수가 같다.
                        else if (minPrice.amount == nowBuy.amount){
                            deal(userMap.get(nowBuy.userId), userMap.get(minPrice.userId), nowBuy, minPrice);
                            nowBuy.amount = 0;
                        }
                        //개수가 모자르다.
                        else {
                            deal(userMap.get(nowBuy.userId), userMap.get(minPrice.userId), nowBuy, minPrice);
                            //추가 구매를 해야 한다.
                            nowBuy.amount -= minPrice.amount;
                        }
                    }
                    //적당한 가격이 없다면 가격 등록을 해야 한다
                    buyList.add(new orderBuy(nowBuy.userId, nowBuy.amount, nowBuy.price));
                    nowBuy.amount = 0;
                } else {
                    //적당한 가격이 없다면 가격 등록을 해야 한다
                    buyList.add(new orderBuy(nowBuy.userId, nowBuy.amount, nowBuy.price));
                    nowBuy.amount = 0;
                }
            }
        }
        String[] answer;
        answer = new String[req_id.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = userMap.get(i).toString();
        }

        return answer;
    }

    public static void deal(User sellUser, User buyUser, orderBuy orderBuy, orderSell orderSell) {
        //구매 완료
        buyUser.gold += orderBuy.amount;
        buyUser.silver -= orderBuy.amount * orderBuy.price;
        userMap.put(orderBuy.userId, buyUser);

        //판매 완료
        sellUser.gold -= orderSell.amount;
        sellUser.silver += orderBuy.amount * orderBuy.price;
        userMap.put(orderSell.userId, sellUser);


    }


    public static class orderBuy implements Comparable<orderBuy>{
        int userId;
        int price;
        int amount;

        public orderBuy(int userId, int amount, int price) {
            this.userId = userId;
            this.price = price;
            this.amount = amount;
        }

        @Override
        public int compareTo(orderBuy o) {
            return this.price - o.price;
        }
    }

    public static class orderSell implements Comparable<orderSell>{
        int userId;
        int price;
        int amount;

        public orderSell(int userId, int amount, int price) {
            this.userId = userId;
            this.price = price;
            this.amount = amount;
        }

        @Override
        public int compareTo(orderSell o) {
            return this.price - o.price;
        }
    }


    public static class User {
        String userName;
        int silver;
        int gold;

        public User(String userName) {
            this.userName = userName;
            this.silver = 0;
            this.gold = 0;
        }

        @Override
        public String toString() {
            String goldString = String.valueOf(gold);
            if(gold > 0) {
                goldString = "+" + gold;
            }
            String silverString = String.valueOf(silver);
            if(silver > 0) {
                silverString = "+" + silver;
            }
            return userName + " " + goldString + silverString;
        }
    }
}