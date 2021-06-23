package com.hitex.evn.filter;



import com.hitex.evn.model.logTransaction.LogTransactionBase;
import com.hitex.evn.repository.logTransaction.LogTransactionRepository;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;

@Log4j2
@Component
public class IPFilter extends FilterBase implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(IPFilter.class);
    @Autowired
    private LogTransactionRepository logTransactionRepo;

    @Value("${isWrite}")
    private int isWriteLogTransaction;

    @Override
    public void init(FilterConfig filterConfig) {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            doFilterAndLog(servletRequest, servletResponse, filterChain, logTransactionRepo, new LogTransactionBase(), isWriteLogTransaction);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        // TODO: 7/4/18
    }


}