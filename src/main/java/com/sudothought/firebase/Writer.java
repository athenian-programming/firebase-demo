package com.sudothought.firebase;

import com.firebase.client.Firebase;

import java.util.concurrent.CountDownLatch;

public class Writer {

  public static void main(String[] args)
      throws InterruptedException {

    final Firebase fb = new Firebase(Constants.FB_URL);

    for (int i = 0; i < 100; i++) {

      final CountDownLatch latch = new CountDownLatch(3);

      final Firebase.CompletionListener listener =
          (error, firebase) -> {
            final String msg = error != null
                               ? String.format("Data not saved: %s", error.getMessage())
                               : "Data saved.";
            System.out.println(msg);
            latch.countDown();
          };

      fb.child("node1")
        .setValue("The current time: " + System.currentTimeMillis(), listener);

      fb.child("node2")
        .setValue(new Person("Bob", "Sacamano"), listener);

      fb.child("node3")
        .setValue("This is a static value", listener);

      latch.await();

      Thread.sleep(3_000);
    }

    Thread.sleep(10_000);
  }
}
