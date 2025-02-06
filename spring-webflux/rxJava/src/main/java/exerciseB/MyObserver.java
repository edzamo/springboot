package exerciseB;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyObserver implements Observer{

    @Override
    public void onSubscribe(Disposable d) {
        System.out.println("subscribed !..");
    }

    @Override
    public void onNext(Object o) {
        System.out.println(o);
    }

    @Override
    public void onError(Throwable e) {
        System.out.println("Erro !..");

    }

    @Override
    public void onComplete() {
        System.out.println("End !..");

    }
}

