package nkong.Thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/7/14 20:50
 * <p>
 * 当我们使用ExecutorService启动多个Callable时，每个Callable返回一个Future，
 * 而当我们执行Future的get方法获取结果时，可能拿到的Future并不是第一个执行完成的Callable的Future，
 * 就会进行阻塞，从而不能获取到第一个完成的Callable结果，那么这样就造成了很严重的性能损耗问题。
 * 而CompletionService正是为了解决这个问题，
 * 它是Java8的新增接口，它的实现类是ExecutorCompletionService。
 * CompletionService会根据线程池中Task的执行结果按执行完成的先后顺序排序，任务先完成的可优先获取到。
 */
public class CompletionServiceExample {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Callable<Integer>> callables = Arrays.asList(
                () -> {
                    mySleep(20);
                    System.out.println("=============20 end==============");
                    return 20;
                },
                () -> {
                    mySleep(10);
                    System.out.println("=============10 end==============");
                    return 10;
                }
        );

        //构建ExecutorCompletionService,与线程池关联
        CompletionService completionService = new ExecutorCompletionService(executorService);
        //提交Callable任务
        completionService.submit(callables.get(0));
        completionService.submit(callables.get(1));

        //获取future结果,不会阻塞
        Future<Integer> pollFuture = completionService.poll();
        //这里因为没有执行完成的Callable,所以返回null
        System.out.println(pollFuture);
        //获取future结果,最多等待3秒,不会阻塞
        Future<Integer> pollTimeOutFuture = completionService.poll(3, TimeUnit.SECONDS);
        //这里因为没有执行完成的Callable,所以返回null
        System.out.println(pollTimeOutFuture);
        //通过take获取Future结果,此方法会阻塞
        for (int i = 0; i < callables.size(); i++) {
            System.out.println(completionService.take().get());
        }
        executorService.shutdown();
        System.out.println("============main end=============");
    }

    private static void mySleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
