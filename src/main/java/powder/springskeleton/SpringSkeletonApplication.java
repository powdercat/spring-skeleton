package powder.springskeleton;
// 이 패키지를 포함한 하위들은 모두 스프링이 컴포넌트 스캔을 통해 스프링 빈을 등록한다.
// 기본적으로 싱글톤으로 등록한다 = 인스턴스 하나만 등록해서 공유한다. (커스터마이징 가능하지만 거의 싱글톤)

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSkeletonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSkeletonApplication.class, args);
	}

}
