import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BikeRacing {
    public static void main(String[] args) throws Exception {
        CountDownLatch startLatch = new CountDownLatch(1);
        int numberOfBikers = 3;
        ExecutorService executor = Executors.newFixedThreadPool(numberOfBikers);
        
        List<Future<Long>> results = new ArrayList<>();
        String[] names = {"Kawasaki", "Ducati", "Harley"};

        // Prepare the bikers
        for (String name : names) {
            results.add(executor.submit(new Biker(name, startLatch)));
        }

        System.out.println("Ready... Set... GO!");
        startLatch.countDown(); 
        long winningTime = Long.MAX_VALUE;
        int winnerIndex = -1;

        for (int i = 0; i < results.size(); i++) {
            long finishTime = results.get(i).get();
            if (finishTime < winningTime) {
                winningTime = finishTime;
                winnerIndex = i;
            }
        }

        System.out.println("\n--- THE WINNER IS: " + names[winnerIndex] + " ---");
        executor.shutdown();
    }
}

class Biker implements Callable<Long> {
    private String name;
    private CountDownLatch startLatch;
    private int distance;
    private static AtomicInteger rank=new AtomicInteger(0);

    public Biker(String name, CountDownLatch startLatch) {
        this.name = name;
        this.startLatch = startLatch;
        this.distance=1000;
    }

    @Override
    public Long call() throws Exception {
        // All bikers wait here until the latch is released
        startLatch.await(); 
        
        long startTime = System.currentTimeMillis();
        System.out.println(name + " has started racing! at time"+startTime);
        while(distance>0)
        {
        
        // Simulate racing time (random distance/speed)
        Thread.sleep((long) (Math.random() * 3000));
        distance-=100;
        // System.out.println(name+"has complemented"+(1000-distance));
        }
        int myRank = rank.incrementAndGet();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println(name + " rank in " + myRank+" position");
        return duration;
    }
}

