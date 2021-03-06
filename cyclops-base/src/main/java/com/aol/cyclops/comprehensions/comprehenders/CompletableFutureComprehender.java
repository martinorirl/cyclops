package com.aol.cyclops.comprehensions.comprehenders;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;

import com.aol.cyclops.lambda.api.Comprehender;

public class CompletableFutureComprehender implements Comprehender<CompletableFuture>{
	public Class getTargetClass(){
		return CompletableFuture.class;
	}
	@Override
	public Object filter(CompletableFuture t, Predicate p) {
		return t;
	}

	@Override
	public Object map(CompletableFuture t, Function fn) {
		return t.thenApply(fn);
	}

	@Override
	public CompletableFuture flatMap(CompletableFuture t, Function fn) {
		return t.thenCompose(fn);
	}

	@Override
	public boolean instanceOfT(Object apply) {
		return apply instanceof CompletableFuture;
	}

	@Override
	public CompletableFuture of(Object o) {
		return CompletableFuture.completedFuture(o);
	}

	@Override
	public CompletableFuture empty() {
		return new CompletableFuture();
	}
	

}
