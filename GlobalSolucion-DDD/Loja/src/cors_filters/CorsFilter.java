package cors_filters;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

	    // Cast to HttpServletResponse to use setHeader method
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	    // Add CORS headers to the response
	    httpResponse.setHeader("Access-Control-Allow-Origin", "*");
	    httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
	    httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
	    httpResponse.setHeader("Access-Control-Max-Age", "3600");

	    // Continue the request chain
	    chain.doFilter(request, response);
	}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
}