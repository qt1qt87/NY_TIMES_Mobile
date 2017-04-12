package jhk.nytimes_mobile.Interfaces;

/**
 * Async Task CallBack
 */
public interface AsyncTaskCallBack {
    /**
     * Async Task 완료시 호출 될 콜백 함수
     * @param result Async Task CallBack Result.Task 별로 다른 형태의 값을 리턴할 수 있다
     */
    void asyncTaskCallBack(Object result);
}
