package ImmutableTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ImmutableTestMain {

    public static void main(String[] args) {

        ArrayList<String> originalList = new ArrayList<String>();
        originalList.add("Hello");
        originalList.add("World");

//        ImmutableList immutableList = new ImmutableList(originalList);
        MutableList mutableList = new MutableList(originalList);

        originalList.add("!");
//
//        System.out.println(immutableList.getList().toString()); //[Hello, World]
//        System.out.println(mutableList.getList().toString()); //[Hello, World, !]
//
//        immutableList.getList().add("!");
//        System.out.println(immutableList.getList().toString()); //[Hello, World]


        ArrayList<MutableText> originText = new ArrayList<>();
        MutableText text1 = new MutableText("Hello");
        MutableText text2 = new MutableText("World");
        originText.add(text1);
        originText.add(text2);

        ImmutableList immutableList2 = new ImmutableList(originText);
        MutableList mutableList2 = new MutableList(originText);

        originText.add(new MutableText("!"));

        System.out.println(immutableList2.getList().toString()); //[Hello, World]
        System.out.println(mutableList2.getList().toString()); //[Hello, World, !]

        text1.setText("Hi");
        System.out.println(immutableList2.getList().toString()); //[Hello, World]
        System.out.println(mutableList2.getList().toString()); //[Hi, World, !]

        //unmodifiableList 사용
        List<MutableText> unmodifiableTextList = Collections.unmodifiableList(originText); // [Hi, World, !]
//        originText.add(new MutableText("See"));
//        System.out.println(unmodifiableTextList.toString()); //[Hi, World, !, See]
//        unmodifiableTextList.add(new MutableText("You")); // Exception!

//        originText.get(0).setText("Hello");
//        System.out.println(unmodifiableTextList.toString()); //[Hi, World, !, See]
    }

}
