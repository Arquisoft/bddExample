package es.uniovi.asw.listeners;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.ManagedBean;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

    private final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    private ObservableEmitter<String> observer;

    @KafkaListener(topics = "exampleTopic")
    public void listen(String data) {
        if(observer != null)
            observer.onNext(data);
        logger.info("New message received: \"" + data + "\"");
    }

    public Observable<String> getObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observer = observableEmitter;
            }
        });
    }



}
