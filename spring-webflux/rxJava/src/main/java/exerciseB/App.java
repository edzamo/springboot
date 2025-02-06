package exerciseB;

import io.reactivex.Observable;

public class App {
    public static void main(String[] args) {
        Observable<String> lists= Observable.just("hola","Caracola","HOla","mundo");

        lists.subscribe( new MyObserver());

    }
}
