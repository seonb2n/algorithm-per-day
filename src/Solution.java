import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {

        HashMap<String, PriorityQueue<Song>> genreMap = new HashMap<>();
        HashMap<String, Integer> sumMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            var pq = genreMap.getOrDefault(genres[i], new PriorityQueue<Song>());
            pq.offer(new Song(i, plays[i]));
            genreMap.put(genres[i], pq);

            var number = sumMap.getOrDefault(genres[i], 0);
            number += plays[i];
            sumMap.put(genres[i], number);
        }

        Genre[] genresArr = new Genre[genreMap.keySet().size()];


        int i = 0;
        for (String key : genreMap.keySet()) {
            genresArr[i] = new Genre(key, sumMap.get(key));
            i++;
        }
        Arrays.sort(genresArr);

        List<Integer> answerList = new ArrayList<Integer>();

        for (int j = 0; j < genresArr.length; j++) {
            String key = genresArr[j].genre;
            answerList.add(genreMap.get(key).poll().id);
            if (!genreMap.get(key).isEmpty()) {
                answerList.add(genreMap.get(key).poll().id);
            }
        }

        int[] answer = new int[answerList.size()];
        for (int j = 0; j < answerList.size(); j++) {
            answer[j] = answerList.get(j);
        }

        return answer;
    }

    class Genre implements Comparable<Genre> {
        String genre;
        int count;

        public Genre(String genre, int count) {
            this.genre = genre;
            this.count = count;
        }

        @Override
        public int compareTo(Genre o) {
            return o.count - this.count;
        }
    }

    class Song implements Comparable<Song> {
        int id;
        int playCount;

        public Song(int id, int playCount) {
            this.id = id;
            this.playCount = playCount;
        }

        @Override
        public int compareTo(Song o) {
            return o.playCount - this.playCount;
        }
    }
}