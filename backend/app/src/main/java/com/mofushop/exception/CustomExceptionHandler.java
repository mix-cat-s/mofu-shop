package com.mofushop.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * SpringWeb向けのアプリ独自の例外に対するハンドラ
 */
@RestControllerAdvice
class CustomExceptionHandler extends ResponseEntityExceptionHandler {
  /**
   * アプリ独自の例外をハンドルします。
   * <p>
   * アプリ独自の例外が発生したときのHTTPステータスやレスポンスボディを作成します。
   * <p>
   * レスポンスボディには{@link ErrorResponseBody}を使用します。
   * 
   * @param ex      例外
   * @param request WebRequest
   * @return レスポンスエンティティ
   */
  @ExceptionHandler(CustomException.class)
  ResponseEntity<?> handleException(@NonNull CustomException ex, @NonNull WebRequest request) {
    final var body = ErrorResponseBody.from(ex);
    final var headers = new HttpHeaders();
    final var statusCode = switch (ex) {
      // add your custom exceptions
      case SampleException e -> HttpStatus.INTERNAL_SERVER_ERROR;
    };
    return this.handleExceptionInternal(ex, body, headers, statusCode, request);
  }
}

/**
 * エラーレスポンスのレスポンスボディ
 */
record ErrorResponseBody(
    /** エラーコード */
    String errorCode,
    /** 例外の説明 */
    String description,
    /** 例外のコンテンツ */
    Object contents,
    /** 例外メッセージ */
    String message) {
  /**
   * {@link CustomException}をもとに作成します。
   * 
   * @param ex 例外
   * @return 作成したレスポンスボディ
   */
  static ErrorResponseBody from(CustomException ex) {
    return new ErrorResponseBody(
        ex.getErrorCode(),
        ex.getDescription(),
        ex.getContents(),
        ex.getLocalizedMessage());
  }
}