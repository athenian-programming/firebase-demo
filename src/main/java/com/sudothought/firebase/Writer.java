package com.sudothought.firebase;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.concurrent.CountDownLatch;

public class Writer {
  public static void main(String[] args)
      throws InterruptedException {

    final Firebase fd = new Firebase("https://amber-inferno-8982.firebaseio.com/");

    for (int i = 0; i < 100; i++) {

      final CountDownLatch latch = new CountDownLatch(3);

      final Firebase.CompletionListener listener =
          new Firebase.CompletionListener() {
            public void onComplete(final FirebaseError error, final Firebase firebase) {
              final String msg = error != null
                                 ? String.format("Data not saved: %s", error.getMessage())
                                 : "Data saved.";
              System.out.println(msg);

              latch.countDown();
            }
          };

      fd.child("node1")
        .setValue("The current time: " + System.currentTimeMillis(), listener);

      fd.child("node2")
        .setValue(new Person("Bob", "Sacamano"), listener);

      fd.child("node3")
        .setValue("This is a static value", listener);

      latch.await();

      Thread.sleep(3_000);
    }

    Thread.sleep(10_000);
  }
}
