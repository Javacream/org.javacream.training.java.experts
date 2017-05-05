package org.javacream.util.notification;

public interface NotificationListener<T> {
	public void handle(Notification<T> notification);
}
