package com.mofushop.exception;

// TODO サンプルなので消してください。

/**
 * サンプルの独自例外
 */
public final class SampleException extends CustomException {
  private static final String description = "サンプルの独自例外です。";

  public SampleException(Throwable cause) {
    super(description, cause);
  }

  public SampleException(String reason) {
    super("%s : %s".formatted(description, reason));
  }

  public SampleException(String reason, Throwable cause) {
    super("%s : %s".formatted(description, reason), cause);
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Void getContents() {
    return null;
  }
}
