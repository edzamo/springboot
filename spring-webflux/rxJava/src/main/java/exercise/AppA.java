package exercise;

import io.reactivex.Observable;

public class AppA {

    public static void main(String[] args) {
        // Ours  frits  observable
        //this items help us to concatenate with list
        Observable<String> lista= Observable.just("hola","Caracola","HOla","mundo");

        // Go to subscribe to it
        //for each element in the subscriptions
        //Consumer is a interface functional in java 8
        /*lista.subscribe(e ->{
                System.out.println(e);
        });*/
        lista.subscribe(e-> System.out.println(e));
    }
}
