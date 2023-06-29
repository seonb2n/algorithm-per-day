import java.util.*;

class Solution {

    // 이전 room 번호가 몇번 방을 줬는지는 넣어놓자
    static Map<Long, Long> lastRoomMap = new HashMap<>();

    public static void main(String[] args) {
        long[] rooms = {1,3,4,1,3,1};
        solution(10, rooms);
    }

    public static long[] solution(long k, long[] room_number) {
        // 메모제이션?
        // 모든 숫자에 대해서
        long[] answer = new long[room_number.length];

        for (int i = 0; i < room_number.length; i++) {
            long nowRoom = room_number[i];
            answer[i] = findRoom(nowRoom);
        }

        return answer;
    }

    public static long findRoom(long nowNumber) {
        if (!lastRoomMap.containsKey(nowNumber)) {
            lastRoomMap.put(nowNumber, nowNumber+1);
            return nowNumber;
        }
        else {
            long nextRoom = lastRoomMap.get(nowNumber);
            long emptyRoom = findRoom(nextRoom);
            lastRoomMap.put(nextRoom, emptyRoom+1);
            return emptyRoom;
        }
    }
}