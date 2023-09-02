package powder.springskeleton.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// AOP: Aspect Oriented Programming
@Aspect
@Component
public class TimeTraceAop { // spring bean 에 등록해야한다.-> @Component 써도 되는데, 이런건 SpringConfig 에서 직접 스프링 빈에 등록하는게 인지하기 더 좋다.

    // 이 공통 관심 사항을 어디에다가 적용할건지 타겟팅 할 수 있음
    @Around("execution(* powder.springskeleton..*(..))") // 패키지 하위에 다 적용한다는 뜻. 커스터마이징 가능
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            // 다음 메소드 진행
            return joinPoint.proceed();

            // 중간에 인터셉트해서 뭐든 다 할 수 있다.
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }

}
