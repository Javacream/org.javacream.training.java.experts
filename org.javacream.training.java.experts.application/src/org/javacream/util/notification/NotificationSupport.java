package org.javacream.util.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationSupport<T> {
	private List<NotificationListener<T>> listeners = new ArrayList<>();
	
	public void addNotificationListener(NotificationListener<T> listener){
		listeners.add(listener);
	}
	public void removeNotificationListener(NotificationListener<T> listener){
		listeners.remove(listener);
	}
	
	public void fireNotification(String command, T data){
		Notification<T> notification = new Notification<T>(command, data);
		for (NotificationListener<T> listener: listeners){
			listener.handle(notification);
		}
	}

}
