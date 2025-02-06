package exerciseD;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        Observable<Long> lists= Observable.interval(1, TimeUnit.SECONDS);
        lists.subscribe(System.out::println);

        try{
            Thread.sleep(10000);
        }catch ( InterruptedException e){
            e.printStackTrace();
        }
    }

}
