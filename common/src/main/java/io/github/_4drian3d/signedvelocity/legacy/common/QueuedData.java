package io.github._4drian3d.signedvelocity.legacy.common;

import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueuedData {
  private static final int timeout = 50;
  private final Queue<SignedResult> results = new ConcurrentLinkedQueue<>();
  private final Queue<CompletableFuture<SignedResult>> unSyncronizedQueue = new ConcurrentLinkedQueue<>();

  public void complete(final SignedResult result) {
    this.results.add(result);
    final CompletableFuture<SignedResult> unSynchronized = unSyncronizedQueue.poll();
    if (unSynchronized != null) {
      unSynchronized.complete(result);
    }
  }

  public CompletableFuture<SignedResult> nextResult() {
    final SignedResult result = results.poll();
    return futureFrom(result);
  }

  public CompletableFuture<SignedResult> nextResultWithoutAdvance() {
    final SignedResult result = results.peek();
    return futureFrom(result);
  }

  private CompletableFuture<SignedResult> futureFrom(final SignedResult result) {
    if (result == null) {
      final CompletableFuture<SignedResult> future = new CompletableFuture<>();
      // TODO: que wea
      // future.completeOnTimeout(SignedResult.allowed(), timeout, TimeUnit.MILLISECONDS);
      unSyncronizedQueue.add(future);
      return future;
    } else {
      return CompletableFuture.completedFuture(result);
    }
  }
}
