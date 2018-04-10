package io.projectreactor.examples;

import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import reactor.adapter.rxjava.RxJava2Scheduler;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(ClassLoader.getSystemResource("sample.fxml"));

		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 300, 275));

		Flux.interval(Duration.ofSeconds(1))
				.onBackpressureBuffer()
				.publishOn(RxJava2Scheduler.from(JavaFxScheduler.platform()))
				.subscribe((i) -> primaryStage.setTitle("[" + i + "]"));

		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
