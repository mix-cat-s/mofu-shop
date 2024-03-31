package com.mofushop.exception;

/**
 * アプリ独自の例外
 * <p>
 * アプリ独自の例外はこの例外を継承して作成します。
 */
public sealed abstract class CustomException extends RuntimeException
    permits
    DuplicatedUserNameException {
  CustomException(String message) {
    super(message);
  }

  CustomException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * 例外のエラーコードを返します。
   * <p>
   * エラーコードにはこのクラスを継承した実装クラスの名前を使用します。
   * このクラスはsealedなため、エラーコードの重複は起こらず一意になります。
   * 
   * @return 例外のエラーコード
   */
  public final String getErrorCode() {
    return this.getClass().getSimpleName();
  }

  /**
   * 例外の説明を返します。
   * 
   * @return 例外の説明
   */
  public abstract String getDescription();

  /**
   * 例外のコンテンツを返します。
   * <p>
   * 実装クラスで自由なコンテンツを持たせることができます。
   * 例外の原因やハンドリングのために必要なコンテンツを持たせるとよいです。
   * <p>
   * 実装クラスでオーバーライドする際は戻り値の型を適切な型で指定してください。
   * 
   * @return 例外のコンテンツ
   */
  public abstract Object getContents();
}
