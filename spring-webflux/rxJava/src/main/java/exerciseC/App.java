package exerciseC;

import io.reactivex.Observable;

public class App {

    public static void main(String[] args) {
        Observable<String> lists= Observable.just("hola","Caracola","HOla","mundo");

        //operation for transformation
        lists.map(String::toUpperCase).subscribe(p-> System.out.println(p));

    }
}
