package com.roc.demo.common.core.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * @Description ResultUtils
 * @Author penn
 * @Date 2022/7/2 16:59
 */
public class ResultUtils<T> {

    private final R<T> original;

    ResultUtils(R<T> original) {
        this.original = original;
    }

    public static  <T> ResultUtils<T> of(R<T> original) {
        return new ResultUtils<>(Objects.requireNonNull(original));
    }

    /**
     * 断言业务数据有值
     * @param func 用户函数,负责创建异常对象
     * @param <Ex> 异常类型
     * @return 返回实例，以便于继续进行链式操作
     * @throws Ex 断言失败时抛出
     */
    public <Ex extends Exception> ResultUtils<T> assertDataNotNull(Function<? super R<T>, ? extends Ex> func) throws Ex {
        if (Objects.isNull(original.getData())) {
            throw func.apply(original);
        }
        return this;
    }

    /**
     * 读取{@code data}的值
     * @return 返回 Optional 包装的data
     */
    public Optional<T> getData() {
        return Optional.of(original.getData());
    }
}
