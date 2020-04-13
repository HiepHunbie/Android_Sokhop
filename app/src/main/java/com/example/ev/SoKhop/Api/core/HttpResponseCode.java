package com.example.ev.SoKhop.Api.core;

public enum HttpResponseCode {
    //// TODO: 3/16/2016
    ALL_SUCCESS(200),
    ERROR_SIGNIN_MAIL_PASSWORD(1),
    ERROR_SIGNIN_FACEBOOK(2),
    ERROR_SIGNUP_MAIL(3),
    ERROR_SIGNOUT(4),
    ERROR_GET_NUMBER_UNREAD_MESSAGE(5),
    ERROR_UPLOAD_VIDEO_FAIL(6),
    ERROR_UPLOAD_VIDEO_MAX_UPLOAD(7),
    ERROR_UPLOAD_VIDEO_MAX_VOLUME(8),
    ERROR_UPLOAD_VIDEO_FILE_TYPE(9),
    ERROR_NEWS_1(10),
    ERROR_NEWS_2(11),
    ERROR_READ_NEWS_1(12),
    ERROR_READ_NEWS_2(13),
    ERROR_READ_NEWS_3(14),
    ERROR_AMAZON(15),

    SUCCESS(0),
    NO_INTERNET_ACCESS(1001),
    REQUEST_TIMEOUT(1002),
    REQUEST_ERROR(1003),
    REQUEST_UNKNOWN_ERROR(1004),
    RESPONSE_ERROR(1005),
    UNKNOWN_ERROR(99);


    private final int responseCode;

    HttpResponseCode(final int code) {
        responseCode = code;
    }

    public static String getMessage(int errCode) {
        String message = "Success";
        switch (errCode) {
            case 0:
                message = "Success";
                break;
            case 1001:
                message = "ネットワークがつながりません";
                break;
            case 1002:
                message = "リクエストタイムアウト";
                break;
            case 1003:
                message = "エーラー発生";
                break;
            case 1005:
                message = "エーラー発生";
                break;
            case 1004:
                message = "エーラー発生";
                break;
            case 99:
                message = "エーラー発生";
                break;
            case 200:
                message = "成功";
                break;
            case 1:
                message = "メールかパスワードか間違いました。";
                break;
            case 2:
                message = "ログインができない";
                break;
            case 3:
                message = "既存メール";
                break;
            case 4:
                message = "ログアウトエラー";
                break;
            case 5:
                message = "未読情報が習得できなかった";
                break;
            case 6:
                message = "Uploadが不成功";
                break;
            case 7:
                message = "5つファイル以上アップできない";
                break;
            case 8:
                message = "ファイル容量がxMB上限が超えました。";
                break;
            case 9:
                message = "ファイルタイプが正しくない";
                break;
            case 10:
                message = "ニュースが習得出来ない";
                break;
            case 11:
                message = "ニュースが習得出来ない";
                break;
            case 12:
                message = "ニュースが習得出来ない";
                break;
            case 13:
                message = "ニュースが習得出来ない";
                break;
            case 14:
                message = "ニュースが習得出来ない";
                break;
            case 15:
                message = "デバイスの登録に失敗";
                break;

        }
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
