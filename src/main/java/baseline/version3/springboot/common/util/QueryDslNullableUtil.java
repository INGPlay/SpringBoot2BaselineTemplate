package baseline.version3.springboot.common.util;

import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class QueryDslNullableUtil {

    // -------------------------------------------------------------------------------------------------------------
    // eq
    public <T extends Number & Comparable<?>>BooleanExpression eq(NumberPath<T> path, T number){
        if (number == null){
            return null;
        }

        return path.eq(number);
    }

    public <T extends Comparable>BooleanExpression eq(DatePath<T> path, T date){
        if (date == null){
            return null;
        }

        return path.eq(date);
    }

    public <T extends Enum<T>> BooleanExpression eq(EnumPath<T> path, T e){
        if (e == null){
            return null;
        }

        return path.eq(e);
    }

    public BooleanExpression eq(StringExpression path, String string){
        if (!StringUtils.hasText(string)){
            return null;
        }

        return path.eq(string);
    }

    // -------------------------------------------------------------------------------------------------------------
    // ne
    public <T extends Number & Comparable<?>>BooleanExpression ne(NumberPath<T> path, T number){
        if (number == null){
            return null;
        }

        return path.ne(number);
    }

    public <T extends Comparable>BooleanExpression ne(DatePath<T> path, T date){
        if (date == null){
            return null;
        }

        return path.ne(date);
    }

    public BooleanExpression ne(StringExpression path, String string){
        if (!StringUtils.hasText(string)){
            return null;
        }

        return path.ne(string);
    }

    public <T extends Enum<T>> BooleanExpression ne(EnumPath<T> path, T e){
        if (e == null){
            return null;
        }

        return path.ne(e);
    }

    // -------------------------------------------------------------------------------------------------------------
    // contain
    public BooleanExpression in(StringExpression path, String... string){
        if (ObjectUtils.isEmpty(string)){
            return null;
        }

        return path.in(string);
    }

    public <T extends Number & Comparable<?>>BooleanExpression in(NumberPath<Long> path, List<Long> number){
        if (ObjectUtils.isEmpty(number)){
            return null;
        }

        return path.in(number);
    }

    public <T>BooleanExpression in (SimpleExpression<T> path, T... t) {
        if (ObjectUtils.isEmpty(t)){
            return null;
        }

        return path.in(t);
    }

}
