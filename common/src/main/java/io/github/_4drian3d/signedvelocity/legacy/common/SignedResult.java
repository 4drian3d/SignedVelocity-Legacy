package io.github._4drian3d.signedvelocity.legacy.common;

public class SignedResult {
  private static final SignedResult CANCEL = new SignedResult(null);
  private static final SignedResult ALLOWED = new SignedResult(null);

  private final String message;

  private SignedResult(String message) {
    this.message = message;
  }

  public boolean cancelled() {
    return this == CANCEL;
  }

  public String toModify() {
    return this.message;
  }

  public static SignedResult cancel() {
    return CANCEL;
  }

  public static SignedResult allowed() {
    return ALLOWED;
  }

  public static SignedResult modify(final String message) {
    return new SignedResult(message);
  }
}
