package proposedExercise;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Ant implements Observer {
    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("Ant start the radio ");
    }

    @Override
    public void onNext(Object o) {
        System.out.println("- Ant begin to song " + ((Song)o).getTitle());
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {
        System.out.println("* Ant power off the radio");

    }
}
