package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

// 표현식에 부모타입 X 정확하게 타입이 맞아야 한다.
public class WithinTest {

	AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	Method helloMethod;

	@BeforeEach
	public void init() throws NoSuchMethodException {
		helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
	}

	@Test
	void withinExact() {
		pointcut.setExpression("within(hello.aop.member.MemberServiceImpl)");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinStar() {
		pointcut.setExpression("within(hello.aop.member.*Service*)");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	void withinSubPackage() {
		pointcut.setExpression("within(hello.aop..*)");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}

	@Test
	@DisplayName("타겟의 타입에만 직접 적용, 인터페이스를 선정하면 안된다.")
	void withinSuperTypeFalse() {
		pointcut.setExpression("within(hello.aop..*)");
		Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
	}
}

