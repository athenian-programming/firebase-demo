package com.sudothought.firebase;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Watcher {

  public static void main(String[] args)
      throws InterruptedException {

    final Firebase fb = new Firebase(Constants.FB_URL);

    final ValueEventListener valueEventListener =
        new ValueEventListener() {
          @Override
          public void onDataChange(final DataSnapshot dataSnapshot) {
            System.out.println(String.format("ValueEventListener.onDataChange() : %s", dataSnapshot.getValue()));
          }

          @Override
          public void onCancelled(final FirebaseError error) {
            System.out.println(String.format("ValueEventListener.onCancelled() : %s", error.getMessage()));
          }
        };

    final ChildEventListener childEventListener =
        new ChildEventListener() {
          @Override
          public void onChildAdded(final DataSnapshot dataSnapshot, final String s) {
            System.out.println(String.format("ChildEventListener.onChildAdded() : [%s] %s", s, dataSnapshot.getValue()));
          }

          @Override
          public void onChildChanged(final DataSnapshot dataSnapshot, final String s) {
            System.out.println(String.format("ChildEventListener.onChildChanged() :[%s] %s", s, dataSnapshot.getValue()));
          }

          @Override
          public void onChildRemoved(final DataSnapshot dataSnapshot) {
            System.out.println(String.format("ChildEventListener.onChildChanged() : %s", dataSnapshot.getValue()));
          }

          @Override
          public void onChildMoved(final DataSnapshot dataSnapshot, final String s) {
            System.out.println(String.format("ChildEventListener.onChildChanged() :[%s] %s", s, dataSnapshot.getValue()));
          }

          @Override
          public void onCancelled(final FirebaseError error) {
            System.out.println(String.format("ChildEventListener.onCancelled() : %s", error));
          }
        };

    fb.child("node1").addValueEventListener(valueEventListener);
    fb.child("node1").addChildEventListener(childEventListener);
    fb.child("node2").addValueEventListener(valueEventListener);
    fb.child("node2").addChildEventListener(childEventListener);
    fb.child("node3").addValueEventListener(valueEventListener);
    fb.child("node3").addChildEventListener(childEventListener);

    Thread.sleep(100_000_000);
  }
}
