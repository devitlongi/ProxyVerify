package sk.longi.proxy.logging;



import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.longi.proxy.logging.Log;


@Interceptor
@Log
@Priority(Interceptor.Priority.APPLICATION+10)
public class LoggerInterceptor {


    @AroundInvoke
    public Object logMethod(InvocationContext context) throws Exception {

        final Logger LOGGER = LoggerFactory.getLogger(context.getClass());
        LOGGER.info("**************************************Method name:{} ", context.getMethod().getName());
//        LOGGER.debug("Method param:{} ", context.getContextData().values());
//        LOGGER.debug("Method param1:{} ", context.getParameters().toString());
//        LOGGER.debug("Method param2:{} ", context.getMethod().toString());
//        LOGGER.debug("Method param3:{} ", context.getMethod().getParameterCount());

//        System.out.println("START " + context.getMethod().getName());
        Object result = context.proceed();

        return result;

    }
}
