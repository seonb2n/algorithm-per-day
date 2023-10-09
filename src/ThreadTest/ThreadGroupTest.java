package ThreadTest;

public class ThreadGroupTest {

    public static void main(String[] args) {
        ThreadGroup main = Thread.currentThread().getThreadGroup();
        ThreadGroup grp1 = new ThreadGroup("Group1");
        ThreadGroup grp2 = new ThreadGroup("Group2");

        ThreadGroup subGrp1 = new ThreadGroup(grp1, "SubGroup1");

        grp1.setMaxPriority(3);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        };

        new Thread(grp1, r, "th1").start();
        new Thread(subGrp1, r, "th2").start();
        new Thread(grp2, r, "th3").start();

        System.out.println(">> List of ThreadGroup : " + main.getName()
        +", ActiveThreadGroup: " + main.activeGroupCount() + ", Active Thread: " + main.activeCount());
        main.list();
//        java.lang.ThreadGroup[name=main,maxpri=10]
//        Thread[main,5,main]
//        Thread[Monitor Ctrl-Break,5,main]
//        java.lang.ThreadGroup[name=Group1,maxpri=3]
//        Thread[th1,3,Group1]
//        java.lang.ThreadGroup[name=SubGroup1,maxpri=3]
//        Thread[th2,3,SubGroup1]
//        java.lang.ThreadGroup[name=Group2,maxpri=10]
//        Thread[th3,5,Group2]
    }
}
