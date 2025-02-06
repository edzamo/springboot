package proposedExercise;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        Observable<Song> station = Observable.just(
                new Song("We are the champions", "Queen"),
                new Song("Dream on", "Aerosmith"),
                new Song("Mutter", "Rammstein"));

        station.delay(5, TimeUnit.SECONDS).subscribe(new Fer());
        station.delay(5,TimeUnit.SECONDS).subscribe(new Ant());
        station.delay(5,TimeUnit.SECONDS).subscribe(new Nadia());

        try {
            Thread.sleep(10000);
        }catch (InterruptedException exception){
            exception.printStackTrace();
        }

    }
}
