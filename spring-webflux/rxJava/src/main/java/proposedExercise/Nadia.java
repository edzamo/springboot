package proposedExercise;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Nadia implements Observer {
    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("Nadia start the radio ");
    }

    @Override
    public void onNext(Object o) {
        System.out.println("- Nadia begin to song " + ((Song)o).getTitle());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        System.out.println("* Nadia power off the radio");

    }
}
