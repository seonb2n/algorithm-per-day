import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


    static List<Person> personList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCaseNumber = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < testCaseNumber; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
            personList.add(
                    new Person(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()))
            );
        }

        for (Person person : personList) {
            person.rank = getRank(person);
            sb.append(person.rank + " ");
        }

        System.out.println(sb);



    }

    private static int getRank(Person person) {
        int result = 1;

        for (int i = 0; i < personList.size(); i++) {
            if(person.height < personList.get(i).height && person.weight < personList.get(i).weight) {
                result++;
            }
        }

        return result;
    }
}

class Person {

    public Person(int weight, int height) {
        this.weight = weight;
        this.height = height;
        rank = 0;
    }

    int weight;
    int height;
    int rank;

}
