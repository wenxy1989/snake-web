package com.google.collections;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * Created by HP on 2017/3/10.
 */
public class OptionalTests {

    @Test
    public void nullTest(){
        Optional<Integer> possible = Optional.of(5);
//        Optional<Integer> possible = Optional.absent();
//        Optional<Integer> possible = Optional.fromNullable(5);
        System.out.println(possible.isPresent());
        System.out.println(possible.orNull());
        System.out.println(possible.get());
        System.out.println(possible.or(5));
        System.out.println(possible.asSet());
    }

}
