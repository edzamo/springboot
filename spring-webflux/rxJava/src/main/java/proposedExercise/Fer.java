package proposedExercise;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Fer implements Observer {
    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("Fer start the radio ");
    }

    @Override
    public void onNext(Object o) {
        System.out.println("- Fer begin to song " + ((Song)o).getTitle());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        System.out.println("* Fer power off the radio");

    }
}
