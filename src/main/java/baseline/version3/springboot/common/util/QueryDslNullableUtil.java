package baseline.version3.springboot.common.util;

import com.querydsl.core.types.dsl.*;
import org.springframework.stereotype.Component;

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

    public BooleanExpression eq(StringPath path, String string){
        if (string == null || string.isBlank()){
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

    public BooleanExpression ne(StringPath path, String string){
        if (string == null || string.isBlank()){
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
}
