package com.aol.cyclops.lambda.api;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.Value;

public class AsStreamable {
	public static <T> Streamable<T> asStreamable(Object toCoerce){
		return new CoercedStreamable(collectStream(toCoerce));
	}
	public static <T> Streamable<T> asStreamable(Stream<T> toCoerce){
		return new CoercedStreamable(collectStream(toCoerce));
	}
	
	private static <T> T collectStream(T object){
		if(object instanceof Stream){
			Collection[] c = new Collection[]{null};
			//lazily convert the wrapped Stream to a collection
			return (T)new Iterable(){

				@Override
				public Iterator iterator() {
					if(c[0]==null)
						c[0]=(Collection) ((Stream)object).collect(Collectors.toList());
					return c[0].iterator();
				}
				
		};
		}
		return object;
	}
	@Value
	public static class CoercedStreamable<T> implements Streamable<T>{
		@Getter
		private final T streamable;
		
	}
}
