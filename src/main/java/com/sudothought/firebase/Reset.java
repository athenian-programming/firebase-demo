package com.sudothought.firebase;

import com.firebase.client.Firebase;

public class Reset {

  public static void main(String[] args)
      throws InterruptedException {

    final Firebase fb = new Firebase(Constants.FB_URL);

    fb.setValue(null);

    Thread.sleep(3_000);
  }
}
