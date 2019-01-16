package future;

import java.util.concurrent.Callable;

/**
 * 用于往外抛出异常
 *
 * @author: xiaoran
 * @date: 2019-01-16 16:18
 */
public class CallableException implements Callable {
    @Override
    public Object call() throws Exception {
        throw new Exception();
    }
}
