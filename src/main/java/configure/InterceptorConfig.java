package configure;

import handler.ResponseResultInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *@ClassName InterceptorConfig
 *@Description TODO
 *@Author DL
 *@Date 2019/7/16 15:39    
 *@Version 1.0
 */
@Configuration
@ConditionalOnBean({ResponseResultInterceptor.class})
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private ResponseResultInterceptor responseResultInterceptor;

    public InterceptorConfig() {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUri = "/**";
        registry.addInterceptor(this.getResponseResultInterceptor()).addPathPatterns(new String[]{apiUri});
    }

    public ResponseResultInterceptor getResponseResultInterceptor() {
        return this.responseResultInterceptor;
    }

    public void setResponseResultInterceptor(ResponseResultInterceptor responseResultInterceptor) {
        this.responseResultInterceptor = responseResultInterceptor;
    }

}
