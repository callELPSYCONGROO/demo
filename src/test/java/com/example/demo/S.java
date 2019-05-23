package com.example.demo;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 無痕剑
 * @date 2019/5/22 22:13
 */
public class S {

	@Test
	public void s() throws BrokenBarrierException, InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(3);

		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		cyclicBarrier.await();
		cyclicBarrier.reset();
	}
}
