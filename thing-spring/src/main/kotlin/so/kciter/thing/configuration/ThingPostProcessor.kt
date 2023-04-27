package so.kciter.thing.configuration

import org.springframework.aop.Pointcut
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor
import org.springframework.aop.support.ComposablePointcut
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.Configuration


@Configuration
open class ThingPostProcessor: AbstractBeanFactoryAwareAdvisingPostProcessor(), InitializingBean {
  override fun afterPropertiesSet() {
    val classPointcut = AnnotationMatchingPointcut(ThingHandler::class.java, true)
    val methodPointcut: Pointcut = AnnotationMatchingPointcut.forMethodAnnotation(ThingHandler::class.java)
    val composedPointcut = ComposablePointcut(classPointcut).union(methodPointcut)
    advisor = DefaultPointcutAdvisor(composedPointcut, ThingInterceptor())
  }
}

